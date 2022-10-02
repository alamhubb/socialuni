package com.socialuni.social.sdk.feignAPI.community;

import com.socialuni.social.sdk.model.QO.follow.SocialuniFollowAddQO;
import com.socialuni.social.sdk.model.RO.user.SocialuniUserFollowDetailRO;
import com.socialuni.social.common.model.ResultRO;
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
    ResultRO<Map<String, List<SocialuniUserFollowDetailRO>>> queryUserFollows();

    @PostMapping("addFollow")
    @Operation(summary = "关注用户")
    ResultRO<Void> addFollow(@RequestBody @Valid SocialuniFollowAddQO addVO);

    @PostMapping("cancelFollow")
    @Operation(summary = "取消关注")
    ResultRO<Void> cancelFollow(@RequestBody @Valid SocialuniFollowAddQO addVO);

}