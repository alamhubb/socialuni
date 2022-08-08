package com.socialuni.center.web.entity.talk;

import com.socialuni.center.web.repository.community.TalkRepository;
import com.socialuni.center.web.store.SocialHomeTalkQueryStore;
import com.socialuni.center.web.store.TalkQueryStore;
import com.socialuni.center.web.model.DO.talk.SocialTalkDO;
import com.socialuni.center.web.model.DO.user.SocialUserDO;
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
    public List<SocialTalkDO> queryUserFollowTalks(List<Integer> talkIds, SocialUserDO mineUser) {
        if (mineUser == null) {
            return new ArrayList<>();
        }
        List<SocialTalkDO> talkDOS = talkQueryStore.queryTalksTop10ByUserFollow(talkIds, mineUser.getUnionId());
        return talkDOS;
    }
}
