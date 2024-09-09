package com.socialuni.social.user.sdk.logic.entity;

import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.user.sdk.dao.DO.SocialUserDeviceLoginHistoryDO;

public class SocialuniCreateLoginHisotryEntity {
    public static void createUserLoginHistory(Long userId, String loginType) {
        SocialUserDeviceLoginHistoryDO socialUserDeviceLoginHistoryDO = new SocialUserDeviceLoginHistoryDO();
        socialUserDeviceLoginHistoryDO.setUserId(userId);
        socialUserDeviceLoginHistoryDO.setLoginType(loginType);

        socialUserDeviceLoginHistoryDO.setSystemInfo(SocialuniRequestUtil.getSystem());
        socialUserDeviceLoginHistoryDO.setLoginProvider(SocialuniRequestUtil.getProvider());
        socialUserDeviceLoginHistoryDO.setPlatform(SocialuniRequestUtil.getPlatform());
        socialUserDeviceLoginHistoryDO.setDeviceUid(SocialuniRequestUtil.getDeviceUid());

        SocialuniRepositoryFacade.save(socialUserDeviceLoginHistoryDO);
    }
}
