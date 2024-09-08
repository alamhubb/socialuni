package com.socialuni.social.tance.dev.service;


import com.socialuni.social.common.api.model.SocialuniAppConfigBO;
import com.socialuni.social.common.api.model.SocialuniAppMoreConfigBO;

public interface SocialuniAppConfigInfoInterface {
    SocialuniAppConfigBO getAppConfig(Integer devId);

    SocialuniAppMoreConfigBO getAppMoreConfig(Integer devId);
}
