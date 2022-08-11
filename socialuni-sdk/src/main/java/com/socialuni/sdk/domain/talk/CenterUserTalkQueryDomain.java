package com.socialuni.sdk.domain.talk;

import com.socialuni.sdk.model.QO.talk.CenterUserTalkQueryQO;
import com.socialuni.sdk.model.RO.talk.CenterTalkRO;
import com.socialuni.sdk.factory.RO.talk.CenterTalkROFactory;
import com.socialuni.sdk.utils.CenterUserUtil;
import com.socialuni.sdk.utils.UnionIdDbUtil;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.model.QO.community.talk.SocialUserTalkQueryQO;
import com.socialuni.sdk.model.RO.community.talk.SocialTalkRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2020-05-16 20:11
 */
@Service
public class CenterUserTalkQueryDomain {
    @Resource
    SocialUserTalkQueryDomain socialUserTalkQueryDomain;

    public List<CenterTalkRO> queryUserTalks(CenterUserTalkQueryQO queryQO) {

        Integer userId = UnionIdDbUtil.getUserUnionIdByUidNotNull(queryQO.getUserId());

        //获取自己的user
        SocialUserDO mineUser = CenterUserUtil.getMineUserAllowNull();

        List<Integer> talkIds = UnionIdDbUtil.getContentIdsByTalkUnionIds(queryQO.getTalkIds());

        SocialUserTalkQueryQO socialUserTalkQueryQO = new SocialUserTalkQueryQO(userId, talkIds);

        List<SocialTalkRO> socialTalkROS = socialUserTalkQueryDomain.queryUserTalks(socialUserTalkQueryQO, mineUser);

        List<CenterTalkRO> centerTalkROS = CenterTalkROFactory.getTalkROS(socialTalkROS, mineUser);

        return centerTalkROS;
    }
}
