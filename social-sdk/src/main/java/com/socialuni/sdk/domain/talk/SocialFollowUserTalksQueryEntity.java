package com.socialuni.sdk.domain.talk;

import com.socialuni.sdk.model.DO.talk.TalkDO;
import com.socialuni.sdk.model.DO.user.UserDO;
import com.socialuni.sdk.repository.TalkRepository;
import com.socialuni.sdk.store.SocialHomeTalksQueryStore;
import com.socialuni.sdk.store.TalkQueryStore;
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
    private TalkRepository talkRepository;
    @Resource
    private TalkQueryStore talkQueryStore;
    @Resource
    private SocialHomeTalksQueryStore socialHomeTalksQueryStore;

    //查询用户关注的动态列表
    public List<TalkDO> queryUserFollowTalks(List<Integer> talkIds, UserDO mineUser) {
        if (mineUser == null) {
            return new ArrayList<>();
        }
        List<TalkDO> talkDOS = talkQueryStore.queryTalksTop10ByUserFollow(talkIds, mineUser.getId());
        return talkDOS;
    }
}
