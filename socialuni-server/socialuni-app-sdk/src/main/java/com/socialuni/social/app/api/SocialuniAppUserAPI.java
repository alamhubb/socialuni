package com.socialuni.social.app.api;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.user.SocialuniUserDetailRO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "用户模块/特定应用用户信息模块")
@FeignClient(name = "appUser", url = "${socialuni.central-server-url:https://api.socialuni.cn}", path = "socialuni/appUser")
public interface SocialuniAppUserAPI {
    @GetMapping("queryUserDetail/{userId}")
    @Operation(summary = "获取他人用户详情")
    ResultRO<SocialuniUserDetailRO> queryUserDetail(@PathVariable("userId") String userId);
}


