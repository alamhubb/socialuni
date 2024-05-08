package com.socialuni.social.likee.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.user.SocialuniUserIdQO;
import com.socialuni.social.like.api.SocialuniUserLikeAPI;
import com.socialuni.social.like.logic.service.SocialuniUserLikeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("socialuni/userLike")
class SocialuniUserLikeControll implements SocialuniUserLikeAPI {
    @Resource
    SocialuniUserLikeService socialuniUserLikeService;

    @PostMapping("likeUser")
    public ResultRO<Void> likeUser(SocialuniUserIdQO addVO) {
        socialuniUserLikeService.likeUser(addVO);
        return ResultRO.success();
    }
}
