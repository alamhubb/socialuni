package com.socialuni.admin.web.service;

import com.socialuni.social.sdk.dao.DO.user.SocialUserPhoneDO;
import com.socialuni.social.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.social.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.common.exception.exception.SocialParamsException;
import com.socialuni.social.sdk.dao.redis.SocialUserPhoneRedis;
import com.socialuni.social.sdk.dao.repository.dev.ThirdUserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminUserService {
    @Resource
    SocialUserPhoneRedis socialUserPhoneRedis;
    @Resource
    ThirdUserRepository thirdUserRepository;

    public SocialuniUserDO getUserByPhoneNum(String phoneNum) {
        //根据phoneNum获取用户
        SocialUserPhoneDO socialUserPhoneDO = socialUserPhoneRedis.findByPhoneNum(phoneNum);
        if (socialUserPhoneDO == null) {
            throw new SocialParamsException("用户不存在或开发者无权限");
        }
        SocialuniUserDO user = SocialuniUserUtil.getUserNotNull(socialUserPhoneDO.getUserId());
        return user;
    }
}
