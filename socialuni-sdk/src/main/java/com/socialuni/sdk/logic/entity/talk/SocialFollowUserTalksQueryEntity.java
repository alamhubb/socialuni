package com.socialuni.sdk.logic.entity.talk;

import com.socialuni.sdk.dao.repository.community.TalkRepository;
import com.socialuni.sdk.dao.store.TalkQueryStore;
import com.socialuni.sdk.dao.DO.talk.SocialTalkDO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
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
    //查询用户关注的动态列表
    public List<SocialTalkDO> queryUserFollowTalks(List<Integer> talkIds, SocialuniUserDO mineUser) {
        if (mineUser == null) {
            return new ArrayList<>();
        }
        List<SocialTalkDO> talkDOS = talkQueryStore.queryTalksTop10ByUserFollow(talkIds, mineUser.getUnionId());
        return talkDOS;
    }
}