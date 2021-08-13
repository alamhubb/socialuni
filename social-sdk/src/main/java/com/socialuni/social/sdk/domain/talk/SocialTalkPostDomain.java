package com.socialuni.social.sdk.domain.talk;

import com.socialuni.social.constant.DateTimeType;
import com.socialuni.social.constant.GenderType;
import com.socialuni.social.entity.model.DO.DistrictDO;
import com.socialuni.social.entity.model.DO.tag.TagDO;
import com.socialuni.social.entity.model.DO.talk.SocialTalkImgDO;
import com.socialuni.social.entity.model.DO.talk.SocialTalkTagDO;
import com.socialuni.social.entity.model.DO.talk.TalkDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.exception.SocialBusinessException;
import com.socialuni.social.exception.SocialParamsException;
import com.socialuni.social.model.model.QO.community.talk.SocialTalkPostQO;
import com.socialuni.social.model.model.RO.community.talk.SocialTalkRO;
import com.socialuni.social.sdk.constant.CommonConst;
import com.socialuni.social.sdk.constant.TalkOperateType;
import com.socialuni.social.sdk.constant.UserType;
import com.socialuni.social.sdk.domain.report.ReportDomain;
import com.socialuni.social.sdk.entity.content.SocialContentAddEntity;
import com.socialuni.social.sdk.factory.SocialTalkROFactory;
import com.socialuni.social.sdk.factory.TalkImgDOFactory;
import com.socialuni.social.sdk.manage.talk.SocialTalkCreateManage;
import com.socialuni.social.sdk.model.TalkAddValidateRO;
import com.socialuni.social.sdk.repository.TagRepository;
import com.socialuni.social.sdk.repository.TalkImgRepository;
import com.socialuni.social.sdk.repository.TalkRepository;
import com.socialuni.social.sdk.repository.TalkTagRepository;
import com.socialuni.social.sdk.service.tag.TagService;
import com.socialuni.social.sdk.utils.DistrictStoreUtils;
import com.socialuni.social.sdk.utils.TalkStore;
import com.socialuni.social.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
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
    TalkStore talkStore;
    @Resource
    TalkTagRepository talkTagRepository;
    @Resource
    TalkImgRepository talkImgRepository;
    @Resource
    SocialContentAddEntity socialContentAddEntity;
    @Resource
    SocialTalkCreateManage socialTalkCreateManage;

    public SocialTalkRO postTalk(UserDO mienUser, SocialTalkPostQO talkPostQO) {
        socialContentAddEntity.paramsValidate(mienUser, talkPostQO);
        TalkAddValidateRO talkAddValidateRO = this.paramsValidate(mienUser, talkPostQO);
        TalkDO talkDO = this.saveEntity(mienUser, talkPostQO, talkAddValidateRO.getDistrict(), talkAddValidateRO.getTags());
        reportDomain.checkKeywordsCreateReport(talkDO);
        SocialTalkRO socialTalkRO = SocialTalkROFactory.getTalkRO(talkDO, mienUser);
        return socialTalkRO;
    }


    public TalkAddValidateRO paramsValidate(UserDO mineUser, SocialTalkPostQO talkVO) {
        String content = talkVO.getContent();

        if (StringUtils.isEmpty(content) && CollectionUtils.isEmpty(talkVO.getImgs())) {
            throw new SocialParamsException("不能发布文字和图片均为空的动态");
        }
        if (content.length() > 200) {
            throw new SocialParamsException("动态最多支持200个字，请精简动态内容");
        }

        // 查询的时候筛选
        //系统管理员则不校验规则
        if (!UserType.system.equals(mineUser.getType())) {
            Date curDate = new Date();
            Date oneMinuteBefore = new Date(curDate.getTime() - DateTimeType.minute);
            //1分钟内不能发超过1条
            Integer minuteCount = talkRepository.countByUserIdAndCreateTimeBetween(mineUser.getId(), oneMinuteBefore, curDate);
            if (minuteCount > 0) {
                log.info("1分钟最多发布1条动态，请稍后再试:+" + content);
                throw new SocialBusinessException("1分钟最多发布1条动态，请稍后再试");
            }
            Date tenMinuteBefore = new Date(curDate.getTime() - 10L * DateTimeType.minute);
            Integer tenMinuteBeforeCount = talkRepository.countByUserIdAndCreateTimeBetween(mineUser.getId(), tenMinuteBefore, curDate);
            if (tenMinuteBeforeCount > 2) {
                log.info("10分钟最多发布3条动态，请稍后再试:+" + content);
                throw new SocialBusinessException("10分钟最多发布3条动态，请稍后再试");
            }
            //每天0点到现在不能发布超过10条
            //获取当天0点
            Date zero = DateUtils.getTodayZeroDate();
            //10分钟内不能发超过5条
            //1天内不能发超过10条
            Integer oneDayBeforeCount = talkRepository.countByUserIdAndCreateTimeBetween(mineUser.getId(), zero, curDate);
            if (oneDayBeforeCount > 9) {
                log.info("1天最多发布10条动态，请稍后再试:+" + content);
                throw new SocialBusinessException("1天最多发布10条动态，请稍后再试");
            }
        }

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
        List<TagDO> list = tagService.checkAndUpdateTagCount(mineUser, tagIds, TalkOperateType.talkAdd, talkVisibleGender);
        TalkAddValidateRO talkAddValidateRO = new TalkAddValidateRO(districtDO, list);
        return talkAddValidateRO;
    }

    public TalkDO saveEntity(UserDO userDO, SocialTalkPostQO socialTalkPostQO, DistrictDO district, List<TagDO> tags) {
        String talkGender = socialTalkPostQO.getVisibleGender();
        //不为全部，添加默认标签
        if (!talkGender.equals(GenderType.all)) {
            //添加默认标签，解决查询没tag查询不出来的问题
            if (talkGender.equals(GenderType.girl)) {
                //这里一定有值，没值就是创建开发者的时候没创建tag，修复逻辑
                TagDO genderTag = tagRepository.findFirstByName(GenderType.girlTagName);
                tags.add(genderTag);
            } else if (talkGender.equals(GenderType.boy)) {
                //这里一定有值，没值就是创建开发者的时候没创建tag，修复逻辑
                TagDO genderTag = tagRepository.findFirstByName(GenderType.boyTagName);
                tags.add(genderTag);
            }
            //还差男生的没写
        }

        TalkDO talkDO = socialTalkCreateManage.createTalkDO(userDO, socialTalkPostQO, district);

        List<SocialTalkTagDO> list = new ArrayList<>();

        for (TagDO tagDO : tags) {
            SocialTalkTagDO socialTalkTagDO = new SocialTalkTagDO();
            socialTalkTagDO.setTagId(tagDO.getId());
            socialTalkTagDO.setTalkId(talkDO.getId());
            list.add(socialTalkTagDO);
        }
        talkTagRepository.saveAll(list);

        List<SocialTalkImgDO> imgDOS = TalkImgDOFactory.newTalkImgDOS(socialTalkPostQO.getImgs());

        for (SocialTalkImgDO imgDO : imgDOS) {
            imgDO.setTalkId(talkDO.getId());
        }

        talkImgRepository.saveAll(imgDOS);
        return talkDO;
    }
}
