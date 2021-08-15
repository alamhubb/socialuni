package com.socialuni.social.sdk.url.user;


import com.socialuni.social.model.model.QO.user.SocialUserIdQO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.social.model.model.RO.user.SocialUserDetailRO;
import com.socialuni.social.sdk.model.QO.user.SocialUserEditQO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@RequestMapping("user")
public interface SocialuniUserUrl {

    @PostMapping("getMineUser")
    ResultRO<SocialMineUserDetailRO> getMineUser();

    @PostMapping("queryUserDetail")
    ResultRO<SocialUserDetailRO> queryUserDetail(@RequestBody @Valid SocialUserIdQO socialUserIdQO);

    @PostMapping("editUser")
    ResultRO<SocialMineUserDetailRO> editUser(@RequestBody @Valid SocialUserEditQO socialUserEditQO);
}