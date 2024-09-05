package com.socialuni.social.tance.dev.util;

import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.tance.dev.facade.DevAccountFacade;
import com.socialuni.social.tance.dev.model.DO.SocialuniThirdTokenDO;

public class SocialuniThirdTokenUtil {
    public static void createdThirdToken(Long userId, String token, Integer devId) {
        SocialuniThirdTokenDO socialuniThirdTokenDO = new SocialuniThirdTokenDO();
        socialuniThirdTokenDO.setUserId(userId);
        socialuniThirdTokenDO.setToken(token);
        socialuniThirdTokenDO.setDevId(DevAccountFacade.getCenterDevIdNotNull());

        SocialuniRepositoryFacade.save(socialuniThirdTokenDO);
    }

    public static void getThird
}
