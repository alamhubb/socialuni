package com.socialuni.social.common.sdk.dao.repository.user;

import com.socialuni.social.common.sdk.dao.DO.user.SocialUserDeviceLoginHistoryDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialUserDeviceLoginHistoryRepository extends JpaRepository<SocialUserDeviceLoginHistoryDO, Integer> {
}