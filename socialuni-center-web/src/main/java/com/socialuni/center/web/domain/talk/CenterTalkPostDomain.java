package com.socialuni.center.web.domain.talk;

import com.socialuni.api.model.RO.talk.CenterTalkRO;
import com.socialuni.center.web.factory.RO.talk.CenterTalkROFactory;
import com.socialuni.center.web.utils.CenterUserUtil;
import com.socialuni.cloud.config.SocialAppEnv;
import com.socialuni.social.constant.DateTimeType;
import com.socialuni.social.entity.model.DO.tag.TagDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.exception.SocialBusinessException;
import com.socialuni.social.exception.SocialParamsException;
import com.socialuni.social.model.model.QO.community.talk.SocialTalkPostQO;
import com.socialuni.social.model.model.RO.community.talk.SocialTalkRO;
import com.socialuni.social.sdk.constant.UserType;
import com.socialuni.social.sdk.domain.talk.SocialTalkPostDomain;
import com.socialuni.social.sdk.entity.content.SocialContentAddEntity;
import com.socialuni.social.sdk.repository.community.TagRepository;
import com.socialuni.social.sdk.repository.community.TalkRepository;
import com.socialuni.social.sdk.utils.DevAccountUtils;
import com.socialuni.social.sdk.utils.common.BirthdayAgeUtil;
import com.socialuni.social.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
public class CenterTalkPostDomain {
    @Resource
    SocialTalkPostDomain socialTalkPostDomain;
    @Resource
    TagRepository tagRepository;
    @Resource
    TalkRepository talkRepository;
    @Resource
    SocialContentAddEntity socialContentAddEntity;

    public CenterTalkRO postTalk(SocialTalkPostQO talkPostQO) {
        UserDO mineUser = CenterUserUtil.getMineUserAllowNull();

        String content = talkPostQO.getContent();

        //是否包含小于18岁的内容
        if (StringUtils.isNotEmpty(content)) {
            String reg = "\\d+";//定义正则表达式
            //编译正则表达式
            Pattern patten = Pattern.compile(reg);
            // 指定要匹配的字符串
            Matcher matcher = patten.matcher(content);
            //此处find()每次被调用后，会偏移到下一个匹配
            while (matcher.find()) {
                //获取当前匹配的值
                String numStr = matcher.group();
                if (BirthdayAgeUtil.ageBetween10to18Str(numStr)) {
                    throw new SocialBusinessException("禁止发布包含小于18岁未成年的内容");
                }
            }
        }

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
        TagDO devTagDO = tagRepository.findFirstByDevId(devId);

        List<String> tagNames = talkPostQO.getTagNames();
        if (tagNames == null || (tagNames.size() == 1 && tagNames.get(0).equals("添加话题"))) {
            tagNames = new ArrayList<>();
        }
        tagNames.add(devTagDO.getName());
        talkPostQO.setTagNames(tagNames);

        socialContentAddEntity.paramsValidate(mineUser, talkPostQO);
        SocialTalkRO socialTalkRO = socialTalkPostDomain.postTalk(mineUser, talkPostQO);

        CenterTalkRO centerTalkRO = CenterTalkROFactory.getTalkRO(socialTalkRO, mineUser);
        return centerTalkRO;
    }
}
