package com.qingchi.web.controller.socialuni;


import com.qingchi.web.service.talk.TalkService;
import com.socialuni.api.model.QO.talk.CenterHomeTabTalkQueryQO;
import com.socialuni.api.model.QO.talk.CenterTalkIdQO;
import com.socialuni.api.model.QO.talk.CenterUserTalkQueryQO;
import com.socialuni.api.model.RO.talk.CenterTalkRO;
import com.socialuni.social.model.model.QO.community.talk.SocialTalkPostQO;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("talk")
public class TalkController {
    @Resource
    TalkService talkService;

    @PostMapping("postTalk")
    ResultRO<Void> postTalk(@RequestBody @Valid SocialTalkPostQO talkPostQO) {
        return talkService.postTalk(talkPostQO);
    }

    @PostMapping("deleteTalk")
    ResultRO<Void> deleteTalk(@RequestBody @Valid CenterTalkIdQO talkIdQO) {
        return talkService.deleteTalk(talkIdQO);
    }

    @PostMapping("queryTalkInfo")
    ResultRO<CenterTalkRO> queryTalkDetail(@RequestBody @Valid CenterTalkIdQO talkIdQO) {
        return talkService.queryTalkDetail(talkIdQO);
    }

    @PostMapping("queryUserTalks")
    ResultRO<List<CenterTalkRO>> queryUserTalks(@RequestBody @Valid CenterUserTalkQueryQO queryQO) {
        return talkService.queryUserTalks(queryQO);
    }
}