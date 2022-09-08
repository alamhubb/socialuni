package com.socialuni.sdk.controller;

import com.socialuni.sdk.feignAPI.SocialuniTalkAPI;
import com.socialuni.sdk.logic.service.talk.SocialuniTalkService;
import com.socialuni.sdk.model.QO.community.talk.SocialuniTalkPostQO;
import com.socialuni.sdk.model.QO.talk.SocialuniHomeTabTalkQueryQO;
import com.socialuni.sdk.model.QO.talk.SocialuniTalkIdQO;
import com.socialuni.sdk.model.QO.talk.SocialuniUserTalkQueryQO;
import com.socialuni.sdk.model.RO.talk.SocialuniTalkRO;
import com.socialuni.social.web.sdk.model.ResultRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class SocialuniTalkController implements SocialuniTalkAPI {
    @Resource
    private SocialuniTalkAPI socialuniTalkAPI;

    @Resource
    private SocialuniTalkService socialuniTalkAPIImpl;

    @Override
    public ResultRO<List<SocialuniTalkRO>> queryTalks() {
        return socialuniTalkAPIImpl.queryTalks();
    }

    @Override
    public ResultRO<List<SocialuniTalkRO>> queryTalks(SocialuniHomeTabTalkQueryQO queryQO) {
        return socialuniTalkAPIImpl.queryTalks(queryQO);
    }

    @Override
    public ResultRO<SocialuniTalkRO> postTalk(SocialuniTalkPostQO talkPostQO) {
        return socialuniTalkAPIImpl.postTalk(talkPostQO);
    }

    @Override
    public ResultRO<Void> deleteTalk(SocialuniTalkIdQO talkIdQO) {
        return socialuniTalkAPIImpl.deleteTalk(talkIdQO);
    }

    @Override
    public ResultRO<SocialuniTalkRO> queryTalkDetail(String talkId) {
        return socialuniTalkAPIImpl.queryTalkDetail(talkId);
    }

    @Override
    public ResultRO<List<SocialuniTalkRO>> queryUserTalks(SocialuniUserTalkQueryQO queryQO) {
        return socialuniTalkAPIImpl.queryUserTalks(queryQO);
    }
}
