package com.socialuni.center.web.domain.talk;

import com.socialuni.center.web.model.QO.talk.CenterTalkIdQO;
import com.socialuni.center.web.utils.CenterUserUtil;
import com.socialuni.center.web.utils.UnionIdDbUtil;
import com.socialuni.center.web.domain.talk.SocialTalkDeleteDomain;
import com.socialuni.center.web.model.DO.user.UserDO;
import com.socialuni.center.web.model.QO.community.talk.SocialTalkDeleteQO;
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

        UserDO mineUser = CenterUserUtil.getMineUserNotNull();

        SocialTalkDeleteQO socialTalkDeleteQO = new SocialTalkDeleteQO(talkId);

        socialTalkDeleteDomain.deleteTalk(mineUser, socialTalkDeleteQO);
    }
}
