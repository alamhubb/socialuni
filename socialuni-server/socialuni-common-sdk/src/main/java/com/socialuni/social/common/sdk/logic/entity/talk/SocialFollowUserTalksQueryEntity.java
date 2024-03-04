package com.socialuni.social.common.sdk.logic.entity.talk;

import com.socialuni.social.community.sdk.dao.DO.SocialuniTalkDO;
import com.socialuni.social.common.sdk.dao.store.TalkQueryStore;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
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
    private TalkQueryStore talkQueryStore;

    //查询用户关注的动态列表
    public List<?  extends SocialuniTalkDO>  queryUserFollowTalks(List<Integer> talkIds, SocialuniUserDo mineUser) {
        if (mineUser == null) {
            return new ArrayList<>();
        }
        List<?  extends SocialuniTalkDO>  talkDOS = talkQueryStore.queryTalksTop10ByUserFollow(talkIds, mineUser.getUnionId());
        return talkDOS;
    }
}
