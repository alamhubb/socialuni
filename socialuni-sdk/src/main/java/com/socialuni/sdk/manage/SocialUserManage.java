package com.socialuni.sdk.manage;

import com.socialuni.sdk.model.QO.user.SocialProviderLoginQO;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.repository.UserRepository;
import com.socialuni.sdk.utils.SocialUserDOFactory;
import com.socialuni.sdk.utils.UnionIdDbUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialUserManage {
    @Resource
    UserRepository userRepository;

    public SocialUserDO createUserByProviderLogin(SocialProviderLoginQO loginQO) {
        Integer userUnionId = UnionIdDbUtil.createUserUnionId();
        SocialUserDO user = SocialUserDOFactory.newUserByProviderLogin(loginQO);
        user.setUnionId(userUnionId);
        user = userRepository.save(user);
        return user;
    }

    public SocialUserDO createUserByPhoneLogin() {
        Integer userUnionId = UnionIdDbUtil.createUserUnionId();
        SocialUserDO user = SocialUserDOFactory.newUserByPhoneLogin();
        user.setUnionId(userUnionId);
        user = userRepository.save(user);
        return user;
    }
}