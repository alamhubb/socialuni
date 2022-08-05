package com.socialuni.admin.web.service;

import com.socialuni.social.entity.model.DO.dev.DevAccountDO;
import com.socialuni.social.entity.model.DO.dev.ThirdUserDO;
import com.socialuni.social.entity.model.DO.user.SocialUserPhoneDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.exception.SocialParamsException;
import com.socialuni.social.sdk.redis.SocialUserPhoneRedis;
import com.socialuni.social.sdk.repository.dev.ThirdUserRepository;
import com.socialuni.social.sdk.utils.DevAccountUtils;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialuniUserService {
    @Resource
    SocialUserPhoneRedis socialUserPhoneRedis;
    @Resource
    ThirdUserRepository thirdUserRepository;

    public UserDO getUserByPhoneNum(String phoneNum) {
        //根据phoneNum获取用户
        SocialUserPhoneDO socialUserPhoneDO = socialUserPhoneRedis.findByPhoneNum(phoneNum);
        if (socialUserPhoneDO == null) {
            throw new SocialParamsException("用户不存在或开发者无权限");
        }
        DevAccountDO devAccountDO = DevAccountUtils.getAdminDevAccountNotNull();
        ThirdUserDO thirdUserDO = thirdUserRepository.findByDevIdAndUserId(devAccountDO.getId(), socialUserPhoneDO.getUserId());
        if (thirdUserDO == null) {
            throw new SocialParamsException("用户不存在或开发者无权限");
        }
        UserDO user = SocialUserUtil.getNotNull(thirdUserDO.getUserId());
        return user;
    }
}
