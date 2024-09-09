package com.socialuni.social.user.sdk.api.user;

import com.socialuni.social.common.api.model.user.SocialuniUserShowRO;
import com.socialuni.social.common.api.model.ResultRO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


//@RequestMapping("socialuni/user")
@Tag(name = "用户模块/用户信息模块")
@FeignClient(name = "user", url = "${socialuni.central-server-url:https://api.socialuni.cn}", path = "socialuni/user")
public interface SocialuniUserAPI {
    @GetMapping("queryUserDetail/{userId}")
    @Operation(summary = "获取他人用户详情")
    ResultRO<SocialuniUserShowRO> queryUserDetail(@PathVariable("userId") Long userId);
//    @GetMapping("queryRecentlyUsers")
//    @Operation(summary = "获取最近在线用户")
//    ResultRO<List<SocialuniContentUserRO>> queryRecentlyUsers();
}