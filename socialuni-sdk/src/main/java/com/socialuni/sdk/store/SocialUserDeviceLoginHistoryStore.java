package com.socialuni.sdk.store;

import com.socialuni.sdk.model.DO.user.SocialUserDeviceLoginHistoryDO;
import com.socialuni.sdk.repository.user.SocialUserDeviceLoginHistoryRepository;
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