package com.socialuni.social.user.sdk.logic.manage;

import com.socialuni.social.common.api.constant.CommonRedisKey;
import com.socialuni.social.common.sdk.constant.SocialuniSysRoleId;
import com.socialuni.social.common.sdk.constant.UserType;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.content.utils.BirthdayAgeUtil;
import com.socialuni.social.user.sdk.model.QO.SocialProviderLoginQO;
import com.socialuni.social.user.sdk.model.factory.SocialUserDOFactory;
import com.socialuni.social.common.sdk.dao.repository.SocialuniUserRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;

@Service
public class SocialUserManage {
    @Resource
    SocialuniUserRepository userApi;

    @Caching(
            evict = {
                    @CacheEvict(cacheNames = CommonRedisKey.findUserIdsByType, key = UserType.operation),
            }
    )
    public SocialuniUserDo createOperateUser(String nickname) {
        SocialuniUserDo user = SocialUserDOFactory.newUserByPhoneLogin();
        user.setNickname(nickname);
        user.setType(UserType.operation);

        Random random = new Random();
        int randomNumber = random.nextInt(6);
        int age = 19 + randomNumber;
        user.setAge(age);
        user.setBirthday(BirthdayAgeUtil.getYearBirthDateByAge(age));
        user = userApi.savePut(user);
        return user;
    }

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