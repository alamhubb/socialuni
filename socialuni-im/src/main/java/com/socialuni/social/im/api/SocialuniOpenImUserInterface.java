package com.socialuni.social.im.api;

import com.socialuni.social.im.model.SocialuniImUserModel;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("socialuni/im/user")
@FeignClient(name = "socialuniImUser", url = "${socialuni.central-server-url:https://api.socialuni.cn}")
@Tag(name = "消息模块/消息用户模块", description = "暂未支持")
public interface SocialuniOpenImUserInterface {

    @Async
    @PostMapping("userLogin")
    void userLogin(@RequestBody SocialuniImUserModel imUserModel);

    @PostMapping("getToken")
    String getToken(String userId);
}
