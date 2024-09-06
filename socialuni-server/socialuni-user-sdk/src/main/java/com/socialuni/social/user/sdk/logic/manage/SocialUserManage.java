package com.socialuni.social.user.sdk.logic.manage;

import com.socialuni.social.common.api.constant.CommonRedisKey;
import com.socialuni.social.common.sdk.constant.SocialuniSysRoleId;
import com.socialuni.social.common.sdk.constant.UserType;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.content.utils.BirthdayAgeUtil;
import com.socialuni.social.user.sdk.model.QO.SocialProviderLoginQO;
import com.socialuni.social.user.sdk.model.factory.SocialUserDOFactory;
import com.socialuni.social.common.sdk.dao.repository.SocialuniUserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
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
    public SocialuniUserDo createOperateUser(String nickname, Long unionId) {
        SocialuniUserDo user = SocialUserDOFactory.newUserByPhoneLogin(unionId);
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

    public SocialuniUserDo createUserByProviderLogin(SocialProviderLoginQO loginQO, Long unionId) {
        SocialuniUserDo user = SocialUserDOFactory.newUserByProviderLogin(loginQO, unionId);
        user = userApi.savePut(user);
        return user;
    }

    public SocialuniUserDo createUserByThirdProviderLogin(SocialProviderLoginQO loginQO, String type, Long unionId) {
        SocialuniUserDo user = SocialUserDOFactory.newUserByProviderLogin(loginQO, unionId);
        if (!StringUtils.isEmpty(type)) {
            //有可能为系统用户
            user.setType(type);
        }
        user = userApi.savePut(user);
        return user;
    }

    public SocialuniUserDo createUser(Long unionId) {
        SocialuniUserDo user = SocialUserDOFactory.newUserByPhoneLogin(unionId);
        user = userApi.savePut(user);
        return user;
    }

    public SocialuniUserDo createUserByNickname(String nickname, Long unionId) {
        SocialuniUserDo user = SocialUserDOFactory.newUserByNickname(nickname, unionId);
        user = userApi.savePut(user);
        return user;
    }

    public SocialuniUserDo createSysUserByPhoneLogin(Long unionId) {
        SocialuniUserDo user = SocialUserDOFactory.newUserByPhoneLogin(unionId);
        user.setRoleId(SocialuniSysRoleId.sys);
        user.setType(UserType.system);
        user = userApi.savePut(user);
        return user;
    }
}