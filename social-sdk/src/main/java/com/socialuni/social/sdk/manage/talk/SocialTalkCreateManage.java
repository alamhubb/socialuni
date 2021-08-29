package com.socialuni.social.sdk.manage.talk;

import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.entity.model.DO.DistrictDO;
import com.socialuni.social.entity.model.DO.talk.TalkDO;
import com.socialuni.social.sdk.factory.TalkDOFactory;
import com.socialuni.social.model.model.QO.community.talk.SocialTalkPostQO;
import com.socialuni.social.sdk.utils.TalkRedis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class SocialTalkCreateManage {
    @Resource
    TalkRedis talkRedis;

    public TalkDO createTalkDO(UserDO user, SocialTalkPostQO socialTalkPostQO, DistrictDO district) {
        TalkDO talkDO = TalkDOFactory.newTalkDO(user, socialTalkPostQO, district);
        //保存说说
        talkDO = talkRedis.save(talkDO);
        return talkDO;
    }
}
