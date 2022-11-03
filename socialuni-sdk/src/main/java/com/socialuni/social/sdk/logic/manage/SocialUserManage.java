package com.socialuni.social.sdk.logic.manage;

import com.socialuni.social.sdk.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.sdk.utils.SocialUserDOFactory;
import com.socialuni.social.user.sdk.entity.SocialuniUserDo;
import com.socialuni.social.user.sdk.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialUserManage {
    @Resource
    UserRepository userApi;

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