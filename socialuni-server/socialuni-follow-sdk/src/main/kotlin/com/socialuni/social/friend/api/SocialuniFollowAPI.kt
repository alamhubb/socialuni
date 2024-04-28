package com.socialuni.social.friend.api

import com.socialuni.social.common.api.model.ResultRO
import com.socialuni.social.common.api.model.SocialuniPageQueryQO
import com.socialuni.social.common.api.model.user.SocialuniUserFollowDetailListRO
import com.socialuni.social.friend.model.qo.SocialuniUserFollowAddQO
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import javax.validation.Valid

//@RequestMapping("socialuni/follow")
@FeignClient(
    name = "follow",
    url = "\${socialuni.central-server-url:https://api.socialuni.cn}",
    path = "socialuni/follow"
)
@Tag(name = "社区模块/关注模块")
interface SocialuniFollowAPI {
    @PostMapping("queryUserFollows")
    @Operation(summary = "查询用户关注列表")
    fun queryUserFollows(@RequestBody socialuniPageQueryQO: @Valid SocialuniPageQueryQO<String?>?): ResultRO<List<SocialuniUserFollowDetailListRO?>?>?

    @PostMapping("addFollow")
    @Operation(summary = "关注用户")
    fun addFollow(@RequestBody addVO: @Valid SocialuniUserFollowAddQO?): ResultRO<Void?>?

    @PostMapping("cancelFollow")
    @Operation(summary = "取消关注")
    fun cancelFollow(@RequestBody addVO: @Valid SocialuniUserFollowAddQO?): ResultRO<Void?>?
}