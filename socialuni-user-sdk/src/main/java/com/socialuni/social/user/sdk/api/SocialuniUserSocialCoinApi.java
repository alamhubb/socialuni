package com.socialuni.social.user.sdk.api;

import com.socialuni.social.user.sdk.model.SocialuniUserSocialCoinModel;

public interface SocialuniUserSocialCoinApi  {
    SocialuniUserSocialCoinModel findByUserId(Integer userId);
    SocialuniUserSocialCoinModel save(SocialuniUserSocialCoinModel socialuniUserExpandDO);

}

