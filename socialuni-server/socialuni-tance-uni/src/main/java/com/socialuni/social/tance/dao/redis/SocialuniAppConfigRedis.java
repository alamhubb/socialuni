package com.socialuni.social.tance.dao.redis;

import com.socialuni.social.common.api.model.SocialuniAppConfigBO;
import com.socialuni.social.tance.model.DO.AppConfigDO;
import com.socialuni.social.tance.repository.AppConfigRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialuniAppConfigRedis {
    @Resource
    AppConfigRepository appConfigRepository;

    @Cacheable(value = "getSocialuniAppConfigBO", key = "#devId")
    public SocialuniAppConfigBO getAppConfig(Integer devId) {
        SocialuniAppConfigBO socialuniAppConfigBO = new SocialuniAppConfigBO();
//        socialuniAppConfigBO.setSystemUserPhoneNum(appConfigRepository.getString("systemUserPhoneNum"));
        return socialuniAppConfigBO;
    }

}
