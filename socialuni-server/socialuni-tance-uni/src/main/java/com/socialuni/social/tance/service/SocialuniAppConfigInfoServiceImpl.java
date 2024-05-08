package com.socialuni.social.tance.service;


import com.socialuni.social.common.api.model.SocialuniAppConfigBO;
import com.socialuni.social.common.api.model.SocialuniAppMoreConfigBO;
import com.socialuni.social.tance.dao.redis.SocialuniAppConfigRedis;
import com.socialuni.social.tance.sdk.service.SocialuniAppConfigInfoInterface;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Primary
public class SocialuniAppConfigInfoServiceImpl implements SocialuniAppConfigInfoInterface {
    @Resource
    private SocialuniAppConfigRedis socialuniAppConfigRedis;

    public SocialuniAppConfigBO getAppConfig(Integer devId) {
        return socialuniAppConfigRedis.getAppConfig(devId);
    }

    public SocialuniAppMoreConfigBO getAppMoreConfig(Integer devId) {
        return socialuniAppConfigRedis.getAppConfigMore(devId);
    }
}
