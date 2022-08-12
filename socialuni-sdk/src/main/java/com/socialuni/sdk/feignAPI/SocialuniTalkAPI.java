package com.socialuni.sdk.feignAPI;

import com.socialuni.sdk.model.QO.community.talk.SocialTalkPostQO;
import com.socialuni.sdk.model.QO.talk.CenterHomeTabTalkQueryQO;
import com.socialuni.sdk.model.QO.talk.CenterTalkIdQO;
import com.socialuni.sdk.model.QO.talk.CenterUserTalkQueryQO;
import com.socialuni.sdk.model.RO.talk.CenterTalkRO;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("talk")
@FeignClient(name = "talk", url = "${socialuni.server-url:https://api.socialuni.cn}")
public interface SocialuniTalkAPI {
    @GetMapping("queryTalks")
    ResultRO<List<CenterTalkRO>> queryTalks();

    @PostMapping("queryTalks")
    ResultRO<List<CenterTalkRO>> queryTalks(@RequestBody(required = false) CenterHomeTabTalkQueryQO queryQO);

    /**
     * 返回talk是因为三方需要Id
     *
     * @param talkPostQO
     * @return
     */
    @PostMapping("postTalk")
    ResultRO<CenterTalkRO> postTalk(@RequestBody @Valid SocialTalkPostQO talkPostQO);

    @PostMapping("deleteTalk")
    ResultRO<Void> deleteTalk(@RequestBody @Valid CenterTalkIdQO talkIdQO);

    @PostMapping("queryTalkDetail")
    ResultRO<CenterTalkRO> queryTalkDetail(@RequestBody @Valid CenterTalkIdQO talkIdQO);

    @PostMapping("queryUserTalks")
    ResultRO<List<CenterTalkRO>> queryUserTalks(@RequestBody @Valid CenterUserTalkQueryQO queryQO);
}