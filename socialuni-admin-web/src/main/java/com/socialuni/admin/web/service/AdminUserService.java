package com.socialuni.admin.web.service;

import com.socialuni.social.user.sdk.model.SocialUserPhoneModel;
import com.socialuni.social.user.sdk.model.SocialuniUserModel;
import com.socialuni.social.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.common.sdk.exception.exception.SocialParamsException;
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

    public SocialuniUserModel getUserByPhoneNum(String phoneNum) {
        //根据phoneNum获取用户
        SocialUserPhoneModel socialUserPhoneModel = socialUserPhoneRedis.findByPhoneNum(phoneNum);
        if (socialUserPhoneModel == null) {
            throw new SocialParamsException("用户不存在或开发者无权限");
        }
        SocialuniUserModel user = SocialuniUserUtil.getUserNotNull(socialUserPhoneModel.getUserId());
        return user;
    }
}
