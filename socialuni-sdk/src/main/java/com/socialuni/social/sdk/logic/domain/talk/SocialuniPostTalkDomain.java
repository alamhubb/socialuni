package com.socialuni.social.sdk.logic.domain.talk;

import com.socialuni.social.common.enumeration.CommonStatus;
import com.socialuni.social.common.exception.exception.SocialBusinessException;
import com.socialuni.social.common.exception.exception.SocialParamsException;
import com.socialuni.social.community.sdk.api.SocialCircleInterface;
import com.socialuni.social.community.sdk.api.TagInterface;
import com.socialuni.social.community.sdk.api.TalkInterface;
import com.socialuni.social.sdk.config.SocialuniAppConfig;
import com.socialuni.social.sdk.constant.SocialuniConst;
import com.socialuni.social.sdk.constant.TalkOperateType;
import com.socialuni.social.sdk.constant.UserType;
import com.socialuni.social.sdk.dao.DO.DistrictDO;
import com.socialuni.social.community.sdk.model.SocialuniCircleModel;
import com.socialuni.social.sdk.dao.DO.community.talk.SocialTalkCircleDO;
import com.socialuni.social.sdk.dao.DO.community.talk.SocialTalkTagDO;
import com.socialuni.social.community.sdk.model.SocialuniTalkModel;
import com.socialuni.social.sdk.dao.DO.community.talk.SocialuniTalkImgModel;
import com.socialuni.social.community.sdk.model.TagModel;
import com.socialuni.social.sdk.dao.repository.community.*;
import com.socialuni.social.sdk.dao.utils.SocialuniCircleDOUtil;
import com.socialuni.social.sdk.dao.utils.content.SocialuniTalkDORedis;
import com.socialuni.social.sdk.dao.utils.user.SocialuniUserExpandDOUtil;
import com.socialuni.social.sdk.logic.check.SocialuniUserCheck;
import com.socialuni.social.sdk.logic.factory.TalkImgDOFactory;
import com.socialuni.social.sdk.logic.manage.talk.SocialTalkCreateManage;
import com.socialuni.social.sdk.logic.service.content.SocialuniTextContentUtil;
import com.socialuni.social.sdk.logic.service.tag.TagService;
import com.socialuni.social.sdk.model.QO.SocialuniImgAddQO;
import com.socialuni.social.sdk.model.QO.community.talk.SocialuniTalkPostQO;
import com.socialuni.social.sdk.model.TalkAddValidateRO;
import com.socialuni.social.sdk.utils.DistrictStoreUtils;
import com.socialuni.social.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.tance.sdk.enumeration.GenderType;
import com.socialuni.social.user.sdk.model.SocialuniUserModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class SocialuniPostTalkDomain {
    @Resource
    TalkInterface talkApi;
    @Resource
    TagService tagService;

    @Resource
    TagInterface tagApi;
    @Resource
    SocialuniTalkDORedis talkRedis;
    @Resource
    TalkTagRepository talkTagRepository;
    @Resource
    SocialCircleInterface socialCircleApi;
    @Resource
    SocialTalkCreateManage socialTalkCreateManage;
    @Resource
    SocialTalkCircleRepository socialTalkCircleRepository;
    @Resource
    TalkImgRepository talkImgRepository;


    /*@Transactional
    public SocialuniTalkRO postTalk(SocialuniTalkPostQO talkPostQO) {
        SocialuniUserDO mineUser = SocialuniUserUtil.getMineUserNotNull();
        //校验内容
        TalkAddValidateRO talkAddValidateRO = this.paramsValidate(mineUser, talkPostQO);


        //校验内容是否违规
//        modelContentCheck.checkUserAndContent(addVO.getContent(), requestUser);
        //获取应用对应的话题

        SocialuniTalkDO talkDO = this.saveEntity(mineUser, talkPostQO, talkAddValidateRO.getDistrict(), talkAddValidateRO.getTags(), talkAddValidateRO.getCircle());

        SoicialuniSystemPreCheckReportDomainDOUtil.systemPreCheckReport(talkDO);

        SocialuniTalkRO socialTalkRO = SocialTalkROFactory.getTalkRO(talkDO, mineUser);
        return socialTalkRO;
    }*/


    public TalkAddValidateRO paramsValidate(SocialuniUserModel mineUser, SocialuniTalkPostQO talkVO) {
        List<String> tagNames = talkVO.getTagNames();
        String talkVOContent = talkVO.getContent();

        List<SocialuniImgAddQO> talkVOImgs = talkVO.getImgs();
        //校验内容不能全部为空
        if (StringUtils.isEmpty(talkVOContent) && CollectionUtils.isEmpty(talkVOImgs)) {
            throw new SocialParamsException("'不能发布文字和图片均为空的动态'");
        }

        if (SocialuniAppConfig.getAppConfig().getMustSetSchoolCanPost()) {
            SocialuniUserExpandDOUtil.getUserSchoolNameNotNull(SocialuniUserUtil.getMineUserIdNotNull());
        }

        //系统管理员不校验相关内容
        if (!UserType.system.equals(mineUser.getType())) {
            //不为开发环境，则校验内容
            if (!tagNames.contains(SocialuniConst.devEnvTagName)) {
                //校验用户
                SocialuniUserCheck.checkUserBindPhoneNumAndStatusNoEnable(mineUser);
            }
            SocialuniTextContentUtil.checkTextHasUnderageAndContactAndViolateWords(talkVOContent);
        }

        for (SocialuniImgAddQO talkVOImg : talkVOImgs) {
            SocialuniTextContentUtil.validateImg(talkVOImg, mineUser);
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
            TagModel tagModel = tagApi.findFirstByName(tagName);
            if (tagModel == null || !tagModel.getStatus().equals(CommonStatus.enable)) {
                throw new SocialBusinessException("选择了无效的话题");
            }
            tagIds.add(tagModel.getId());
        }
        talkVO.setTagIds(tagIds);

        List<TagModel> list = tagService.checkAndUpdateTagCount(mineUser, tagIds, TalkOperateType.talkAdd);

        String circleName = talkVO.getCircleName();
        SocialuniCircleModel socialCircleDO = null;
        if (StringUtils.isNotEmpty(circleName)) {
            socialCircleDO = SocialuniCircleDOUtil.getCircleEnableNotNull(talkVO.getCircleName());
            socialCircleDO.setCount(socialCircleDO.getCount() + 1);
            socialCircleDO = socialCircleApi.savePut(socialCircleDO);
        }
        TalkAddValidateRO talkAddValidateRO = new TalkAddValidateRO(districtDO, list, socialCircleDO);
        return talkAddValidateRO;
    }

    public SocialuniTalkModel saveEntity(SocialuniUserModel userDO, SocialuniTalkPostQO socialTalkPostQO, DistrictDO district, List<TagModel> tags, SocialuniCircleModel socialCircleDO) {
        String talkVisibleGender = socialTalkPostQO.getVisibleGender();
        //不为全部，添加默认标签
        if (!talkVisibleGender.equals(GenderType.all)) {
            //添加默认标签，解决查询没tag查询不出来的问题
            if (talkVisibleGender.equals(GenderType.girl)) {
                //这里一定有值，没值就是创建开发者的时候没创建tag，修复逻辑
                TagModel genderTag = tagApi.findFirstByName(GenderType.girlTagName);
                tags.add(genderTag);
            } else if (talkVisibleGender.equals(GenderType.boy)) {
                //这里一定有值，没值就是创建开发者的时候没创建tag，修复逻辑
                TagModel genderTag = tagApi.findFirstByName(GenderType.boyTagName);
                tags.add(genderTag);
            }
            //还差男生的没写
        }
        SocialuniTalkModel talkDO = socialTalkCreateManage.createTalkDO(userDO, socialTalkPostQO, district);

        List<SocialTalkTagDO> list = new ArrayList<>();
        for (TagModel tagModel : tags) {
            SocialTalkTagDO socialTalkTagDO = new SocialTalkTagDO();
            socialTalkTagDO.setTagId(tagModel.getId());
            socialTalkTagDO.setTalkId(talkDO.getUnionId());
            list.add(socialTalkTagDO);
        }
        talkTagRepository.saveAll(list);

        //不能为异步因为会存在查不出来图片的问题
        List<SocialuniTalkImgModel> imgDOS = TalkImgDOFactory.newTalkImgDOS(socialTalkPostQO.getImgs());
        for (SocialuniTalkImgModel talkImgDO : imgDOS) {
            talkImgDO.setTalkId(talkDO.getUnionId());
            talkImgDO.setUserId(talkDO.getUserId());
        }
        talkImgRepository.saveAll(imgDOS);

        if (socialCircleDO != null) {
            SocialTalkCircleDO socialTalkCircleDO = new SocialTalkCircleDO();
            socialTalkCircleDO.setTalkId(talkDO.getUnionId());
            socialTalkCircleDO.setCircleId(socialCircleDO.getId());
            socialTalkCircleRepository.save(socialTalkCircleDO);
        }
        return talkDO;
    }
}
