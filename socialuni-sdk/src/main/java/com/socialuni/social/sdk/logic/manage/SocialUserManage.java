package com.socialuni.social.sdk.logic.manage;

import com.socialuni.social.sdk.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.user.sdk.model.SocialuniUserModel;
import com.socialuni.social.user.sdk.api.UserApi;
import com.socialuni.social.sdk.utils.SocialUserDOFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialUserManage {
    @Resource
    UserApi userApi;

    public SocialuniUserModel createUserByProviderLogin(SocialProviderLoginQO loginQO) {
        SocialuniUserModel user = SocialUserDOFactory.newUserByProviderLogin(loginQO);
        user = userApi.savePut(user);
        return user;
    }

    public SocialuniUserModel createUserByPhoneLogin() {
        SocialuniUserModel user = SocialUserDOFactory.newUserByPhoneLogin();
        user = userApi.savePut(user);
        return user;
    }
}