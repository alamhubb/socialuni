package com.socialuni.social.sdk.dao.store;

import com.socialuni.social.sdk.dao.DO.user.SocialUserDeviceLoginHistoryDO;
import com.socialuni.social.sdk.dao.repository.user.SocialUserDeviceLoginHistoryRepository;
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