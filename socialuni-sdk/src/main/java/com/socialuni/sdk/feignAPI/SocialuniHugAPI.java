package com.socialuni.sdk.feignAPI;

import com.socialuni.sdk.model.QO.SocialuniHugAddQO;
import com.socialuni.social.web.sdk.model.ResultRO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("socialuni/hug")
@FeignClient(name = "hug", url = "${socialuni.central-server-url:https://api.socialuni.cn}")
@Tag(name = "社区模块/点赞模块")
public interface SocialuniHugAPI {
    @PostMapping("addHug")
    @Operation(summary = "点赞")
    ResultRO<Void> addHug(@RequestBody @Valid SocialuniHugAddQO socialHugAddQO);
}