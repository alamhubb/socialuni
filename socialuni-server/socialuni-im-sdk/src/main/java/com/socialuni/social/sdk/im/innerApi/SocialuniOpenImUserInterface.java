package com.socialuni.social.sdk.im.innerApi;

import com.socialuni.social.sdk.im.model.SocialuniImUserModel;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("socialuni/im/user")
@FeignClient(name = "socialuniImUser", url = "${socialuni.central-server-url:https://api.socialuni.cn}")
@Tag(name = "消息模块/消息用户模块", description = "暂未支持")
public interface SocialuniOpenImUserInterface {

    @PostMapping("userLogin")
    String userLogin(@RequestBody SocialuniImUserModel imUserModel);

    @PostMapping("getToken")
    String getAndRefreshToken(String userId);
}
