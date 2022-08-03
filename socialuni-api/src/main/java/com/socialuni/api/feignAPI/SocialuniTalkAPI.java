package com.socialuni.api.feignAPI;

import com.socialuni.api.model.QO.talk.CenterHomeTabTalkQueryQO;
import com.socialuni.api.model.QO.talk.CenterTalkIdQO;
import com.socialuni.api.model.QO.talk.CenterUserTalkQueryQO;
import com.socialuni.api.model.RO.talk.CenterTalkRO;
import com.socialuni.social.model.model.QO.community.talk.SocialTalkPostQO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.RO.community.talk.SocialTalkRO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.net.URI;
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
     * @param talkPostQO
     * @return
     */
    @PostMapping("postTalk")
    ResultRO<SocialTalkRO> postTalk(@RequestBody @Valid SocialTalkPostQO talkPostQO);

    @PostMapping("deleteTalk")
    ResultRO<Void> deleteTalk(@RequestBody @Valid CenterTalkIdQO talkIdQO);

    @PostMapping("queryTalkDetail")
    ResultRO<CenterTalkRO> queryTalkDetail(@RequestBody @Valid CenterTalkIdQO talkIdQO);

    @PostMapping("queryUserTalks")
    ResultRO<List<CenterTalkRO>> queryUserTalks(@RequestBody @Valid CenterUserTalkQueryQO queryQO);
}