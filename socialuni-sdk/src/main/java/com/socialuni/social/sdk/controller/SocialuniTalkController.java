package com.socialuni.social.sdk.controller;

import com.socialuni.social.common.model.ResultRO;
import com.socialuni.social.sdk.feignAPI.community.SocialuniTalkAPI;
import com.socialuni.social.sdk.logic.service.talk.SocialuniTalkService;
import com.socialuni.social.sdk.model.QO.community.talk.SocialuniTalkPostQO;
import com.socialuni.social.sdk.model.QO.talk.SocialuniHomeTabTalkQueryQO;
import com.socialuni.social.sdk.model.QO.talk.SocialuniTalkIdQO;
import com.socialuni.social.sdk.model.QO.talk.SocialuniUserTalkQueryQO;
import com.socialuni.social.sdk.model.RO.talk.SocialuniTalkRO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("socialuni/talk")
public class SocialuniTalkController implements SocialuniTalkAPI {
    @Resource
    private SocialuniTalkService socialuniTalkService;

    @Override
    public ResultRO<List<SocialuniTalkRO>> queryTalks() {
        return socialuniTalkService.queryTalks();
    }

    @Override
    public ResultRO<List<SocialuniTalkRO>> queryStickTalks() {
        return socialuniTalkService.queryStickTalks();
    }

    @Override
    public ResultRO<List<SocialuniTalkRO>> queryTalks(SocialuniHomeTabTalkQueryQO queryQO) {
        return socialuniTalkService.queryTalks(queryQO);
    }

    @Override
    public ResultRO<SocialuniTalkRO> postTalk(SocialuniTalkPostQO talkPostQO) {
        return socialuniTalkService.postTalk(talkPostQO);
    }

    @Override
    public ResultRO<Void> deleteTalk(SocialuniTalkIdQO talkIdQO) {
        return socialuniTalkService.deleteTalk(talkIdQO);
    }

    @Override
    public ResultRO<SocialuniTalkRO> queryTalkDetail(String talkId) {
        return socialuniTalkService.queryTalkDetail(talkId);
    }

    @Override
    public ResultRO<List<SocialuniTalkRO>> queryUserTalks(SocialuniUserTalkQueryQO queryQO) {
        return socialuniTalkService.queryUserTalks(queryQO);
    }
}
