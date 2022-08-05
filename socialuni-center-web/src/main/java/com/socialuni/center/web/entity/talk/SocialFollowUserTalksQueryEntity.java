package com.socialuni.center.web.entity.talk;

import com.socialuni.center.web.repository.community.TalkRepository;
import com.socialuni.center.web.store.SocialHomeTalkQueryStore;
import com.socialuni.center.web.store.TalkQueryStore;
import com.socialuni.social.entity.model.DO.talk.TalkDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
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
    private SocialHomeTalkQueryStore socialHomeTalkQueryStore;

    //查询用户关注的动态列表
    public List<TalkDO> queryUserFollowTalks(List<Integer> talkIds, UserDO mineUser) {
        if (mineUser == null) {
            return new ArrayList<>();
        }
        List<TalkDO> talkDOS = talkQueryStore.queryTalksTop10ByUserFollow(talkIds, mineUser.getId());
        return talkDOS;
    }
}
