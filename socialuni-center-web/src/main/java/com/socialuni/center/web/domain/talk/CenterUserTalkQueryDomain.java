package com.socialuni.center.web.domain.talk;

import com.socialuni.center.web.model.QO.talk.CenterUserTalkQueryQO;
import com.socialuni.center.web.model.RO.talk.CenterTalkRO;
import com.socialuni.center.web.factory.RO.talk.CenterTalkROFactory;
import com.socialuni.center.web.utils.CenterUserUtil;
import com.socialuni.center.web.utils.UnionIdDbUtil;
import com.socialuni.center.web.model.DO.user.SocialUserDO;
import com.socialuni.center.web.model.QO.community.talk.SocialUserTalkQueryQO;
import com.socialuni.center.web.model.RO.community.talk.SocialTalkRO;
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
