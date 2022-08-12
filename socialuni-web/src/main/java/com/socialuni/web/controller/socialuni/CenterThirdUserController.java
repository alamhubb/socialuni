package com.socialuni.web.controller.socialuni;

import com.socialuni.sdk.feignAPI.SocialuniThirdUserAPI;
import com.socialuni.sdk.model.QO.user.SocialProviderLoginQO;
import com.socialuni.sdk.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CenterThirdUserController implements SocialuniThirdUserAPI {
    @Override
    public ResultRO<CenterMineUserDetailRO> registryUser(SocialProviderLoginQO loginQO) {
        return null;
    }

    @Override
    public ResultRO<CenterMineUserDetailRO> queryThirdUser() {
        return null;
    }
}
