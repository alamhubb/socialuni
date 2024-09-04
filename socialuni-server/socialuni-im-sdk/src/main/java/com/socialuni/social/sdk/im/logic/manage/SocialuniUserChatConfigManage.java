package com.socialuni.social.sdk.im.logic.manage;

import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.common.sdk.dao.facede.SocialuniUserRepositoryFacede;
import com.socialuni.social.sdk.im.dao.DO.SocialuniUserChatConfigDO;

public class SocialuniUserChatConfigManage {

    public static SocialuniUserChatConfigDO getOrCreateUserChatConfigDO(Long userId) {
        SocialuniUserChatConfigDO socialuniUserChatConfigDO = SocialuniUserRepositoryFacede.findFirstByUserIdOrderByIdDesc(userId, SocialuniUserChatConfigDO.class);

        if (socialuniUserChatConfigDO == null) {
            socialuniUserChatConfigDO = new SocialuniUserChatConfigDO();
            socialuniUserChatConfigDO.setUserId(userId);
            socialuniUserChatConfigDO = SocialuniRepositoryFacade.save(socialuniUserChatConfigDO);
        }
        return socialuniUserChatConfigDO;
    }
}
