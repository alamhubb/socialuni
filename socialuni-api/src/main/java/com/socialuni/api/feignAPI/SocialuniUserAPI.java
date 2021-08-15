package com.socialuni.api.feignAPI;


import com.socialuni.api.model.QO.user.CenterUserEditQO;
import com.socialuni.api.model.QO.user.CenterUserIdQO;
import com.socialuni.api.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.api.model.RO.user.CenterUserDetailRO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.QO.user.SocialUserEditQO;
import com.socialuni.social.model.model.RO.user.SocialMineUserDetailRO;
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

    @PostMapping("editUser")
    ResultRO<CenterMineUserDetailRO> editUser(@RequestBody @Valid SocialUserEditQO socialUserEditQO);
}