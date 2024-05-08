package com.socialuni.social.likee.api;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.user.SocialuniUserIdQO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(name = "userLike", url = "${socialuni.central-server-url:https://api.socialuni.cn}", path = "socialuni/userLike")
@Tag(name = "用户模块/喜欢用户")
public interface SocialuniUserLikeAPI {
    @PostMapping("likeUser")
    @Operation(summary = "喜欢用户")
    ResultRO<Void> likeUser(@RequestBody @Valid SocialuniUserIdQO addVO);
}