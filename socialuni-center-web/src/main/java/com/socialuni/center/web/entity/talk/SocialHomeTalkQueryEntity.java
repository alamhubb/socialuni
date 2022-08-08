package com.socialuni.center.web.entity.talk;

import com.socialuni.center.web.constant.CommonConst;
import com.socialuni.center.web.constant.TalkTabType;
import com.socialuni.center.web.repository.community.TalkRepository;
import com.socialuni.center.web.store.SocialHomeTalkQueryStore;
import com.socialuni.social.constant.ContentStatus;
import com.socialuni.center.web.model.DO.talk.SocialTalkDO;
import com.socialuni.center.web.model.DO.user.SocialUserDO;
import com.socialuni.social.exception.SocialParamsException;
import com.socialuni.center.web.model.QO.community.talk.SocialHomeTabTalkQueryBO;
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

    public List<SocialTalkDO> queryStickTalks(Integer devId) {
        return talkRepository.findTop2ByStatusAndDevIdAndGlobalTopGreaterThanOrderByGlobalTopDesc(ContentStatus.enable, devId, CommonConst.initNum);
    }

    //查询非关注tab的动态列表
    public List<SocialTalkDO> queryHomeTalks(SocialHomeTabTalkQueryBO queryBO, SocialUserDO mineUser) {
        List<Integer> talkIds = queryBO.getTalkIds();

        List<SocialTalkDO> stickTalks = new ArrayList<>();
        if (TalkTabType.home_type.equals(queryBO.getHomeTabType())) {
            if (talkIds.size() == 0 || (talkIds.size() == 1 && talkIds.get(0).equals(0))) {
                stickTalks = this.queryStickTalks(queryBO.getDevId());
            }
        }

        //话题校验
        List<Integer> tagIds = queryBO.getTagIds();
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
        List<SocialTalkDO> talkDOS = socialHomeTalkQueryStore.queryHomeTalks(queryBO, mineUser);

        stickTalks.addAll(talkDOS);

        /*if (stickTalks.size() > 10) {
            stickTalks = stickTalks.subList(0, 10);
        }*/
//        log.info("queryNotFollowTalks结束1：" + new Date().getTime() / 1000);
        return stickTalks;
    }
}
