package com.socialuni.social.tance.dev.service;


import com.socialuni.social.common.api.model.SocialuniAppConfigBO;
import com.socialuni.social.common.api.model.SocialuniAppMoreConfigBO;
import com.socialuni.social.tance.dev.redis.SocialuniAppConfigRedis;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Primary
public class SocialuniAppConfigInfoServiceImpl implements SocialuniAppConfigInfoInterface {
    @Resource
    private SocialuniAppConfigRedis socialuniAppConfigRedis;

    @Override
    public SocialuniAppConfigBO getAppConfig(Integer devId) {
        return socialuniAppConfigRedis.getAppConfig(devId);
    }

    @Override
    public SocialuniAppMoreConfigBO getAppMoreConfig(Integer devId) {
        return socialuniAppConfigRedis.getAppConfigMore(devId);
    }
}
