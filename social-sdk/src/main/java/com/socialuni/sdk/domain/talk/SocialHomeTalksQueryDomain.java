package com.socialuni.sdk.domain.talk;

import com.socialuni.sdk.constant.TalkTabType;
import com.socialuni.sdk.exception.SocialParamsException;
import com.socialuni.sdk.model.DO.talk.TalkDO;
import com.socialuni.sdk.model.DO.user.UserDO;
import com.socialuni.social.model.model.QO.community.talk.SocialTalkHomeQueryQO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class SocialHomeTalksQueryDomain {
    @Resource
    private SocialHomeTalksQueryEntity socialHomeTalksQueryEntity;
    @Resource
    private SocialFollowUserTalksQueryEntity socialFollowUserTalksQueryEntity;

    //查询非关注tab的动态列表
    public List<TalkDO> queryHomeTalks(List<Integer> talkIds, SocialTalkHomeQueryQO queryVO, UserDO mineUser) {
        String tabType = queryVO.getTabType();
        if (!TalkTabType.values.contains(tabType)) {
            throw new SocialParamsException("错误的tab类型");
        }
        List<TalkDO> talkDOS;
        //为关注，查询关注
        if ((TalkTabType.follow_type.equals(tabType))) {
            talkDOS = socialFollowUserTalksQueryEntity.queryUserFollowTalks(talkIds, mineUser);
        } else {
            talkDOS = socialHomeTalksQueryEntity.queryHomeTalks(talkIds, queryVO, mineUser);
        }
        return talkDOS;
    }
}
