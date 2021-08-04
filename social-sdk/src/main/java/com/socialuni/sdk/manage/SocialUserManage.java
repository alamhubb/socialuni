package com.socialuni.sdk.manage;

import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;
import com.socialuni.sdk.model.DO.user.UserDO;
import com.socialuni.sdk.repository.UserRepository;
import com.socialuni.sdk.utils.SocialUserDOFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialUserManage {
    @Resource
    UserRepository userRepository;

    public UserDO createUserByProviderLogin(SocialProviderLoginQO loginQO) {
        UserDO user = SocialUserDOFactory.newUserByProviderLogin(loginQO);
        user = userRepository.save(user);
        return user;
    }

    public UserDO createUserByPhoneLogin() {
        UserDO user = SocialUserDOFactory.newUserByPhoneLogin();
        user = userRepository.save(user);
        return user;
    }
}