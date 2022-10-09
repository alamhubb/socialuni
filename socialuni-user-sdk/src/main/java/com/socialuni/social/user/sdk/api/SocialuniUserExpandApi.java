package com.socialuni.social.user.sdk.api;

import com.socialuni.social.user.sdk.model.SocialuniUserExpandModel;

public interface SocialuniUserExpandApi  {
    SocialuniUserExpandModel findByUserId(Integer userId);

    SocialuniUserExpandModel save(SocialuniUserExpandModel socialuniUserExpandModel);
}

