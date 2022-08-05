package com.socialuni.center.web.manage;

import com.socialuni.center.web.model.QO.user.SocialProviderLoginQO;
import com.socialuni.center.web.model.DO.user.UserDO;
import com.socialuni.center.web.repository.UserRepository;
import com.socialuni.center.web.utils.SocialUserDOFactory;
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