package com.socialuni.admin.web.service;

import com.socialuni.sdk.model.DO.user.SocialUserPhoneDO;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.utils.SocialUserUtil;
import com.socialuni.social.web.sdk.exception.SocialParamsException;
import com.socialuni.sdk.dao.redis.SocialUserPhoneRedis;
import com.socialuni.sdk.dao.repository.dev.ThirdUserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialuniUserService {
    @Resource
    SocialUserPhoneRedis socialUserPhoneRedis;
    @Resource
    ThirdUserRepository thirdUserRepository;

    public SocialUserDO getUserByPhoneNum(String phoneNum) {
        //根据phoneNum获取用户
        SocialUserPhoneDO socialUserPhoneDO = socialUserPhoneRedis.findByPhoneNum(phoneNum);
        if (socialUserPhoneDO == null) {
            throw new SocialParamsException("用户不存在或开发者无权限");
        }
        SocialUserDO user = SocialUserUtil.getUserNotNull(socialUserPhoneDO.getUserId());
        return user;
    }
}
