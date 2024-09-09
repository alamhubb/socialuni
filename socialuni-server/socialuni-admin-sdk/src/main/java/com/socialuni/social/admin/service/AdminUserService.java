package com.socialuni.social.admin.service;

import com.qingchi.qing.common.exception.base.exception.SocialParamsException;
import com.socialuni.social.sdk.dao.repository.dev.ThirdUserRepository;
import com.socialuni.social.user.sdk.dao.DO.SocialUserPhoneDo;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.logic.redis.SocialUserPhoneRedis;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminUserService {
    @Resource
    SocialUserPhoneRedis socialUserPhoneRedis;
    @Resource
    ThirdUserRepository thirdUserRepository;

    public SocialuniUserDo getUserByPhoneNum(String phoneNum) {
        //根据phoneNum获取用户
        SocialUserPhoneDo SocialUserPhoneDo = socialUserPhoneRedis.findByPhoneNum(phoneNum);
        if (SocialUserPhoneDo == null) {
            throw new SocialParamsException("用户不存在或开发者无权限");
        }
        SocialuniUserDo user = SocialuniUserUtil.getUserNotNull(SocialUserPhoneDo.getUserId());
        return user;
    }
}
