package com.socialuni.social.web.controller;

import com.socialuni.social.sdk.service.SocialTalkService;
import com.socialuni.social.model.model.QO.community.talk.*;
import com.socialuni.social.api.model.model.ResultRO;
import com.socialuni.social.model.model.RO.community.talk.SocialTalkRO;
import com.socialuni.social.sdk.url.community.SocialuniTalkUrl;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class SocialTalkController implements SocialuniTalkUrl {
    @Resource
    private SocialTalkService socialTalkService;

    @Override
    public ResultRO<List<SocialTalkRO>> queryHomeTalks() {
        return socialTalkService.queryHomeTalks();
    }

    @Override
    public ResultRO<List<SocialTalkRO>> queryHomeTabTalks(SocialHomeTabTalkQueryQO queryVO) {
        return socialTalkService.queryHomeTabTalks(queryVO);
    }

    @Override
    public ResultRO<Void> postTalk(SocialTalkPostQO talkPostQO) {
        return socialTalkService.postTalk(talkPostQO);
    }

    @Override
    public ResultRO<Void> deleteTalk(SocialTalkDeleteQO talkDeleteQO) {
        return socialTalkService.deleteTalk(talkDeleteQO);
    }

    @Override
    public ResultRO<SocialTalkRO> queryTalkDetail(SocialTalkIdQO socialTalkIdQO) {
        return socialTalkService.queryTalkDetail(socialTalkIdQO);
    }

    @Override
    public ResultRO<List<SocialTalkRO>> queryUserTalks(SocialUserTalkQueryQO queryQO) {
        return socialTalkService.queryUserTalks(queryQO);
    }
}
