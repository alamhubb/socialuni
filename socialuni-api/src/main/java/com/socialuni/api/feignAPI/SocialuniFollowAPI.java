package com.socialuni.api.feignAPI;

import com.socialuni.api.model.QO.follow.CenterFollowAddQO;
import com.socialuni.api.model.RO.user.CenterUserFollowDetailRO;
import com.socialuni.social.api.model.ResultRO;
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
    ResultRO<Map<String, List<CenterUserFollowDetailRO>>> queryUserFollows();

    @PostMapping("addFollow")
    ResultRO<Void> addFollow(@RequestBody @Valid CenterFollowAddQO addVO);

    @PostMapping("cancelFollow")
    ResultRO<Void> cancelFollow(@RequestBody @Valid CenterFollowAddQO addVO);

}