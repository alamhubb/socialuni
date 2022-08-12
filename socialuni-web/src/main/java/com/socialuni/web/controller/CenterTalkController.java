package com.socialuni.web.controller;

import com.socialuni.sdk.feignAPI.SocialuniTalkAPI;
import com.socialuni.sdk.model.QO.community.talk.SocialTalkPostQO;
import com.socialuni.sdk.model.QO.talk.CenterHomeTabTalkQueryQO;
import com.socialuni.sdk.model.QO.talk.CenterTalkIdQO;
import com.socialuni.sdk.model.QO.talk.CenterUserTalkQueryQO;
import com.socialuni.sdk.model.RO.talk.CenterTalkRO;
import com.socialuni.sdk.serive.CenterTalkService;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

@RestController
public class CenterTalkController implements SocialuniTalkAPI {
    @Resource
    private CenterTalkService centerTalkService;
    @Resource
    private SocialuniTalkAPI socialuniTalkAPI;

    @Resource
    private com.socialuni.sdk.api.SocialuniTalkAPIImpl SocialuniTalkAPIImpl;

    @Override
    public ResultRO<List<CenterTalkRO>> queryTalks() {
        return centerTalkService.queryTalks();
    }

    @Override
    public ResultRO<List<CenterTalkRO>> queryTalks(CenterHomeTabTalkQueryQO queryQO) {
        return centerTalkService.queryTalks(queryQO);
    }

    @Override
    public ResultRO<CenterTalkRO> postTalk(SocialTalkPostQO talkPostQO) {
        return SocialuniTalkAPIImpl.postTalk(talkPostQO);
    }

    @Override
    public ResultRO<Void> deleteTalk(CenterTalkIdQO talkIdQO) {
        return centerTalkService.deleteTalk(talkIdQO);
    }

    @Override
    public ResultRO<CenterTalkRO> queryTalkDetail(CenterTalkIdQO talkIdQO) {
        return centerTalkService.queryTalkDetail(talkIdQO);
    }

    @Override
    public ResultRO<List<CenterTalkRO>> queryUserTalks(CenterUserTalkQueryQO queryQO) {
        return centerTalkService.queryUserTalks(queryQO);
    }
}
