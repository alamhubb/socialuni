package com.socialuni.social.im.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.im.api.friend.SocialuniFriendAPI;
import com.socialuni.social.im.logic.service.SocialuniFriendService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("socialuni/friend")
public class SocialuniFriendController implements SocialuniFriendAPI {

    @Resource
    SocialuniFriendService socialuniFriendService;

    @Override
    public ResultRO<Void> addFriend(String userId) {
        socialuniFriendService.addFriend(userId);
        return ResultRO.success();
    }
}
