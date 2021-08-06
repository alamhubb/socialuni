package com.socialuni.sdk.service.talk;

import com.socialuni.sdk.domain.talk.SocialFollowUserTalksQueryEntity;
import com.socialuni.social.model.model.QO.community.talk.SocialHomeTalkQueryQO;
import com.socialuni.social.model.model.RO.community.talk.SocialTalkRO;
import com.socialuni.sdk.exception.SocialParamsException;
import com.socialuni.sdk.factory.TalkROFactory;
import com.socialuni.sdk.model.DO.talk.TalkDO;
import com.socialuni.sdk.model.DO.user.UserDO;
import com.socialuni.sdk.repository.TalkRepository;
import com.socialuni.sdk.store.SocialHomeTalkQueryStore;
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
public class SocialQueryHomeTalksService {
    @Resource
    private TalkRepository talkRepository;
    @Resource
    private TalkQueryStore talkQueryStore;
    //    @Resource
//    private TagService tagService;
    @Resource
    private SocialHomeTalkQueryStore socialHomeTalkQueryStore;
    @Resource
    SocialFollowUserTalksQueryEntity socialFollowUserTalksQueryEntity;

    /*public List<TalkDO> queryStickTalks() {
        Integer devId = DevAccountUtils.getDevId();
        if (devId == null) {
            devId = AppConfigConst.qingchiDevId;
        }
        return talkRepository.findTop2ByStatusAndDevIdAndGlobalTopGreaterThanOrderByGlobalTopDesc(ContentStatus.enable, DevAccountUtils.getDevId(), CommonConst.initNum);
    }*/

    //查询非关注tab的动态列表
    public List<SocialTalkRO> queryHomeTalks(SocialHomeTalkQueryQO queryQO, UserDO user) {
//        log.info("queryNotFollowTalks开始1：" + new Date().getTime() / 1000);
        List<TalkDO> stickTalks = new ArrayList<>();
        /*if (talkIds.size() == 1 && talkIds.get(0).equals(0)) {
            stickTalks = this.queryStickTalks();
        }*/

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
        List<TalkDO> talkDOS = socialHomeTalkQueryStore.queryHomeTalks(queryQO, user);

        stickTalks.addAll(talkDOS);

        List<SocialTalkRO> socialTalkROFactories = TalkROFactory.newHomeTalkROs(user, talkDOS, queryQO);
        /*if (stickTalks.size() > 10) {
            stickTalks = stickTalks.subList(0, 10);
        }*/
//        log.info("queryNotFollowTalks结束1：" + new Date().getTime() / 1000);
        return socialTalkROFactories;
    }
}
