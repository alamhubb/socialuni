package com.socialuni.social.user.sdk.manage;

import com.socialuni.social.user.sdk.model.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.model.QO.SocialProviderLoginQO;
import com.socialuni.social.user.sdk.model.factory.SocialUserDOFactory;
import com.socialuni.social.user.sdk.repository.SocialuniUserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialUserManage {
    @Resource
    SocialuniUserRepository userApi;

    public SocialuniUserDo createUserByProviderLogin(SocialProviderLoginQO loginQO) {
        SocialuniUserDo user = SocialUserDOFactory.newUserByProviderLogin(loginQO);
        user = userApi.savePut(user);
        return user;
    }

    public SocialuniUserDo createUserByPhoneLogin() {
        SocialuniUserDo user = SocialUserDOFactory.newUserByPhoneLogin();
        user = userApi.savePut(user);
        return user;
    }
}