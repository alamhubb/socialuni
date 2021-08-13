package com.socialuni.center.web.domain.talk;

import com.socialuni.api.model.RO.talk.CenterTalkRO;
import com.socialuni.center.web.factory.RO.talk.CenterTalkROFactory;
import com.socialuni.center.web.utils.CenterUserUtil;
import com.socialuni.center.web.utils.DevAccountUtils;
import com.socialuni.social.sdk.domain.talk.SocialTalkPostDomain;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.QO.community.talk.SocialTalkPostQO;
import com.socialuni.social.model.model.RO.community.talk.SocialTalkRO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class CenterTalkPostDomain {
    @Resource
    SocialTalkPostDomain socialTalkPostDomain;

    public CenterTalkRO postTalk(SocialTalkPostQO talkPostQO) {
        UserDO mineUser = CenterUserUtil.getMineUser();
        talkPostQO.setDevId(DevAccountUtils.getDevId());
        SocialTalkRO socialTalkRO = socialTalkPostDomain.postTalk(mineUser, talkPostQO);
        CenterTalkRO centerTalkRO = CenterTalkROFactory.getTalkRO(socialTalkRO, mineUser);
        return centerTalkRO;
    }
}
