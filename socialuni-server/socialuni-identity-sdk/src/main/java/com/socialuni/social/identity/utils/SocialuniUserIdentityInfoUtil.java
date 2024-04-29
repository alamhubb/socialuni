package com.socialuni.social.identity.utils;

import com.socialuni.social.common.api.model.user.SocialuniContentUserRO;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.identity.dao.DO.SocialUserIdentityAuthDO;
import com.socialuni.social.user.sdk.constant.UserIdentityAuthStatus;
import com.socialuni.social.user.sdk.model.factory.SocialuniUserROFactory;
import com.socialuni.social.identity.dao.repository.SocialUserIdentityAuthRepository;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;

import javax.annotation.Resource;

public class SocialuniUserIdentityInfoUtil {
    static SocialUserIdentityAuthRepository socialUserIdentityAuthRepository;

    @Resource
    public void setSocialUserIdentityAuthRepository(SocialUserIdentityAuthRepository socialUserIdentityAuthRepository) {
        SocialuniUserIdentityInfoUtil.socialUserIdentityAuthRepository = socialUserIdentityAuthRepository;
    }

    public static boolean getMineUserIdentityStatus() {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();
        SocialUserIdentityAuthDO socialUserIdentityAuthDO = socialUserIdentityAuthRepository.findFirstByUserId(mineUser.getUserId());
        if (socialUserIdentityAuthDO != null) {
            return UserIdentityAuthStatus.authSuccessList.contains(socialUserIdentityAuthDO.getStatus());
        }
        return false;
    }
}
