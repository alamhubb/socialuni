package com.socialuni.demo.socialuni;


import com.socialuni.api.feignAPI.SocialuniTalkAPI;
import com.socialuni.api.model.QO.talk.CenterHomeTabTalkQueryQO;
import com.socialuni.api.model.QO.talk.CenterTalkIdQO;
import com.socialuni.api.model.QO.talk.CenterUserTalkQueryQO;
import com.socialuni.api.model.RO.talk.CenterTalkRO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.QO.community.talk.SocialTalkPostQO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
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
    public ResultRO<CenterTalkRO> postTalk(SocialTalkPostQO talkPostQO) {
        return socialuniTalkAPI.postTalk(talkPostQO);
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