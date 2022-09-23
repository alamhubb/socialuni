package com.socialuni.social.sdk.dao.repository.dev;


import com.socialuni.social.sdk.dao.DO.dev.DevAccountProviderDO;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DevAccountProviderRepository extends JpaRepository<DevAccountProviderDO, Integer> {

    //    @Cacheable(cacheNames = "getDevAccountByAppIdAndProvider")
//    Optional<DevAccountProviderDO> findFirstByAppId(String appId);
    @Caching(
            put = {
                    @CachePut(cacheNames ="getDevAccountProviderByAppIdAndMpType", key = "#devAccountProviderDO.mpType+'-'+#devAccountProviderDO.appId"),
                    @CachePut(cacheNames ="getDevAccountProviderByDevIdAndMpType", key = "#devAccountProviderDO.mpType+'-'+#devAccountProviderDO.devId"),
            }
    )
    DevAccountProviderDO save(DevAccountProviderDO devAccountProviderDO);

    List<DevAccountProviderDO> findAllByDevId(Integer devId);

    @Cacheable(cacheNames = "getDevAccountProviderByAppIdAndMpType",key = "#mpType+'-'+#appId")
    DevAccountProviderDO findOneByAppIdAndMpType(String appId, String mpType);

//    Optional<DevAccountProviderDO> findFirstByAppIdAndMpTypeAndStatus(String appId, String provider, String status);

    @Cacheable(cacheNames = "getDevAccountProviderByDevIdAndMpType",key = "#mpType+'-'+#devId")
    DevAccountProviderDO findOneByDevIdAndMpType(Integer devId, String mpType);
//    DevAccountProviderDO findOneByDevIdAndMpType(Integer devId, String mpType, String status);
}
