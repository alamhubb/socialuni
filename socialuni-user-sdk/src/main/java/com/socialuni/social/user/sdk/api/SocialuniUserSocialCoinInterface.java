package com.socialuni.social.user.sdk.api;

import com.socialuni.social.user.sdk.model.SocialuniUserSocialCoinModel;

public interface SocialuniUserSocialCoinInterface {
    SocialuniUserSocialCoinModel findByUserId(Integer userId);
    SocialuniUserSocialCoinModel savePut(SocialuniUserSocialCoinModel socialuniUserExpandDO);

}

