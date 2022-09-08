package com.socialuni.sdk.controller;

import com.socialuni.sdk.feignAPI.SocialuniUserExpandAPI;
import com.socialuni.sdk.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.social.web.sdk.model.ResultRO;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SocialuniUserExpandController implements SocialuniUserExpandAPI {
    @Override
    public ResultRO<CenterMineUserDetailRO> editUserSchool() {
        return null;
    }
}
