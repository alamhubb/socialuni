package com.socialuni.social.user.sdk.repository;

import com.socialuni.social.user.sdk.dao.DO.SocialUserDeviceLoginHistoryDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialUserDeviceLoginHistoryRepository extends JpaRepository<SocialUserDeviceLoginHistoryDO, Integer> {
}