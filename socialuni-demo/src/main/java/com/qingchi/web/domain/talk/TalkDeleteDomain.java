package com.qingchi.web.domain.talk;

import com.qingchi.web.utils.TalkUtil;
import com.socialuni.api.feignAPI.SocialuniTalkAPI;
import com.socialuni.api.model.QO.talk.CenterTalkIdQO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.QO.community.talk.SocialTalkDeleteQO;
import com.socialuni.social.sdk.domain.talk.SocialTalkDeleteDomain;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author qinkaiyuan
 * @date 2020-05-16 20:11
 */
@Service
@Slf4j
public class TalkDeleteDomain {
    @Resource
    private SocialTalkDeleteDomain socialTalkDeleteDomain;
    @Resource
    SocialuniTalkAPI socialuniTalkAPI;

    public void deleteTalk(CenterTalkIdQO talkIdQO) {
        // 先确认本条应用是否为本应用的动态
        socialuniTalkAPI.deleteTalk(talkIdQO);

        Integer talkId = TalkUtil.getTalkIdByUnionId(talkIdQO.getTalkId());

        UserDO mineUser = SocialUserUtil.getMineUserAllowNull();

        SocialTalkDeleteQO socialTalkDeleteQO = new SocialTalkDeleteQO(talkId);

        socialTalkDeleteDomain.deleteTalk(mineUser, socialTalkDeleteQO);
    }
}
