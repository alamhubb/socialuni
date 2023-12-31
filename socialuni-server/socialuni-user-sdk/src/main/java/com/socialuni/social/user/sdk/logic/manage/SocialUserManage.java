package com.socialuni.social.user.sdk.logic.manage;

import com.socialuni.social.common.sdk.constant.SocialuniSysRoleId;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.model.QO.SocialProviderLoginQO;
import com.socialuni.social.user.sdk.model.factory.SocialUserDOFactory;
import com.socialuni.social.common.sdk.dao.repository.SocialuniUserRepository;
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

    public SocialuniUserDo createSysUserByPhoneLogin() {
        SocialuniUserDo user = SocialUserDOFactory.newUserByPhoneLogin();
        user.setRoleId(SocialuniSysRoleId.sys);
        user = userApi.savePut(user);
        return user;
    }
}