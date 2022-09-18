package com.socialuni.sdk.logic.domain.talk;

import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.model.QO.community.talk.SocialTalkDeleteQO;
import com.socialuni.sdk.model.QO.talk.SocialuniTalkIdQO;
import com.socialuni.sdk.utils.SocialuniUnionIdUtil;
import com.socialuni.sdk.utils.SocialuniUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author qinkaiyuan
 * @date 2020-05-16 20:11
 */
@Service
@Slf4j
public class SocialuniTalkDeleteDomain {
    @Resource
    private SocialTalkDeleteDomain socialTalkDeleteDomain;

    public void deleteTalk(SocialuniTalkIdQO talkIdQO) {
        Integer talkId = SocialuniUnionIdUtil.getUnionIdByUuidNotNull(talkIdQO.getTalkId());

        SocialuniUserDO mineUser = SocialuniUserUtil.getMineUserNotNull();

        SocialTalkDeleteQO socialTalkDeleteQO = new SocialTalkDeleteQO(talkId);

        socialTalkDeleteDomain.deleteTalk(mineUser, socialTalkDeleteQO);
    }
}
