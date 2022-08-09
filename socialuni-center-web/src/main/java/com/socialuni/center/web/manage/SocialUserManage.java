package com.socialuni.center.web.manage;

import com.socialuni.center.web.model.QO.user.SocialProviderLoginQO;
import com.socialuni.center.web.model.DO.user.SocialUserDO;
import com.socialuni.center.web.repository.UserRepository;
import com.socialuni.center.web.utils.SocialUserDOFactory;
import com.socialuni.center.web.utils.UnionIdDbUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialUserManage {
    @Resource
    UserRepository userRepository;

    public SocialUserDO createUserByProviderLogin(SocialProviderLoginQO loginQO) {
        SocialUserDO user = SocialUserDOFactory.newUserByProviderLogin(loginQO);
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