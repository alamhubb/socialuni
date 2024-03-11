package com.socialuni.social.common.api.config;

import com.socialuni.social.common.api.model.SocialuniAppConfigBO;
import com.socialuni.social.common.api.model.SocialuniAppMoreConfigBO;

public interface SocialuniAppConfigInterface {
    default SocialuniAppConfigBO getAppConfig() {
        return new SocialuniAppConfigBO();
    }

    default SocialuniAppMoreConfigBO getAppMoreConfig() {
        return new SocialuniAppMoreConfigBO();
    }
}
