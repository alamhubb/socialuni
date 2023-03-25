package com.socialuni.social.im.api.friend;


import com.socialuni.social.common.api.model.ResultRO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:45
 */

//@RequestMapping("socialuni/chat")
@FeignClient(name = "friend", url = "${socialuni.central-server-url:https://api.socialuni.cn}", path = "socialuni/friend")
@Tag(name = "消息模块/好友模块",description = "暂未支持")
public interface SocialuniFriendAPI {

    @GetMapping("addFriend/{userId}")
    ResultRO<Void> addFriend(@PathVariable("userId") String userId);

}

