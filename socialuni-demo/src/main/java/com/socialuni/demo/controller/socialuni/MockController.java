package com.socialuni.demo.controller.socialuni;

import com.socialuni.api.feignAPI.SocialuniMockOAuthAPI;
import com.socialuni.api.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.demo.service.LoginService;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.QO.user.MockOAuthUserInfoQO;
import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.model.model.RO.SocialOAuthUserRO;
import com.socialuni.social.model.model.RO.user.login.SocialLoginRO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
public class MockController implements SocialuniMockOAuthAPI {
    @Resource
    SocialuniMockOAuthAPI socialuniMockOAuthAPI;

    /*@Override
    public ResultRO<SocialLoginRO<SocialOAuthUserRO>> mockOAuthUserInfo() {
        return socialuniMockOAuthAPI.mockOAuthUserInfo();
    }*/

    @Override
    public ResultRO<SocialLoginRO<SocialOAuthUserRO>> mockOAuthUserPhoneNum() {
        return socialuniMockOAuthAPI.mockOAuthUserPhoneNum();
    }
}
