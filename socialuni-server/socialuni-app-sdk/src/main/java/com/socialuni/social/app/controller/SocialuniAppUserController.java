package com.socialuni.social.app.controller;

import com.socialuni.social.app.api.SocialuniAppUserAPI;
import com.socialuni.social.app.logic.service.SocialuniAppUserService;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.user.SocialuniUserDetailRO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("socialuni/appUser")
public class SocialuniAppUserController implements SocialuniAppUserAPI {
    @Resource
    SocialuniAppUserService socialuniAppUserService;

    @Override
    @GetMapping("queryUserDetail/{userId}")
    public ResultRO<SocialuniUserDetailRO> queryUserDetail(@PathVariable("userId") Long userId){
        return socialuniAppUserService.queryUserDetail(userId);
    }
}
