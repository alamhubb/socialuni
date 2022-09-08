package com.socialuni.sdk.utils;

import com.socialuni.sdk.config.SocialRequestUserConfig;
import com.socialuni.sdk.constant.SocialuniAccountProviderType;
import com.socialuni.sdk.constant.socialuni.UserIdentityAuthStatus;
import com.socialuni.sdk.constant.status.UserStatus;
import com.socialuni.sdk.dao.DO.user.*;
import com.socialuni.sdk.dao.redis.SocialUserPhoneRedis;
import com.socialuni.sdk.dao.repository.CommonTokenRepository;
import com.socialuni.sdk.dao.repository.SocialuniUserExpandRepository;
import com.socialuni.sdk.dao.repository.UserRepository;
import com.socialuni.sdk.dao.repository.user.SocialUserAccountRepository;
import com.socialuni.sdk.dao.repository.user.SocialUserViolationRepository;
import com.socialuni.sdk.dao.repository.user.identity.SocialUserIdentityAuthRepository;
import com.socialuni.social.web.sdk.exception.SocialNullUserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class SocialuniUserExpandUtil {

    static SocialuniUserExpandRepository socialuniUserExpandRepository;

    @Resource
    public void setSocialuniUserExpandRepository(SocialuniUserExpandRepository socialuniUserExpandRepository) {
        SocialuniUserExpandUtil.socialuniUserExpandRepository = socialuniUserExpandRepository;
    }


    public static SocialuniUserExpandDO getUserExpandDO(Integer userId) {
        SocialuniUserExpandDO socialuniUserExpandDO = socialuniUserExpandRepository.findByUserId(userId);
        return socialuniUserExpandDO;
    }

    public static String getUserSchoolName(Integer userId) {
        SocialuniUserExpandDO socialuniUserExpandDO = socialuniUserExpandRepository.findByUserId(userId);
        if (socialuniUserExpandDO == null) {
            return null;
        }
        return socialuniUserExpandDO.getSchoolName();
    }
}
