package com.socialuni.center.controller.socialuni;

import com.socialuni.sdk.feignAPI.SocialuniTalkAPI;
import com.socialuni.sdk.model.QO.community.talk.SocialTalkPostQO;
import com.socialuni.sdk.model.QO.talk.CenterHomeTabTalkQueryQO;
import com.socialuni.sdk.model.QO.talk.CenterTalkIdQO;
import com.socialuni.sdk.model.QO.talk.CenterUserTalkQueryQO;
import com.socialuni.sdk.model.RO.talk.CenterTalkRO;
import com.socialuni.sdk.serive.CenterTalkService;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
public class CenterTalkController  {
    @Resource
    private SocialuniTalkAPI socialuniTalkAPI;

    @Resource
    private CenterTalkService socialuniTalkAPIImpl;

    @GetMapping("queryTalks")
    public ResultRO<List<CenterTalkRO>> queryTalks() {
        return socialuniTalkAPIImpl.queryTalks();
    }

    @PostMapping("queryTalks")
    public ResultRO<List<CenterTalkRO>> queryTalks(@RequestBody(required = false) CenterHomeTabTalkQueryQO queryQO) {
        return socialuniTalkAPIImpl.queryTalks(queryQO);
    }

    @PostMapping("postTalk")
    public ResultRO<CenterTalkRO> postTalk(@RequestBody @Valid SocialTalkPostQO talkPostQO){
        return socialuniTalkAPIImpl.postTalk(talkPostQO);
    }

    @PostMapping("deleteTalk")
    public ResultRO<Void> deleteTalk(@RequestBody @Valid CenterTalkIdQO talkIdQO) {
        return socialuniTalkAPIImpl.deleteTalk(talkIdQO);
    }

    @PostMapping("queryTalkDetail")
    public ResultRO<CenterTalkRO> queryTalkDetail(@RequestBody @Valid CenterTalkIdQO talkIdQO){
        return socialuniTalkAPIImpl.queryTalkDetail(talkIdQO);
    }

    @PostMapping("queryUserTalks")
    public ResultRO<List<CenterTalkRO>> queryUserTalks(@RequestBody @Valid CenterUserTalkQueryQO queryQO) {
        return socialuniTalkAPIImpl.queryUserTalks(queryQO);
    }
}
