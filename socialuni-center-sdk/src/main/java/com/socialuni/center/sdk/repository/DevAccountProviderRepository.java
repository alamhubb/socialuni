package com.socialuni.center.sdk.repository;


import com.socialuni.entity.model.DevAccountProviderDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DevAccountProviderRepository extends JpaRepository<DevAccountProviderDO, Integer> {

    //    @Cacheable(cacheNames = "getDevAccountByAppIdAndProvider")
    Optional<DevAccountProviderDO> findFirstByAppId(String appId);

    DevAccountProviderDO findFirstByAppIdAndMpType(String appId, String provider);

//    Optional<DevAccountProviderDO> findFirstByAppIdAndMpTypeAndStatus(String appId, String provider, String status);

    DevAccountProviderDO findOneByDevIdAndMpTypeAndStatus(Integer devId, String mpType, String status);
}
