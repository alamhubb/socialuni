package com.socialuni.social.common.sdk.dao.store;

import com.socialuni.social.common.sdk.dao.repository.user.SocialUserDeviceLoginHistoryRepository;
import com.socialuni.social.common.sdk.dao.DO.user.SocialUserDeviceLoginHistoryDO;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialUserDeviceLoginHistoryStore {
    @Resource
    SocialUserDeviceLoginHistoryRepository socialUserDeviceLoginHistoryRepository;

    @Async
    public void saveAsync(SocialUserDeviceLoginHistoryDO socialUserDeviceLoginHistoryDO) {
        socialUserDeviceLoginHistoryRepository.save(socialUserDeviceLoginHistoryDO);
    }
}