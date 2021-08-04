package com.socialuni.sdk.manage.talk;

import com.socialuni.sdk.model.DO.user.UserDO;
import com.socialuni.sdk.model.DO.DistrictDO;
import com.socialuni.sdk.model.DO.talk.TalkDO;
import com.socialuni.sdk.factory.TalkDOFactory;
import com.socialuni.social.model.model.QO.community.talk.SocialTalkPostQO;
import com.socialuni.sdk.repository.TalkRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class SocialTalkCreateManage {
    @Resource
    TalkRepository talkRepository;

    public TalkDO createTalkDO(UserDO user, SocialTalkPostQO socialTalkPostQO, DistrictDO district) {
        TalkDO talkDO = TalkDOFactory.newTalkDO(user, socialTalkPostQO, district);
        //保存说说
        talkDO = talkRepository.save(talkDO);
        return talkDO;
    }
}
