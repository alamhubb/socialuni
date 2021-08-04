package com.socialuni.api.feignAPI.user;


import com.socialuni.social.model.model.QO.user.SocialUserIdQO;
import com.socialuni.social.model.model.RO.ResultRO;
import com.socialuni.social.model.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.social.model.model.RO.user.SocialUserDetailRO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@RequestMapping("user")
@FeignClient(name = "user", url = "${socialuni.server-url:https://api.socialuni.com}")
public interface SocialuniUserAPI {

    @PostMapping("getMineUser")
    ResultRO<SocialMineUserDetailRO> getMineUser();

    @PostMapping("queryUserDetail")
    ResultRO<SocialUserDetailRO> queryUserDetail(@RequestBody @Valid SocialUserIdQO socialUserIdQO);

}