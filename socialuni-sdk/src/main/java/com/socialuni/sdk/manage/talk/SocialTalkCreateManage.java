package com.socialuni.sdk.manage.talk;

import com.socialuni.sdk.factory.TalkDOFactory;
import com.socialuni.sdk.model.DO.DistrictDO;
import com.socialuni.sdk.model.DO.talk.SocialTalkDO;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.model.QO.community.talk.SocialTalkPostQO;
import com.socialuni.sdk.utils.TalkRedis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class SocialTalkCreateManage {
    @Resource
    TalkRedis talkRedis;

    public SocialTalkDO createTalkDO(SocialUserDO user, SocialTalkPostQO socialTalkPostQO, DistrictDO district) {
        SocialTalkDO talkDO = TalkDOFactory.newTalkDO(user, socialTalkPostQO, district);
        //保存说说
        talkDO = talkRedis.save(talkDO);
        return talkDO;
    }
}
