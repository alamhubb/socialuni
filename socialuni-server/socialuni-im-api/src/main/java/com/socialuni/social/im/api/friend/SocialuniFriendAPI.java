package com.socialuni.social.im.api.friend;


import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.im.model.QO.friend.SocialuniFriendAddQO;
import com.socialuni.social.im.model.RO.SocialuniFriendApplyUserRO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:45
 */

//@RequestMapping("socialuni/chat")
@FeignClient(name = "friend", url = "${socialuni.central-server-url:https://api.socialuni.cn}", path = "socialuni/friend")
@Tag(name = "消息模块/好友模块",description = "暂未支持")
public interface SocialuniFriendAPI {

    @PostMapping("addFriend")
    ResultRO<Void> addFriend(@RequestBody @Valid @NotNull SocialuniFriendAddQO friendAddQO);


    @GetMapping("queryFriendApplyList")
    ResultRO<List<SocialuniFriendApplyUserRO>> queryFriendApplyList();
}

