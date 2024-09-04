package com.socialuni.social.community.sdk.logic.domain.talk;

import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.community.sdk.model.QO.talk.SocialUserTalkQueryQO;
import com.socialuni.social.community.sdk.model.QO.talk.SocialuniUserTalkQueryQO;
import com.socialuni.social.community.sdk.model.RO.talk.SocialuniTalkRO;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2020-05-16 20:11
 */
@Service
public class SocialuniUserTalkQueryDomain {
    @Resource
    SocialUserTalkQueryDomain socialUserTalkQueryDomain;

    public List<SocialuniTalkRO> queryUserTalks(SocialuniUserTalkQueryQO queryQO) {

        Long userId = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(queryQO.getUserId());

        //获取自己的user
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserAllowNull();

        List<Long> talkIds = SocialuniUnionIdFacede.getContentIdsByTalkUnionIds(queryQO.getTalkIds());

        SocialUserTalkQueryQO socialUserTalkQueryQO = new SocialUserTalkQueryQO(userId, talkIds);

        List<SocialuniTalkRO> socialTalkROS = socialUserTalkQueryDomain.queryUserTalks(socialUserTalkQueryQO, mineUser);

        return socialTalkROS;
    }
}
