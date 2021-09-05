package com.qingchi.web.controller.socialuni;


import com.qingchi.web.model.RO.user.MineUserDetailRO;
import com.qingchi.web.service.MockOAuthAuthService;
import com.socialuni.api.feignAPI.SocialuniMockOAuthAPI;
import com.socialuni.api.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.RO.user.login.SocialLoginRO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("mock")
public class MockOAuthAuthController {
    @Resource
    MockOAuthAuthService mockOAuthAuthService;

    @PostMapping("mockOAuthUserPhoneNum")
    public ResultRO<MineUserDetailRO> mockOAuthUserPhoneNum() {
        return mockOAuthAuthService.mockOAuthUserPhoneNum();
    }
}