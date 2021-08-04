package com.socialuni.sdk.domain.talk;

import com.socialuni.sdk.exception.SocialParamsException;
import com.socialuni.sdk.model.DO.talk.TalkDO;
import com.socialuni.sdk.model.DO.user.UserDO;
import com.socialuni.sdk.store.SocialHomeTalksQueryStore;
import com.socialuni.social.model.model.QO.community.talk.SocialTalkHomeQueryQO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class SocialHomeTalksQueryEntity {
    @Resource
    private SocialHomeTalksQueryStore socialHomeTalksQueryStore;

    //查询非关注tab的动态列表
    public List<TalkDO> queryHomeTalks(List<Integer> talkIds, SocialTalkHomeQueryQO queryVO, UserDO mineUser) {
//        log.info("queryNotFollowTalks开始1：" + new Date().getTime() / 1000);
        List<TalkDO> stickTalks = new ArrayList<>();
        /*if (talkIds.size() == 1 && talkIds.get(0).equals(0)) {
            stickTalks = this.queryStickTalks();
        }*/

        //话题校验
        List<Integer> tagIds = queryVO.getTagIds();
        if (tagIds != null && tagIds.size() > 3) {
//            return new ResultRO<>("最多同时筛选3个话题");
            throw new SocialParamsException("最多同时筛选3个话题");
        }

        //更新话题使用次数
//        ResultRO<Set<TagDO>> resultRO = tagService.checkAndUpdateTagCount(user, tagIds, TalkOperateType.talkQuery, talkVisibleGender);
//        if (resultRO.hasError()) {
//            return new ResultRO<>(resultRO.getErrorMsg());
//        }

        //用户的性别，展示的talk行呗
        List<TalkDO> talkDOS = socialHomeTalksQueryStore.queryHomeTalks(talkIds, queryVO, mineUser);

        stickTalks.addAll(talkDOS);

        /*if (stickTalks.size() > 10) {
            stickTalks = stickTalks.subList(0, 10);
        }*/
//        log.info("queryNotFollowTalks结束1：" + new Date().getTime() / 1000);
        return talkDOS;
    }
}
