package com.socialuni.social.user.sdk.logic.entity;

import com.socialuni.social.common.api.utils.RequestUtil;
import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.user.sdk.dao.DO.SocialUserDeviceLoginHistoryDO;

public class SocialuniCreateLoginHisotryEntity {
    public static void createUserLoginHistory(Integer userId, String loginType) {
        SocialUserDeviceLoginHistoryDO socialUserDeviceLoginHistoryDO = new SocialUserDeviceLoginHistoryDO();
        socialUserDeviceLoginHistoryDO.setUserId(userId);
        socialUserDeviceLoginHistoryDO.setLoginType(loginType);

        socialUserDeviceLoginHistoryDO.setSystemInfo(RequestUtil.getSystem());
        socialUserDeviceLoginHistoryDO.setLoginProvider(RequestUtil.getProvider());
        socialUserDeviceLoginHistoryDO.setPlatform(RequestUtil.getPlatform());
        socialUserDeviceLoginHistoryDO.setDeviceUid(RequestUtil.getDeviceUid());

        SocialuniRepositoryFacade.save(socialUserDeviceLoginHistoryDO);
    }
}
