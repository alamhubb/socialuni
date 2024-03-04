package com.socialuni.social.sdk.im.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.im.api.feign.SocialuniFriendAPI;
import com.socialuni.social.sdk.im.logic.service.SocialuniFriendService;
import com.socialuni.social.im.api.model.QO.friend.SocialuniFriendAddQO;
import com.socialuni.social.im.api.model.RO.SocialuniFriendApplyUserRO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("socialuni/friend")
public class SocialuniFriendController implements SocialuniFriendAPI {
    @Resource
    SocialuniFriendService socialuniFriendService;

    @Override
    public ResultRO<Void> addFriend(SocialuniFriendAddQO friendAddQO) {
        return socialuniFriendService.addFriend(friendAddQO);
    }

    @Override
    public ResultRO<List<SocialuniFriendApplyUserRO>> queryFriendApplyList() {
        return socialuniFriendService.queryFriendApplyList();
    }
}
