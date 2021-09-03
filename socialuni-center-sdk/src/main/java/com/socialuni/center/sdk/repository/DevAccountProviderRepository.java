package com.socialuni.center.sdk.repository;


import com.socialuni.entity.model.DevAccountProviderDO;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DevAccountProviderRepository extends JpaRepository<DevAccountProviderDO, Integer> {

    //    @Cacheable(cacheNames = "getDevAccountByAppIdAndProvider")
//    Optional<DevAccountProviderDO> findFirstByAppId(String appId);
    @CachePut(cacheNames = "getDevAccountProviderByAppIdAndMpType")
    DevAccountProviderDO save(DevAccountProviderDO devAccountProviderDO);

    @Cacheable(cacheNames = "getDevAccountProviderByAppIdAndMpType")
    DevAccountProviderDO findOneByAppIdAndMpType(String appId, String mpType);

//    Optional<DevAccountProviderDO> findFirstByAppIdAndMpTypeAndStatus(String appId, String provider, String status);

    DevAccountProviderDO findOneByDevIdAndMpType(Integer devId, String mpType);
//    DevAccountProviderDO findOneByDevIdAndMpType(Integer devId, String mpType, String status);
}
