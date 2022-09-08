package com.socialuni.sdk.controller;

import com.socialuni.sdk.feignAPI.SocialuniTalkAPI;
import com.socialuni.sdk.model.QO.community.talk.SocialTalkPostQO;
import com.socialuni.sdk.model.QO.talk.CenterHomeTabTalkQueryQO;
import com.socialuni.sdk.model.QO.talk.CenterTalkIdQO;
import com.socialuni.sdk.model.QO.talk.CenterUserTalkQueryQO;
import com.socialuni.sdk.model.RO.talk.CenterTalkRO;
import com.socialuni.sdk.logic.service.talk.CenterTalkService;
import com.socialuni.social.web.sdk.model.ResultRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class SocialuniTalkController implements SocialuniTalkAPI {
    @Resource
    private SocialuniTalkAPI socialuniTalkAPI;

    @Resource
    private CenterTalkService socialuniTalkAPIImpl;

    @Override
    public ResultRO<List<CenterTalkRO>> queryTalks() {
        return socialuniTalkAPIImpl.queryTalks();
    }

    @Override
    public ResultRO<List<CenterTalkRO>> queryTalks(CenterHomeTabTalkQueryQO queryQO) {
        return socialuniTalkAPIImpl.queryTalks(queryQO);
    }

    @Override
    public ResultRO<CenterTalkRO> postTalk(SocialTalkPostQO talkPostQO) {
        return socialuniTalkAPIImpl.postTalk(talkPostQO);
    }

    @Override
    public ResultRO<Void> deleteTalk(CenterTalkIdQO talkIdQO) {
        return socialuniTalkAPIImpl.deleteTalk(talkIdQO);
    }

    @Override
    public ResultRO<CenterTalkRO> queryTalkDetail(String talkId) {
        return socialuniTalkAPIImpl.queryTalkDetail(talkId);
    }

    @Override
    public ResultRO<List<CenterTalkRO>> queryUserTalks(CenterUserTalkQueryQO queryQO) {
        return socialuniTalkAPIImpl.queryUserTalks(queryQO);
    }
}
