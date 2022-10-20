package com.socialuni.social.user.sdk.api;

import com.socialuni.social.user.sdk.model.SocialuniUserExpandModel;

public interface SocialuniUserExpandInterface {
    SocialuniUserExpandModel findByUserId(Integer userId);

    SocialuniUserExpandModel savePut(SocialuniUserExpandModel socialuniUserExpandModel);
}

