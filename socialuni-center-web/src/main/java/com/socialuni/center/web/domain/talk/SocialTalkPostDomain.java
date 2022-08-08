package com.socialuni.center.web.domain.talk;

import com.socialuni.center.web.constant.CommonConst;
import com.socialuni.center.web.constant.TalkOperateType;
import com.socialuni.center.web.domain.report.ReportDomain;
import com.socialuni.center.web.factory.SocialTalkROFactory;
import com.socialuni.center.web.factory.TalkImgDOFactory;
import com.socialuni.center.web.manage.talk.SocialTalkCreateManage;
import com.socialuni.center.web.model.TalkAddValidateRO;
import com.socialuni.center.web.repository.community.*;
import com.socialuni.center.web.service.content.ModelContentCheck;
import com.socialuni.center.web.service.tag.TagService;
import com.socialuni.center.web.utils.CenterUserUtil;
import com.socialuni.center.web.utils.DistrictStoreUtils;
import com.socialuni.center.web.utils.TalkRedis;
import com.socialuni.social.constant.CommonStatus;
import com.socialuni.social.constant.GenderType;
import com.socialuni.center.web.model.DO.DistrictDO;
import com.socialuni.center.web.model.DO.circle.SocialCircleDO;
import com.socialuni.center.web.model.DO.tag.TagDO;
import com.socialuni.center.web.model.DO.talk.SocialTalkCircleDO;
import com.socialuni.center.web.model.DO.talk.SocialTalkImgDO;
import com.socialuni.center.web.model.DO.talk.SocialTalkTagDO;
import com.socialuni.center.web.model.DO.talk.SocialTalkDO;
import com.socialuni.center.web.model.DO.user.SocialUserDO;
import com.socialuni.social.exception.SocialBusinessException;
import com.socialuni.social.exception.SocialParamsException;
import com.socialuni.center.web.model.QO.community.talk.SocialTalkPostQO;
import com.socialuni.center.web.model.RO.community.talk.SocialTalkRO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class SocialTalkPostDomain {
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
    ModelContentCheck modelContentCheck;

    public SocialTalkRO postTalk(SocialTalkPostQO talkPostQO) {
        SocialUserDO mineUser = CenterUserUtil.getMineUserNotNull();

        modelContentCheck.checkUserAndLongContent(talkPostQO.getContent(), mineUser);


        //校验内容是否违规
//        modelContentCheck.checkUserAndContent(addVO.getContent(), requestUser);
        //获取应用对应的话题
        TalkAddValidateRO talkAddValidateRO = this.paramsValidate(mineUser, talkPostQO);
        SocialTalkDO talkDO = this.saveEntity(mineUser, talkPostQO, talkAddValidateRO.getDistrict(), talkAddValidateRO.getTags(), talkAddValidateRO.getCircle());
        reportDomain.checkKeywordsCreateReport(talkDO);
        //不使用图片安全校验
        //        reportDomain.checkImgCreateReport(talkDO, talkPostQO.getImgs());
        SocialTalkRO socialTalkRO = SocialTalkROFactory.getTalkRO(talkDO, mineUser);
        return socialTalkRO;
    }


    public TalkAddValidateRO paramsValidate(SocialUserDO mineUser, SocialTalkPostQO talkVO) {

        //校验地理位置
        String adCode = talkVO.getAdCode();
        //如果为未定位或者老版本，adcode为空，则设置为中国地区
        if (adCode == null) {
            adCode = CommonConst.chinaDistrictCode;
        }
        //根据adcode获取地区名
        DistrictDO districtDO = DistrictStoreUtils.findFirstOneByAdCode(adCode);
        if (districtDO == null) {
            throw new SocialParamsException("选择了不存在的地区");
        }

        String talkVisibleGender = talkVO.getVisibleGender();
        //获取经过后台认证的 行政区DO
        //话题校验
        List<Integer> tagIds = talkVO.getTagIds();

        if (tagIds == null) {
            tagIds = new ArrayList<>();
        }
        List<String> tagNames = talkVO.getTagNames();
        for (String tagName : tagNames) {
            TagDO tagDO = tagRepository.findFirstByName(tagName);
            if (tagDO == null || !tagDO.getStatus().equals(CommonStatus.enable)) {
                throw new SocialBusinessException("选择了无效的话题");
            }
            tagIds.add(tagDO.getId());
        }
        talkVO.setTagIds(tagIds);

        List<TagDO> list = tagService.checkAndUpdateTagCount(mineUser, tagIds, TalkOperateType.talkAdd, talkVisibleGender);

        String circleName = talkVO.getCircleName();
        SocialCircleDO socialCircleDO = null;
        if (StringUtils.isNotEmpty(circleName)) {
            socialCircleDO = socialCircleRepository.findFirstByName(talkVO.getCircleName());
            if (socialCircleDO != null) {
                socialCircleDO.setCount(socialCircleDO.getCount() + 1);
                socialCircleDO = socialCircleRepository.save(socialCircleDO);
            }
        }

        TalkAddValidateRO talkAddValidateRO = new TalkAddValidateRO(districtDO, list, socialCircleDO);
        return talkAddValidateRO;
    }

    public SocialTalkDO saveEntity(SocialUserDO userDO, SocialTalkPostQO socialTalkPostQO, DistrictDO district, List<TagDO> tags, SocialCircleDO socialCircleDO) {
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

        for (SocialTalkImgDO imgDO : imgDOS) {
            imgDO.setContentId(talkDO.getUnionId());
            imgDO.setUserId(talkDO.getUserId());
        }

        List<SocialTalkImgDO> talkImgDOS = talkImgRepository.saveAll(imgDOS);

        if (socialCircleDO != null) {
            SocialTalkCircleDO socialTalkCircleDO = new SocialTalkCircleDO();
            socialTalkCircleDO.setTalkId(talkDO.getUnionId());
            socialTalkCircleDO.setCircleId(socialCircleDO.getId());
            socialTalkCircleRepository.save(socialTalkCircleDO);
        }

        return talkDO;
    }
}
