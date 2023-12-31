package com.socialuni.social.tance.sdk.config;

public interface SocialuniAppConfigInterface {
    default SocialuniAppConfigBO getAppConfig() {
        return new SocialuniAppConfigBO();
    }

    default SocialuniAppMoreConfigBO getAppMoreConfig() {
        return new SocialuniAppMoreConfigBO();
    }
}
