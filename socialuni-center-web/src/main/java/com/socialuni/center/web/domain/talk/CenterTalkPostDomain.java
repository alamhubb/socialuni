package com.socialuni.center.web.domain.talk;

import com.socialuni.center.web.model.RO.talk.CenterTalkRO;
import com.socialuni.center.web.factory.RO.talk.CenterTalkROFactory;
import com.socialuni.center.web.utils.CenterUserUtil;
import com.socialuni.cloud.config.SocialAppEnv;
import com.socialuni.social.constant.DateTimeType;
import com.socialuni.center.web.model.DO.user.SocialUserDO;
import com.socialuni.social.exception.SocialBusinessException;
import com.socialuni.social.exception.SocialParamsException;
import com.socialuni.center.web.model.QO.community.talk.SocialTalkPostQO;
import com.socialuni.center.web.model.RO.community.talk.SocialTalkRO;
import com.socialuni.center.web.constant.UserType;
import com.socialuni.center.web.repository.community.TagRepository;
import com.socialuni.center.web.repository.community.TalkRepository;
import com.socialuni.center.web.utils.DevAccountUtils;
import com.socialuni.social.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@Component
public class CenterTalkPostDomain {
    @Resource
    SocialTalkPostDomain socialTalkPostDomain;
    @Resource
    TagRepository tagRepository;
    @Resource
    TalkRepository talkRepository;

    public CenterTalkRO postTalk(SocialTalkPostQO talkPostQO) {
        SocialUserDO mineUser = CenterUserUtil.getMineUserNotNull();

        String content = talkPostQO.getContent();



        if (StringUtils.isEmpty(content) && CollectionUtils.isEmpty(talkPostQO.getImgs())) {
            throw new SocialParamsException("不能发布文字和图片均为空的动态");
        }
        if (content.length() > 200) {
            throw new SocialParamsException("动态最多支持200个字，请精简动态内容");
        }

        // 查询的时候筛选
        //系统管理员则不校验规则,生产环境才校验
        if (SocialAppEnv.getIsProdEnv() && !UserType.system.equals(mineUser.getType())) {
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

        Integer devId = DevAccountUtils.getDevIdNotNull();

        talkPostQO.setDevId(devId);
       /* TagDO devTagDO = tagRepository.findFirstByDevId(devId);

        List<String> tagNames = talkPostQO.getTagNames();
        if (tagNames == null || (tagNames.size() == 1 && tagNames.get(0).equals("添加话题"))) {
            tagNames = new ArrayList<>();
        }
        tagNames.add(devTagDO.getName());
        talkPostQO.setTagNames(tagNames);*/

        SocialTalkRO socialTalkRO = socialTalkPostDomain.postTalk(talkPostQO);

        CenterTalkRO centerTalkRO = CenterTalkROFactory.getTalkRO(socialTalkRO, mineUser);
        return centerTalkRO;
    }
}
