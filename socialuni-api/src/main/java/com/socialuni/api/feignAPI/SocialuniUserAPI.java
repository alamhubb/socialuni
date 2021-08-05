package com.socialuni.api.feignAPI;


import com.socialuni.api.model.CenterMineUserDetailRO;
import com.socialuni.api.model.CenterUserDetailRO;
import com.socialuni.api.model.CenterUserIdQO;
import com.socialuni.social.model.model.RO.ResultRO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@RequestMapping("user")
@FeignClient(name = "user", url = "${socialuni.server-url:https://api.socialuni.com}")
public interface SocialuniUserAPI {

    @PostMapping("getMineUser")
    ResultRO<CenterMineUserDetailRO> getMineUser();

    @PostMapping("queryUserDetail")
    ResultRO<CenterUserDetailRO> queryUserDetail(@RequestBody @Valid CenterUserIdQO centerUserIdQO);

}