package com.socialuni.api.feignAPI;

import com.socialuni.api.model.QO.talk.CenterHomeTabTalkQueryQO;
import com.socialuni.api.model.RO.talk.CenterTalkRO;
import com.socialuni.social.model.model.QO.community.talk.SocialTalkDeleteQO;
import com.socialuni.social.model.model.QO.community.talk.SocialTalkIdQO;
import com.socialuni.social.model.model.QO.community.talk.SocialTalkPostQO;
import com.socialuni.social.model.model.QO.community.talk.SocialUserTalkQueryQO;
import com.socialuni.social.model.model.RO.ResultRO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("talk")
@FeignClient(name = "talk", url = "${socialuni.server-url:https://api.socialuni.com}")
public interface SocialuniTalkAPI {
    @GetMapping("queryTalks")
    ResultRO<List<CenterTalkRO>> queryHomeTalks();

    @PostMapping("queryTalks")
    ResultRO<List<CenterTalkRO>> queryHomeTabTalks(@RequestBody @Valid CenterHomeTabTalkQueryQO queryVO);

    @PostMapping("postTalk")
    ResultRO<Void> postTalk(@RequestBody @Valid SocialTalkPostQO talkVO);

    @PostMapping("deleteTalk")
    ResultRO<Void> deleteTalk(@RequestBody @Valid SocialTalkDeleteQO talkVO);

    @PostMapping("queryTalkInfo")
    ResultRO<CenterTalkRO> queryTalkDetail(@RequestBody @Valid SocialTalkIdQO socialTalkIdQO);

    @PostMapping("queryUserTalks")
    ResultRO<List<CenterTalkRO>> queryUserTalks(@RequestBody @Valid SocialUserTalkQueryQO queryVO);
}