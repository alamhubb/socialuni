package com.socialuni.center.web.controller;

import com.socialuni.api.feignAPI.SocialuniTalkAPI;
import com.socialuni.api.model.QO.talk.CenterHomeTabTalkQueryQO;
import com.socialuni.api.model.QO.talk.CenterTalkIdQO;
import com.socialuni.api.model.QO.talk.CenterUserTalkQueryQO;
import com.socialuni.api.model.RO.talk.CenterTalkRO;
import com.socialuni.center.web.serive.CenterTalkService;
import com.socialuni.social.model.model.QO.community.talk.SocialTalkPostQO;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class CenterTalkController implements SocialuniTalkAPI {
    @Resource
    private CenterTalkService centerTalkService;

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
        return centerTalkService.postTalk(talkPostQO);
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
