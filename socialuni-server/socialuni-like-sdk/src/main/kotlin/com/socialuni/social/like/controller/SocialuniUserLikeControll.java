package com.socialuni.social.like.controller;

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

    @PostMapping("addLikeUser")
    public ResultRO<Void> addLikeUser(SocialuniUserIdQO addVO) {
        socialuniUserLikeService.addLike(addVO);
        return ResultRO.success();
    }
}
