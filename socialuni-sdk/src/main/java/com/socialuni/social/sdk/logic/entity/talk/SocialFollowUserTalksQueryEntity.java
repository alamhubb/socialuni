package com.socialuni.social.sdk.logic.entity.talk;

import com.socialuni.social.community.sdk.model.SocialuniTalkModel;
import com.socialuni.social.user.sdk.model.SocialuniUserModel;
import com.socialuni.social.community.sdk.api.TalkApi;
import com.socialuni.social.sdk.dao.store.TalkQueryStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2020-05-16 20:11
 */
@Service
@Slf4j
public class SocialFollowUserTalksQueryEntity {
    @Resource
    private TalkApi talkApi;
    @Resource
    private TalkQueryStore talkQueryStore;

    //查询用户关注的动态列表
    public List<?  extends SocialuniTalkModel>  queryUserFollowTalks(List<Integer> talkIds, SocialuniUserModel mineUser) {
        if (mineUser == null) {
            return new ArrayList<>();
        }
        List<?  extends SocialuniTalkModel>  talkDOS = talkQueryStore.queryTalksTop10ByUserFollow(talkIds, mineUser.getUnionId());
        return talkDOS;
    }
}
