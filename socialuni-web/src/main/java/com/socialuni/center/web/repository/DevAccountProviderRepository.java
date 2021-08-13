package com.socialuni.center.web.repository;


import com.socialuni.entity.model.DevAccountProviderDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DevAccountProviderRepository extends JpaRepository<DevAccountProviderDO, Integer> {

    //    @Cacheable(cacheNames = "getDevAccountByAppIdAndProvider")
    Optional<DevAccountProviderDO> findFirstByAppId(String appId);

    Optional<DevAccountProviderDO> findFirstByAppIdAndMpType(String appId, String provider);

    Optional<DevAccountProviderDO> findFirstByPlatformAndAppIdAndMpTypeAndStatus(String platform, String appId, String provider, String status);

    Optional<DevAccountProviderDO> findFirstByAppIdAndMpTypeAndStatus(String appId, String provider, String status);

    DevAccountProviderDO findByDevIdAndMpTypeAndStatus(Integer devId, String mpType, String status);

    Optional<DevAccountProviderDO> findFirstBy();

}
