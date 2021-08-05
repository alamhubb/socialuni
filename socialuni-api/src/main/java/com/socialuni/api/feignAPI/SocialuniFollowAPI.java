package com.socialuni.api.feignAPI;

import com.socialuni.social.model.model.QO.community.FollowAddVO;
import com.socialuni.social.model.model.RO.ResultRO;
import com.socialuni.social.model.model.RO.user.base.SocialUserFollowDetailRO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RequestMapping("follow")
@FeignClient(name = "follow", url = "${socialuni.server-url:https://api.socialuni.com}")
public interface SocialuniFollowAPI {

    @PostMapping("queryUserFollows")
    ResultRO<Map<String, List<SocialUserFollowDetailRO>>> queryUserFollows();

    @PostMapping("addFollow")
    ResultRO<Void> addFollow(@RequestBody @Valid FollowAddVO addVO);

    @PostMapping("cancelFollow")
    ResultRO<Void> cancelFollow(@RequestBody @Valid FollowAddVO addVO);

}