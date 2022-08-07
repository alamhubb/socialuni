package com.socialuni.center.web.feignAPI;

import com.socialuni.center.web.model.QO.community.talk.SocialTalkPostQO;
import com.socialuni.center.web.model.QO.talk.CenterHomeTabTalkQueryQO;
import com.socialuni.center.web.model.QO.talk.CenterTalkIdQO;
import com.socialuni.center.web.model.QO.talk.CenterUserTalkQueryQO;
import com.socialuni.center.web.model.RO.talk.CenterTalkRO;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Map;

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
    ResultRO<CenterTalkRO> postTalk(URI baseUrl, @RequestHeader Map<String, Object> headerMap, @RequestBody @Valid SocialTalkPostQO talkPostQO);

    @PostMapping("deleteTalk")
    ResultRO<Void> deleteTalk(@RequestBody @Valid CenterTalkIdQO talkIdQO);

    @PostMapping("queryTalkDetail")
    ResultRO<CenterTalkRO> queryTalkDetail(@RequestBody @Valid CenterTalkIdQO talkIdQO);

    @PostMapping("queryUserTalks")
    ResultRO<List<CenterTalkRO>> queryUserTalks(@RequestBody @Valid CenterUserTalkQueryQO queryQO);
}