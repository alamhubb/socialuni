package com.socialuni.demo.controller.socialuni;


import com.socialuni.api.feignAPI.SocialuniTalkAPI;
import com.socialuni.api.model.QO.talk.CenterHomeTabTalkQueryQO;
import com.socialuni.api.model.QO.talk.CenterTalkIdQO;
import com.socialuni.api.model.QO.talk.CenterUserTalkQueryQO;
import com.socialuni.api.model.RO.talk.CenterTalkRO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.QO.community.talk.SocialTalkPostQO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("talk")
public class TalkController implements SocialuniTalkAPI {
    @Resource
    SocialuniTalkAPI socialuniTalkAPI;

    @Override
    public ResultRO<List<CenterTalkRO>> queryTalks() {
        return socialuniTalkAPI.queryTalks();
    }

    @Override
    public ResultRO<List<CenterTalkRO>> queryTalks(CenterHomeTabTalkQueryQO queryQO) {
        return socialuniTalkAPI.queryTalks(queryQO);
    }

    @Override
    public ResultRO<CenterTalkRO> postTalk(URI baseUrl, SocialTalkPostQO talkPostQO) {
        return socialuniTalkAPI.postTalk(baseUrl, talkPostQO);
    }

    @Override
    public ResultRO<Void> deleteTalk(CenterTalkIdQO talkIdQO) {
        return socialuniTalkAPI.deleteTalk(talkIdQO);
    }

    @Override
    public ResultRO<CenterTalkRO> queryTalkDetail(CenterTalkIdQO talkIdQO) {
        return socialuniTalkAPI.queryTalkDetail(talkIdQO);
    }

    @Override
    public ResultRO<List<CenterTalkRO>> queryUserTalks(CenterUserTalkQueryQO queryQO) {
        return socialuniTalkAPI.queryUserTalks(queryQO);
    }

}