package com.socialuni.sdk.feignAPI;

import com.socialuni.sdk.model.QO.follow.CenterFollowAddQO;
import com.socialuni.sdk.model.RO.user.CenterUserFollowDetailRO;
import com.socialuni.social.web.sdk.model.ResultRO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RequestMapping("socialuni/follow")
@FeignClient(name = "follow", url = "${socialuni.central-server-url:https://api.socialuni.cn}")
@Tag(name = "社区模块/关注模块")
public interface SocialuniFollowAPI {

    @GetMapping("queryUserFollows")
    @Operation(summary = "查询用户关注列表")
    ResultRO<Map<String, List<CenterUserFollowDetailRO>>> queryUserFollows();

    @PostMapping("addFollow")
    @Operation(summary = "关注用户")
    ResultRO<Void> addFollow(@RequestBody @Valid CenterFollowAddQO addVO);

    @PostMapping("cancelFollow")
    @Operation(summary = "取消关注")
    ResultRO<Void> cancelFollow(@RequestBody @Valid CenterFollowAddQO addVO);

}