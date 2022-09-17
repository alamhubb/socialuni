package com.socialuni.sdk.logic.manage;

import com.socialuni.sdk.model.QO.user.SocialProviderLoginQO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.dao.repository.UserRepository;
import com.socialuni.sdk.utils.SocialUserDOFactory;
import com.socialuni.sdk.utils.UnionIdUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialUserManage {
    @Resource
    UserRepository userRepository;

    public SocialuniUserDO createUserByProviderLogin(SocialProviderLoginQO loginQO) {
        Integer userUnionId = UnionIdUtil.createUserUnionId();
        SocialuniUserDO user = SocialUserDOFactory.newUserByProviderLogin(loginQO);
        user.setUnionId(userUnionId);
        user = userRepository.save(user);
        return user;
    }

    public SocialuniUserDO createUserByPhoneLogin() {
        Integer userUnionId = UnionIdUtil.createUserUnionId();
        SocialuniUserDO user = SocialUserDOFactory.newUserByPhoneLogin();
        user.setUnionId(userUnionId);
        user = userRepository.save(user);
        return user;
    }
}