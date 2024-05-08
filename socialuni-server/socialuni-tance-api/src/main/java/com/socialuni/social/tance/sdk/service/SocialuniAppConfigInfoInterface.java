package com.socialuni.social.tance.sdk.service;


import com.socialuni.social.common.api.model.SocialuniAppConfigBO;
import com.socialuni.social.common.api.model.SocialuniAppMoreConfigBO;

public interface SocialuniAppConfigInfoInterface {
    default SocialuniAppConfigBO getAppConfig(Integer devId) {
        return new SocialuniAppConfigBO();
    }

    default SocialuniAppMoreConfigBO getAppMoreConfig(Integer devId) {
        return new SocialuniAppMoreConfigBO();
    }
}
