package com.qingchi.web.controller.oldVersion;


import com.qingchi.web.model.old.HomeTabTalkQueryQO;
import com.qingchi.web.service.talk.TalkService;
import com.socialuni.api.model.QO.talk.CenterHomeTabTalkQueryQO;
import com.socialuni.api.model.QO.talk.CenterTalkIdQO;
import com.socialuni.api.model.RO.talk.CenterTalkRO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.QO.community.talk.SocialTalkPostQO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("talk")
public class OldTalkController {
    @Resource
    TalkService talkService;

    @PostMapping("queryTalkDetail")
    ResultRO<CenterTalkRO> queryTalkDetail(@Valid @NotBlank String talkId) {
        CenterTalkIdQO talkIdQO = new CenterTalkIdQO(talkId);
        return talkService.queryTalkDetail(talkIdQO);
    }

    @PostMapping("addTalk")
    ResultRO<Void> addTalk(@RequestBody @Valid SocialTalkPostQO talkPostQO) {
        return talkService.postTalk(talkPostQO);
    }

    @PostMapping("queryTalks")
    ResultRO<List<CenterTalkRO>> queryHomeTabTalks(@RequestBody @Valid HomeTabTalkQueryQO queryQO) {
        //兼容代码
        return talkService.queryHomeTabTalks(queryQO.toCenterQO());
    }
}