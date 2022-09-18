package com.socialuni.sdk.logic.domain.talk;

import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.model.QO.community.talk.SocialUserTalkQueryQO;
import com.socialuni.sdk.model.QO.talk.SocialuniUserTalkQueryQO;
import com.socialuni.sdk.model.RO.talk.SocialuniTalkRO;
import com.socialuni.sdk.utils.SocialuniUserUtil;
import com.socialuni.sdk.utils.SocialuniUnionIdUtil;
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

        Integer userId = SocialuniUnionIdUtil.getUnionIdByUuidNotNull(queryQO.getUserId());

        //获取自己的user
        SocialuniUserDO mineUser = SocialuniUserUtil.getMineUserAllowNull();

        List<Integer> talkIds = SocialuniUnionIdUtil.getContentIdsByTalkUnionIds(queryQO.getTalkIds());

        SocialUserTalkQueryQO socialUserTalkQueryQO = new SocialUserTalkQueryQO(userId, talkIds);

        List<SocialuniTalkRO> socialTalkROS = socialUserTalkQueryDomain.queryUserTalks(socialUserTalkQueryQO, mineUser);

        return socialTalkROS;
    }
}
