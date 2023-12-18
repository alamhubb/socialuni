package com.socialuni.social.tance.sdk.api;

import com.socialuni.social.common.api.model.ResultRO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@Tag(name = "用户模块/用户信息模块")
@FeignClient(name = "dev", url = "${socialuni.central-server-url:https://api.socialuni.cn}", path = "socialuni/dev")
public interface SocialuniDevTokenAPI {
    @GetMapping("getAuthToken/{devKey}")
    @Operation(summary = "根据秘钥获取开发者授权token")
    ResultRO<String> getAuthToken(@PathVariable("devKey") String devKey);
}