package com.socialuni.social.im.api;

import com.socialuni.social.common.api.model.ResultRO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@RequestMapping("socialuni/imUser")
@Tag(name = "聊天模块/用户信息模块")
@FeignClient(name = "imUser", url = "${socialuni.central-server-url:https://api.socialuni.cn}", path = "socialuni/imUser")
public interface SocialuniImUserAPI {
    @GetMapping("getImUserToken")
    @Operation(summary = "获取用户Im模块的token")
    ResultRO<String> getImUserToken();
}