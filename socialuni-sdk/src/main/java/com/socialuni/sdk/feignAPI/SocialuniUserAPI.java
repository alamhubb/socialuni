package com.socialuni.sdk.feignAPI;

import com.socialuni.sdk.model.QO.user.*;
import com.socialuni.sdk.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.sdk.model.RO.user.CenterUserDetailRO;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.net.URI;
import java.util.Map;


@RequestMapping("user")
public interface SocialuniUserAPI {
    @PostMapping("registryUser")
    ResultRO<CenterMineUserDetailRO> registryUser(URI baseUrl, @RequestHeader Map<String, Object> headerMap, @RequestBody @Valid SocialProviderLoginQO loginQO);

    @PostMapping("getMineUser")
    ResultRO<CenterMineUserDetailRO> getMineUser();

    @PostMapping("queryThirdUser")
    ResultRO<CenterMineUserDetailRO> queryThirdUser();

    @PostMapping("queryUserDetail")
    ResultRO<CenterUserDetailRO> queryUserDetail(@RequestBody @Valid CenterUserIdQO centerUserIdQO);

    @PostMapping("editUser")
    ResultRO<CenterMineUserDetailRO> editUser(@RequestBody @Valid SocialUserEditQO socialUserEditQO);

    @PostMapping("addUserImg")
    ResultRO<CenterMineUserDetailRO> addUserImg(@RequestBody @Valid SocialUserImgAddQO socialUserImgAddQO);

    @PostMapping("deleteUserImg")
    ResultRO<CenterMineUserDetailRO> deleteUserImg(@RequestBody @Valid CenterUserImgDeleteQO centerUserImgDeleteQO);
}