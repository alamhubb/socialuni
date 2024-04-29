package com.socialuni.social.follow.controller

import com.socialuni.social.common.api.model.ResultRO
import com.socialuni.social.common.api.model.user.SocialuniUserIdQO
import com.socialuni.social.like.api.SocialuniUserLikeAPI
import com.socialuni.social.like.logic.service.SocialuniUserLikeService
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.Resource
import javax.validation.Valid

@RestController
@RequestMapping("socialuni/like")
class SocialuniUserLikeController : SocialuniUserLikeAPI {
    @Resource
    val socialuniUserLikeService: SocialuniUserLikeService? = null

    override fun addLike(@RequestBody addVO: @Valid SocialuniUserIdQO?): ResultRO<Void?>? {
        socialuniUserLikeService?.addLike(addVO);
        return ResultRO.success();
    }
}