package com.socialuni.sdk.dao.repository.user;

import com.socialuni.sdk.model.DO.user.SocialUserDeviceLoginHistoryDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialUserDeviceLoginHistoryRepository extends JpaRepository<SocialUserDeviceLoginHistoryDO, Integer> {
}