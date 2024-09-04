package com.socialuni.social.community.sdk.logic.domain.talk;

import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.community.sdk.model.QO.talk.SocialTalkDeleteQO;
import com.socialuni.social.community.sdk.model.QO.talk.SocialuniTalkIdQO;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
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
        Long talkId = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(talkIdQO.getTalkId());

        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();

        SocialTalkDeleteQO socialTalkDeleteQO = new SocialTalkDeleteQO(talkId);

        socialTalkDeleteDomain.deleteTalk(mineUser, socialTalkDeleteQO);
    }
}
