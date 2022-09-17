package com.socialuni.sdk.logic.domain.talk;

import com.socialuni.sdk.config.SocialuniAppConfig;
import com.socialuni.sdk.config.SocialuniSystemConst;
import com.socialuni.sdk.constant.UserType;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.model.QO.community.talk.SocialuniTalkPostQO;
import com.socialuni.sdk.dao.repository.community.TagRepository;
import com.socialuni.sdk.dao.repository.community.TalkRepository;
import com.socialuni.sdk.model.RO.talk.SocialuniTalkRO;
import com.socialuni.sdk.utils.DateUtils;
import com.socialuni.sdk.utils.DevAccountUtils;
import com.socialuni.sdk.utils.SocialuniUserUtil;
import com.socialuni.sdk.constant.socialuni.DateTimeType;
import com.socialuni.sdk.utils.model.DO.SocialuniUserExpandDOUtil;
import com.socialuni.social.web.sdk.exception.SocialBusinessException;
import com.socialuni.social.web.sdk.exception.SocialParamsException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@Component
public class SocialuniTalkPostDomain {
    @Resource
    SocialTalkPostDomain socialTalkPostDomain;
    @Resource
    TagRepository tagRepository;
    @Resource
    TalkRepository talkRepository;

    public SocialuniTalkRO postTalk(SocialuniTalkPostQO talkPostQO) {
        SocialuniUserDO mineUser = SocialuniUserUtil.getMineUserNotNull();

        String content = talkPostQO.getContent();

        if (StringUtils.isEmpty(content) && CollectionUtils.isEmpty(talkPostQO.getImgs())) {
            throw new SocialParamsException("不能发布文字和图片均为空的动态");
        }
        if (content.length() > 200) {
            throw new SocialParamsException("动态最多支持200个字，请精简动态内容");
        }

        // 查询的时候筛选
        //系统管理员则不校验规则,生产环境才校验
        if (SocialuniSystemConst.getIsProdEnv() && !UserType.system.equals(mineUser.getType())) {
            Date curDate = new Date();
            Date oneMinuteBefore = new Date(curDate.getTime() - DateTimeType.minute);
            //1分钟内不能发超过1条
            Integer minuteCount = talkRepository.countByUserIdAndCreateTimeBetween(mineUser.getUnionId(), oneMinuteBefore, curDate);
            if (minuteCount > 0) {
                log.info("1分钟最多发布1条动态，请稍后再试:+" + content);
                throw new SocialBusinessException("1分钟最多发布1条动态，请稍后再试");
            }
            Date tenMinuteBefore = new Date(curDate.getTime() - 10L * DateTimeType.minute);
            Integer tenMinuteBeforeCount = talkRepository.countByUserIdAndCreateTimeBetween(mineUser.getUnionId(), tenMinuteBefore, curDate);
            if (tenMinuteBeforeCount > 2) {
                log.info("10分钟最多发布3条动态，请稍后再试:+" + content);
                throw new SocialBusinessException("10分钟最多发布3条动态，请稍后再试");
            }
            //每天0点到现在不能发布超过10条
            //获取当天0点
            Date zero = DateUtils.getTodayZeroDate();
            //10分钟内不能发超过5条
            //1天内不能发超过10条
            Integer oneDayBeforeCount = talkRepository.countByUserIdAndCreateTimeBetween(mineUser.getUnionId(), zero, curDate);
            if (oneDayBeforeCount > 9) {
                log.info("1天最多发布10条动态，请稍后再试:+" + content);
                throw new SocialBusinessException("1天最多发布10条动态，请稍后再试");
            }
        }

        Integer devId = DevAccountUtils.getDevIdNotNull();

        talkPostQO.setDevId(devId);
       /* TagDO devTagDO = tagRepository.findFirstByDevId(devId);

        List<String> tagNames = talkPostQO.getTagNames();
        if (tagNames == null || (tagNames.size() == 1 && tagNames.get(0).equals("添加话题"))) {
            tagNames = new ArrayList<>();
        }
        tagNames.add(devTagDO.getName());
        talkPostQO.setTagNames(tagNames);*/

        if (SocialuniAppConfig.getAppConfig().getMustSetSchoolCanPost()) {
            SocialuniUserExpandDOUtil.getUserSchoolNameNotNull(SocialuniUserUtil.getMineUserIdNotNull());
        }
        SocialuniTalkRO socialTalkRO = socialTalkPostDomain.postTalk(talkPostQO);

        return socialTalkRO;
    }
}
