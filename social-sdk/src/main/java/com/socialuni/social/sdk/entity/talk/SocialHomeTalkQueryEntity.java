package com.socialuni.social.sdk.entity.talk;

import com.socialuni.social.constant.ContentStatus;
import com.socialuni.social.entity.model.DO.talk.TalkDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.exception.SocialParamsException;
import com.socialuni.social.model.model.QO.community.talk.SocialHomeTabTalkQueryBO;
import com.socialuni.social.sdk.constant.CommonConst;
import com.socialuni.social.sdk.repository.TalkRepository;
import com.socialuni.social.sdk.store.SocialHomeTalkQueryStore;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class SocialHomeTalkQueryEntity {
    @Resource
    private TalkRepository talkRepository;
    @Resource
    private SocialHomeTalkQueryStore socialHomeTalkQueryStore;

    public List<TalkDO> queryStickTalks(Integer devId) {
        return talkRepository.findTop2ByStatusAndDevIdAndGlobalTopGreaterThanOrderByGlobalTopDesc(ContentStatus.enable, devId, CommonConst.initNum);
    }

    //查询非关注tab的动态列表
    public List<TalkDO> queryHomeTalks(SocialHomeTabTalkQueryBO queryQO, UserDO mineUser) {
        List<Integer> talkIds = queryQO.getTalkIds();

        List<TalkDO> stickTalks = new ArrayList<>();
        if (talkIds.size() == 0 || (talkIds.size() == 1 && talkIds.get(0).equals(0))) {
            stickTalks = this.queryStickTalks(queryQO.getDevId());
        }

        //话题校验
        List<Integer> tagIds = queryQO.getTagIds();
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
        List<TalkDO> talkDOS = socialHomeTalkQueryStore.queryHomeTalks(queryQO, mineUser);

        stickTalks.addAll(talkDOS);

        /*if (stickTalks.size() > 10) {
            stickTalks = stickTalks.subList(0, 10);
        }*/
//        log.info("queryNotFollowTalks结束1：" + new Date().getTime() / 1000);
        return stickTalks;
    }
}
