package com.socialuni.center.web.domain.talk;

import com.socialuni.api.model.QO.talk.CenterTalkIdQO;
import com.socialuni.center.web.utils.CenterUserUtil;
import com.socialuni.center.web.utils.UnionIdDbUtil;
import com.socialuni.sdk.domain.talk.SocialTalkDeleteDomain;
import com.socialuni.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.QO.community.talk.SocialTalkDeleteQO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author qinkaiyuan
 * @date 2020-05-16 20:11
 */
@Service
@Slf4j
public class CenterTalkDeleteDomain {
    @Resource
    private SocialTalkDeleteDomain socialTalkDeleteDomain;

    public void deleteTalk(CenterTalkIdQO talkIdQO) {
        Integer talkId = UnionIdDbUtil.getTalkIdByUnionId(talkIdQO.getTalkId());

        UserDO mineUser = CenterUserUtil.getMineUser();

        SocialTalkDeleteQO socialTalkDeleteQO = new SocialTalkDeleteQO(talkId);

        socialTalkDeleteDomain.deleteTalk(mineUser, socialTalkDeleteQO);
    }
}
