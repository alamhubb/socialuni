package com.socialuni.social.tance.dev.service;


import com.socialuni.social.common.api.model.SocialuniAppConfigBO;
import com.socialuni.social.common.api.model.SocialuniAppMoreConfigBO;

public interface SocialuniAppConfigInfoInterface {
    default SocialuniAppConfigBO getAppConfig() {
        return new SocialuniAppConfigBO();
    }

    default SocialuniAppMoreConfigBO getAppMoreConfig() {
        return new SocialuniAppMoreConfigBO();
    }
}
