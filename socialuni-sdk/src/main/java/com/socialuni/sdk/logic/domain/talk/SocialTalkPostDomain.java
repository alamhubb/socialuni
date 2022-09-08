package com.socialuni.sdk.logic.domain.talk;

import com.socialuni.sdk.constant.SocialuniConst;
import com.socialuni.sdk.constant.TalkOperateType;
import com.socialuni.sdk.dao.repository.community.*;
import com.socialuni.sdk.logic.domain.report.ReportDomain;
import com.socialuni.sdk.factory.SocialTalkROFactory;
import com.socialuni.sdk.factory.TalkImgDOFactory;
import com.socialuni.sdk.logic.manage.talk.SocialTalkCreateManage;
import com.socialuni.sdk.model.DO.DistrictDO;
import com.socialuni.sdk.model.DO.circle.SocialCircleDO;
import com.socialuni.sdk.model.DO.tag.TagDO;
import com.socialuni.sdk.model.DO.talk.SocialTalkCircleDO;
import com.socialuni.sdk.model.DO.talk.SocialTalkDO;
import com.socialuni.sdk.model.DO.talk.SocialTalkImgDO;
import com.socialuni.sdk.model.DO.talk.SocialTalkTagDO;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.model.QO.community.talk.SocialTalkPostQO;
import com.socialuni.sdk.model.RO.community.talk.SocialTalkRO;
import com.socialuni.sdk.model.TalkAddValidateRO;
import com.socialuni.sdk.logic.service.content.ModelContentCheck;
import com.socialuni.sdk.logic.service.tag.TagService;
import com.socialuni.sdk.utils.DistrictStoreUtils;
import com.socialuni.sdk.utils.SocialUserUtil;
import com.socialuni.sdk.utils.TalkRedis;
import com.socialuni.sdk.constant.socialuni.CommonStatus;
import com.socialuni.sdk.constant.socialuni.GenderType;
import com.socialuni.social.web.sdk.exception.SocialBusinessException;
import com.socialuni.social.web.sdk.exception.SocialParamsException;
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
        SocialUserDO mineUser = SocialUserUtil.getMineUserNotNull();

        List<String> tagNames = talkPostQO.getTagNames();

        if (!tagNames.contains(SocialuniConst.devEnvTagName)) {
            modelContentCheck.checkUserAndLongContent(talkPostQO.getContent(), mineUser);
        }

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
            adCode = SocialuniConst.chinaDistrictCode;
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
