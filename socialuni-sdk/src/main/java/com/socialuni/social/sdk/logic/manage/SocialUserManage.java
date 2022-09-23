package com.socialuni.social.sdk.logic.manage;

import com.socialuni.social.sdk.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.social.sdk.dao.repository.UserRepository;
import com.socialuni.social.sdk.utils.SocialUserDOFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialUserManage {
    @Resource
    UserRepository userRepository;

    public SocialuniUserDO createUserByProviderLogin(SocialProviderLoginQO loginQO) {
        SocialuniUserDO user = SocialUserDOFactory.newUserByProviderLogin(loginQO);
        user = userRepository.save(user);
        return user;
    }

    public SocialuniUserDO createUserByPhoneLogin() {
        SocialuniUserDO user = SocialUserDOFactory.newUserByPhoneLogin();
        user = userRepository.save(user);
        return user;
    }
}