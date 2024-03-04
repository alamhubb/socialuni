package com.socialuni.social.community.sdk.logic.domain.talk;

import com.socialuni.social.common.api.enumeration.SocialuniCommonStatus;
import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.community.sdk.dao.DO.SocialuniDistrictDO;
import com.socialuni.social.common.sdk.dao.DO.SocialuniCircleDO;
import com.socialuni.social.community.sdk.dao.DO.SocialuniTalkDO;
import com.socialuni.social.common.sdk.dao.DO.SocialuniTagDO;
import com.socialuni.social.common.sdk.dao.repository.SocialuniCircleRepository;
import com.socialuni.social.common.sdk.dao.repository.TagRepository;
import com.socialuni.social.tance.sdk.config.SocialuniAppConfig;
import com.socialuni.social.common.sdk.constant.SocialuniConst;
import com.socialuni.social.sdk.constant.TalkOperateType;
import com.socialuni.social.common.sdk.constant.UserType;
import com.socialuni.social.community.sdk.dao.DO.SocialTalkCircleDO;
import com.socialuni.social.community.sdk.dao.DO.SocialTalkTagDO;
import com.socialuni.social.community.sdk.dao.DO.SocialuniTalkImgDO;
import com.socialuni.social.community.sdk.repository.SocialTalkCircleRepository;
import com.socialuni.social.community.sdk.repository.TalkImgRepository;
import com.socialuni.social.community.sdk.repository.TalkTagRepository;
import com.socialuni.social.community.sdk.dao.SocialuniCircleDOUtil;
import com.socialuni.social.community.sdk.dao.SocialuniTalkDORedis;
import com.socialuni.social.user.sdk.dao.utils.SocialuniUserExpandDOUtil;
import com.socialuni.social.user.sdk.logic.check.SocialuniUserCheck;
import com.socialuni.social.community.sdk.logic.factory.TalkImgDOFactory;
import com.socialuni.social.community.sdk.logic.manage.talk.SocialTalkCreateManage;
import com.socialuni.social.report.sdk.utils.SocialuniTextContentUtil;
import com.socialuni.social.sdk.logic.service.tag.TagService;
import com.socialuni.social.common.sdk.model.SocialuniImgAddQO;
import com.socialuni.social.community.sdk.model.QO.talk.SocialuniTalkPostQO;
import com.socialuni.social.sdk.model.TalkAddValidateRO;
import com.socialuni.social.user.sdk.dao.utils.DistrictStoreUtils;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.common.api.constant.GenderType;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
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
    TagService tagService;

    @Resource
    TagRepository tagApi;
    @Resource
    SocialuniTalkDORedis talkRedis;
    @Resource
    TalkTagRepository talkTagRepository;
    @Resource
    SocialuniCircleRepository socialCircleApi;
    @Resource
    SocialTalkCreateManage socialTalkCreateManage;
    @Resource
    SocialTalkCircleRepository socialTalkCircleRepository;
    @Resource
    TalkImgRepository talkImgRepository;


    /*@Transactional
    public SocialuniTalkRO postTalk(SocialuniTalkPostQO talkPostQO) {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();
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


    public TalkAddValidateRO paramsValidate(SocialuniUserDo mineUser, SocialuniTalkPostQO talkVO) {
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
                SocialuniUserCheck.checkUserBindPhoneNumAndStatusEnable();
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
        SocialuniDistrictDO districtDO = DistrictStoreUtils.findFirstOneByAdCode(adCode);
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
            SocialuniTagDO TagDO = tagApi.findFirstByName(tagName);
            if (TagDO == null || !TagDO.getStatus().equals(SocialuniCommonStatus.enable)) {
                throw new SocialBusinessException("选择了无效的话题");
            }
            tagIds.add(TagDO.getId());
        }
        talkVO.setTagIds(tagIds);

        List<SocialuniTagDO> list = tagService.checkAndUpdateTagCount(mineUser, tagIds, TalkOperateType.talkAdd);

        String circleName = talkVO.getCircleName();
        SocialuniCircleDO socialCircleDO = null;
        if (StringUtils.isNotEmpty(circleName)) {
            socialCircleDO = SocialuniCircleDOUtil.getCircleEnableNotNull(talkVO.getCircleName());
            socialCircleDO.setCount(socialCircleDO.getCount() + 1);
            socialCircleDO = socialCircleApi.savePut(socialCircleDO);
        }
        TalkAddValidateRO talkAddValidateRO = new TalkAddValidateRO(districtDO, list, socialCircleDO);
        return talkAddValidateRO;
    }

    public SocialuniTalkDO saveEntity(SocialuniUserDo userDO, SocialuniTalkPostQO socialTalkPostQO, SocialuniDistrictDO district, List<SocialuniTagDO> tags, SocialuniCircleDO socialCircleDO) {
        String talkVisibleGender = socialTalkPostQO.getVisibleGender();
        //不为全部，添加默认标签
        if (!talkVisibleGender.equals(GenderType.all)) {
            //添加默认标签，解决查询没tag查询不出来的问题
            if (talkVisibleGender.equals(GenderType.girl)) {
                //这里一定有值，没值就是创建开发者的时候没创建tag，修复逻辑
                SocialuniTagDO genderTag = tagApi.findFirstByName(GenderType.girlTagName);
                tags.add(genderTag);
            } else if (talkVisibleGender.equals(GenderType.boy)) {
                //这里一定有值，没值就是创建开发者的时候没创建tag，修复逻辑
                SocialuniTagDO genderTag = tagApi.findFirstByName(GenderType.boyTagName);
                tags.add(genderTag);
            }
            //还差男生的没写
        }
        SocialuniTalkDO talkDO = socialTalkCreateManage.createTalkDO(userDO, socialTalkPostQO, district);

        List<SocialTalkTagDO> list = new ArrayList<>();
        for (SocialuniTagDO TagDO : tags) {
            SocialTalkTagDO socialTalkTagDO = new SocialTalkTagDO();
            socialTalkTagDO.setTagId(TagDO.getId());
            socialTalkTagDO.setTalkId(talkDO.getUnionId());
            list.add(socialTalkTagDO);
        }
        talkTagRepository.saveAll(list);

        //不能为异步因为会存在查不出来图片的问题
        List<SocialuniTalkImgDO> imgDOS = TalkImgDOFactory.newTalkImgDOS(socialTalkPostQO.getImgs());
        for (SocialuniTalkImgDO talkImgDO : imgDOS) {
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
