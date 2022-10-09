package com.socialuni.social.sdk.feignAPI.community;

import com.socialuni.social.common.model.ResultRO;
import com.socialuni.social.sdk.model.QO.SocialuniHugAddQO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

//@RequestMapping("socialuni/hug")
@FeignClient(name = "hug", url = "${socialuni.central-server-url:https://api.socialuni.cn}", path = "socialuni/hug")
@Tag(name = "社区模块/点赞模块")
public interface SocialuniHugAPI {
    @PostMapping("addHug")
    @Operation(summary = "点赞")
    ResultRO<Void> addHug(@RequestBody @Valid SocialuniHugAddQO socialHugAddQO);
}