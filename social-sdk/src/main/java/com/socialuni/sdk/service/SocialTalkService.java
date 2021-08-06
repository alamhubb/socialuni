package com.socialuni.sdk.service;

import com.socialuni.sdk.domain.talk.SocialTalkDeleteDomain;
import com.socialuni.sdk.domain.talk.SocialTalkPostDomain;
import com.socialuni.sdk.model.DO.user.UserDO;
import com.socialuni.sdk.utils.SocialUserUtil;
import com.socialuni.social.model.model.QO.community.talk.SocialTalkPostQO;
import com.socialuni.social.model.model.QO.community.talk.SocialTalkDeleteQO;
import com.socialuni.social.model.model.QO.community.talk.SocialHomeTabTalkQueryQO;
import com.socialuni.social.model.model.RO.ResultRO;
import com.socialuni.social.model.model.RO.community.talk.SocialTalkRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SocialTalkService {
    @Resource
    private SocialTalkQueryService socialTalkQueryService;
    @Resource
    SocialTalkPostDomain socialTalkPostDomain;
    @Resource
    SocialTalkDeleteDomain socialTalkDeleteDomain;

    //无参数get请求访问talks，主要为了方便用户体验。
    public ResultRO<List<SocialTalkRO>> queryHomeTalks() {
        return socialTalkQueryService.queryHomeTalks();
    }

    //查询非关注tab的动态列表
    public ResultRO<List<SocialTalkRO>> queryHomeTabTalks(SocialHomeTabTalkQueryQO queryVO) {
        return socialTalkQueryService.queryHomeTabTalks(queryVO);
    }

    public ResultRO<Void> postTalk(SocialTalkPostQO talkPostQO) {
        //校验是否触发关键词，如果触发生成举报，修改动态为预审查，只能用户自己可见
        UserDO mineUser = SocialUserUtil.getMineUser();
        socialTalkPostDomain.postTalk(mineUser, talkPostQO);
        return new ResultRO<>();
    }

    public ResultRO<Void> deleteTalk(SocialTalkDeleteQO talkDeleteQO) {
        //校验是否触发关键词，如果触发生成举报，修改动态为预审查，只能用户自己可见
        UserDO mineUser = SocialUserUtil.getMineUser();
        socialTalkDeleteDomain.deleteTalk(mineUser, talkDeleteQO);
        return new ResultRO<>();
    }


}