package com.socialuni.social.like.api

import com.socialuni.social.common.api.model.ResultRO
import com.socialuni.social.common.api.model.user.SocialuniUserIdQO
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import javax.validation.Valid

//@RequestMapping("socialuni/follow")
@FeignClient(
    name = "like",
    url = "\${socialuni.central-server-url:https://api.socialuni.cn}",
    path = "socialuni/like"
)
@Tag(name = "用户模块/喜欢用户")
interface SocialuniUserLikeAPI {
    @PostMapping("addLike")
    @Operation(summary = "喜欢用户")
    fun addLike(@RequestBody addVO: @Valid SocialuniUserIdQO?): ResultRO<Void?>?
}