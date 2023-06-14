package com.socialuni.social.sdk.logic.domain.talk;

import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.community.sdk.dao.DO.SocialuniTalkDO;
import com.socialuni.social.community.sdk.repository.TalkRepository;
import com.socialuni.social.common.sdk.constant.UserType;
import com.socialuni.social.common.api.constant.DateTimeType;
import com.socialuni.social.sdk.logic.domain.report.SoicialuniSystemPreCheckReportDomainDOUtil;
import com.socialuni.social.sdk.logic.factory.SocialTalkROFactory;
import com.socialuni.social.sdk.model.QO.community.talk.SocialuniTalkPostQO;
import com.socialuni.social.sdk.model.RO.talk.SocialuniTalkRO;
import com.socialuni.social.sdk.model.TalkAddValidateRO;
import com.socialuni.social.common.sdk.utils.DateUtils;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.tance.sdk.enumeration.SocialuniSystemConst;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@Component
public class SocialuniTalkPostDomain {
    @Resource
    SocialuniPostTalkDomain socialTalkPostDomain;
    @Resource
    TalkRepository talkApi;

    public SocialuniTalkRO postTalk(SocialuniTalkPostQO talkPostQO) {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();

        String content = talkPostQO.getContent();

        if (content.length() > 200) {
            throw new SocialParamsException("动态最多支持200个字，请精简动态内容");
        }

        // 查询的时候筛选
        //系统管理员则不校验规则,生产环境才校验
        if (SocialuniSystemConst.getIsProdEnv() && !UserType.system.equals(mineUser.getType())) {
            Date curDate = new Date();
            Date oneMinuteBefore = new Date(curDate.getTime() - DateTimeType.minute);
            //1分钟内不能发超过1条
            Integer minuteCount = talkApi.countByUserIdAndCreateTimeBetween(mineUser.getUnionId(), oneMinuteBefore, curDate);
            if (minuteCount > 0) {
                log.info("1分钟最多发布1条动态，请稍后再试:+" + content);
                throw new SocialBusinessException("1分钟最多发布1条动态，请稍后再试");
            }
            Date tenMinuteBefore = new Date(curDate.getTime() - 10L * DateTimeType.minute);
            Integer tenMinuteBeforeCount = talkApi.countByUserIdAndCreateTimeBetween(mineUser.getUnionId(), tenMinuteBefore, curDate);
            if (tenMinuteBeforeCount > 2) {
                log.info("10分钟最多发布3条动态，请稍后再试:+" + content);
                throw new SocialBusinessException("10分钟最多发布3条动态，请稍后再试");
            }
            //每天0点到现在不能发布超过10条
            //获取当天0点
            Date zero = DateUtils.getTodayZeroDate();
            //10分钟内不能发超过5条
            //1天内不能发超过10条
            Integer oneDayBeforeCount = talkApi.countByUserIdAndCreateTimeBetween(mineUser.getUnionId(), zero, curDate);
            if (oneDayBeforeCount > 9) {
                log.info("1天最多发布10条动态，请稍后再试:+" + content);
                throw new SocialBusinessException("1天最多发布10条动态，请稍后再试");
            }
        }

//        Integer devId = DevAccountUtils.getDevIdNotNull();

//        talkPostQO.setDevId(devId);
       /* TagDO devTagDO = tagRepository.findFirstByDevId(devId);

        List<String> tagNames = talkPostQO.getTagNames();
        if (tagNames == null || (tagNames.size() == 1 && tagNames.get(0).equals("添加话题"))) {
            tagNames = new ArrayList<>();
        }
        tagNames.add(devTagDO.getName());
        talkPostQO.setTagNames(tagNames);*/

        //校验内容
        TalkAddValidateRO talkAddValidateRO = socialTalkPostDomain.paramsValidate(mineUser, talkPostQO);

        //校验内容是否违规
//        modelContentCheck.checkUserAndContent(addVO.getContent(), requestUser);
        //获取应用对应的话题

        SocialuniTalkDO talkDO = socialTalkPostDomain.saveEntity(mineUser, talkPostQO, talkAddValidateRO.getDistrict(), talkAddValidateRO.getTags(), talkAddValidateRO.getCircle());

        SoicialuniSystemPreCheckReportDomainDOUtil.systemPreCheckReport(talkDO);

        SocialuniTalkRO socialTalkRO = SocialTalkROFactory.getTalkRO(talkDO, mineUser);

        return socialTalkRO;
    }
}
