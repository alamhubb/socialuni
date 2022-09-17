package com.socialuni.sdk.logic.domain.talk;

import com.socialuni.sdk.constant.SocialuniConst;
import com.socialuni.sdk.constant.TalkOperateType;
import com.socialuni.sdk.dao.DO.talk.*;
import com.socialuni.sdk.dao.repository.community.*;
import com.socialuni.sdk.logic.check.SocialuniUserCheck;
import com.socialuni.sdk.logic.domain.report.ReportDomain;
import com.socialuni.sdk.logic.factory.SocialTalkROFactory;
import com.socialuni.sdk.logic.factory.TalkImgDOFactory;
import com.socialuni.sdk.logic.manage.talk.SocialTalkCreateManage;
import com.socialuni.sdk.dao.DO.DistrictDO;
import com.socialuni.sdk.dao.DO.circle.SocialuniCircleDO;
import com.socialuni.sdk.dao.DO.tag.TagDO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.logic.service.content.SocialuniContentCheckUtil;
import com.socialuni.sdk.model.QO.community.talk.SocialTalkImgAddQO;
import com.socialuni.sdk.model.QO.community.talk.SocialuniTalkPostQO;
import com.socialuni.sdk.model.RO.talk.SocialuniTalkRO;
import com.socialuni.sdk.model.TalkAddValidateRO;
import com.socialuni.sdk.logic.service.tag.TagService;
import com.socialuni.sdk.utils.DistrictStoreUtils;
import com.socialuni.sdk.utils.ImgContentUtil;
import com.socialuni.sdk.utils.SocialuniUserUtil;
import com.socialuni.sdk.utils.TalkRedis;
import com.socialuni.sdk.constant.socialuni.CommonStatus;
import com.socialuni.sdk.constant.socialuni.GenderType;
import com.socialuni.sdk.utils.model.DO.SocialuniCircleDOUtil;
import com.socialuni.social.web.sdk.exception.SocialBusinessException;
import com.socialuni.social.web.sdk.exception.SocialParamsException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class SocialuniPostTalkDomain {
    @Resource
    TalkRepository talkRepository;
    @Resource
    TagService tagService;

    @Resource
    TagRepository tagRepository;
    @Resource
    ReportDomain reportDomain;
    @Resource
    TalkRedis talkRedis;
    @Resource
    TalkTagRepository talkTagRepository;
    @Resource
    SocialCircleRepository socialCircleRepository;
    @Resource
    TalkImgRepository talkImgRepository;
    @Resource
    SocialTalkCreateManage socialTalkCreateManage;
    @Resource
    SocialTalkCircleRepository socialTalkCircleRepository;
    @Resource
    TalkAdultImgAuditRepository talkAdultAuditRepository;


    @Transactional
    public SocialuniTalkRO postTalk(SocialuniTalkPostQO talkPostQO) {
        SocialuniUserDO mineUser = SocialuniUserUtil.getMineUserNotNull();
        //校验内容
        TalkAddValidateRO talkAddValidateRO = this.paramsValidate(mineUser, talkPostQO);


        //校验内容是否违规
//        modelContentCheck.checkUserAndContent(addVO.getContent(), requestUser);
        //获取应用对应的话题

        SocialTalkDO talkDO = this.saveEntity(mineUser, talkPostQO, talkAddValidateRO.getDistrict(), talkAddValidateRO.getTags(), talkAddValidateRO.getCircle());

        reportDomain.checkKeywordsCreateReport(talkDO);

        List<SocialTalkImgDO> imgDOS = talkDO.getImgs();

        for (SocialTalkImgDO imgDO : imgDOS) {
            //如果用户包含人物头像,且用户未认证
            if (imgDO.getHasPeopleImg() && !imgDO.getAdultAuth()) {
                //则添加一条待审核的动态，qq平台只查询审核通过的动态
                SocialTalkImgAdultAuditDO socialTalkAdultAuditDO = new SocialTalkImgAdultAuditDO(imgDO.getId());
                talkAdultAuditRepository.save(socialTalkAdultAuditDO);
            }
        }
        SocialuniTalkRO socialTalkRO = SocialTalkROFactory.getTalkRO(talkDO, mineUser);
        return socialTalkRO;
    }


    public TalkAddValidateRO paramsValidate(SocialuniUserDO mineUser, SocialuniTalkPostQO talkVO) {
        List<String> tagNames = talkVO.getTagNames();
        String talkVOContent = talkVO.getContent();
        //校验用户
        SocialuniUserCheck.checkUserBindPhoneNumAndStatusNoEnable(mineUser);

        List<SocialTalkImgAddQO> talkVOImgs = talkVO.getImgs();
        //校验内容不能全部为空
        if (StringUtils.isEmpty(talkVOContent) && CollectionUtils.isEmpty(talkVOImgs)) {
            throw new SocialParamsException("'不能发布文字和图片均为空的动态'");
        }


        //不为开发环境，则校验内容
        if (!tagNames.contains(SocialuniConst.devEnvTagName)) {
            SocialuniContentCheckUtil.checkUserInputLongTextContent(talkVOContent);
            //不使用图片安全校验，原因是啥，今晚确认
//            reportDomain.checkImgCreateReport(talkPostQO.getImgs());
            //还需要获取图片中的cor
        }

        for (SocialTalkImgAddQO talkVOImg : talkVOImgs) {
            String imgTextContent = ImgContentUtil.getImgTextContent(talkVOImg.getSrc());
            talkVOImg.setContent(imgTextContent);
        }

        //校验地理位置
        String adCode = talkVO.getAdCode();
        //如果为未定位或者老版本，adcode为空，则设置为中国地区
        if (adCode == null) {
            adCode = SocialuniConst.chinaDistrictCode;
        }
        //根据adcode获取地区名
        DistrictDO districtDO = DistrictStoreUtils.findFirstOneByAdCode(adCode);
        if (districtDO == null) {
            throw new SocialParamsException("选择了不存在的地区");
        }

//        String talkVisibleGender = talkVO.getVisibleGender();
        //获取经过后台认证的 行政区DO
        //话题校验
        List<Integer> tagIds = talkVO.getTagIds();

        if (tagIds == null) {
            tagIds = new ArrayList<>();
        }
        for (String tagName : tagNames) {
            TagDO tagDO = tagRepository.findFirstByName(tagName);
            if (tagDO == null || !tagDO.getStatus().equals(CommonStatus.enable)) {
                throw new SocialBusinessException("选择了无效的话题");
            }
            tagIds.add(tagDO.getId());
        }
        talkVO.setTagIds(tagIds);

        List<TagDO> list = tagService.checkAndUpdateTagCount(mineUser, tagIds, TalkOperateType.talkAdd);

        String circleName = talkVO.getCircleName();
        SocialuniCircleDO socialCircleDO = null;
        if (StringUtils.isNotEmpty(circleName)) {
            socialCircleDO = SocialuniCircleDOUtil.getCircleEnableNotNull(talkVO.getCircleName());
            socialCircleDO.setCount(socialCircleDO.getCount() + 1);
            socialCircleDO = socialCircleRepository.save(socialCircleDO);
        }
        TalkAddValidateRO talkAddValidateRO = new TalkAddValidateRO(districtDO, list, socialCircleDO);
        return talkAddValidateRO;
    }

    public SocialTalkDO saveEntity(SocialuniUserDO userDO, SocialuniTalkPostQO socialTalkPostQO, DistrictDO district, List<TagDO> tags, SocialuniCircleDO socialCircleDO) {
        String talkVisibleGender = socialTalkPostQO.getVisibleGender();
        //不为全部，添加默认标签
        if (!talkVisibleGender.equals(GenderType.all)) {
            //添加默认标签，解决查询没tag查询不出来的问题
            if (talkVisibleGender.equals(GenderType.girl)) {
                //这里一定有值，没值就是创建开发者的时候没创建tag，修复逻辑
                TagDO genderTag = tagRepository.findFirstByName(GenderType.girlTagName);
                tags.add(genderTag);
            } else if (talkVisibleGender.equals(GenderType.boy)) {
                //这里一定有值，没值就是创建开发者的时候没创建tag，修复逻辑
                TagDO genderTag = tagRepository.findFirstByName(GenderType.boyTagName);
                tags.add(genderTag);
            }
            //还差男生的没写
        }

        SocialTalkDO talkDO = socialTalkCreateManage.createTalkDO(userDO, socialTalkPostQO, district);


        List<SocialTalkTagDO> list = new ArrayList<>();

        for (TagDO tagDO : tags) {
            SocialTalkTagDO socialTalkTagDO = new SocialTalkTagDO();
            socialTalkTagDO.setTagId(tagDO.getId());
            socialTalkTagDO.setTalkId(talkDO.getUnionId());
            list.add(socialTalkTagDO);
        }
        talkTagRepository.saveAll(list);

        List<SocialTalkImgDO> imgDOS = TalkImgDOFactory.newTalkImgDOS(socialTalkPostQO.getImgs());

        //用户是否已经认证
        Boolean userIdentityAuth = SocialuniUserUtil.getUserIsIdentityAuth(talkDO.getUserId());

        for (SocialTalkImgDO imgDO : imgDOS) {
            imgDO.setTalkId(talkDO.getUnionId());
            imgDO.setUserId(talkDO.getUserId());
            //包含未成年内容
            boolean hasUn18Content = SocialuniContentCheckUtil.hasUn18ContentThrowError(imgDO.getContent());
            imgDO.setHasUnderageContent(hasUn18Content);
            //是否包含图片
            boolean hasPeople = ImgContentUtil.hasPeopleImg(imgDO.getSrc());
            if (hasPeople) {
                imgDO.setHasPeopleImg(true);
            }
            //是否成年认证通过
            imgDO.setAdultAuth(userIdentityAuth);
        }

        List<SocialTalkImgDO> talkImgDOS = talkImgRepository.saveAll(imgDOS);
        talkDO.setImgs(talkImgDOS);

        if (socialCircleDO != null) {
            SocialTalkCircleDO socialTalkCircleDO = new SocialTalkCircleDO();
            socialTalkCircleDO.setTalkId(talkDO.getUnionId());
            socialTalkCircleDO.setCircleId(socialCircleDO.getId());
            socialTalkCircleRepository.save(socialTalkCircleDO);
        }
        return talkDO;
    }
}
