package com.socialuni.social.user.sdk.dao.store;

import com.socialuni.social.user.sdk.dao.DO.SocialUserDeviceLoginHistoryDO;
import com.socialuni.social.user.sdk.dao.repository.SocialUserDeviceLoginHistoryRepository;
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