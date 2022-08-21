package com.socialuni.admin.web.service;

import com.socialuni.sdk.model.DO.dev.DevAccountDO;
import com.socialuni.sdk.model.DO.user.SocialUserPhoneDO;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.social.exception.SocialParamsException;
import com.socialuni.sdk.redis.SocialUserPhoneRedis;
import com.socialuni.sdk.repository.dev.ThirdUserRepository;
import com.socialuni.sdk.utils.DevAccountUtils;
import com.socialuni.sdk.utils.SocialUserUtil;
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
        DevAccountDO devAccountDO = DevAccountUtils.getAdminDevAccountNotNull();
        /*ThirdSocialUserDO thirdSocialUserDO = thirdUserRepository.findByDevIdAndUserId(devAccountDO.getId(), socialUserPhoneDO.getUserId());
        if (thirdSocialUserDO == null) {
            throw new SocialParamsException("用户不存在或开发者无权限");
        }
        SocialUserDO user = SocialUserUtil.getNotNull(thirdSocialUserDO.getUserId());*/
        return null;
    }
}
