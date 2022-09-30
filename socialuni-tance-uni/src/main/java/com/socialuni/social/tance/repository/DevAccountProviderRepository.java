package com.socialuni.social.tance.repository;


import com.socialuni.social.tance.entity.DevAccountProviderDo;
import com.socialuni.social.tance.sdk.api.DevAccountProviderApi;
import com.socialuni.social.tance.sdk.model.DevAccountProviderModler;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DevAccountProviderRepository extends JpaRepository<DevAccountProviderDo, Integer>, DevAccountProviderApi {

    //    @Cacheable(cacheNames = "getDevAccountByAppIdAndProvider")
//    Optional<DevAccountProviderDO> findFirstByAppId(String appId);
    @Caching(
            put = {
                    @CachePut(cacheNames ="getDevAccountProviderByAppIdAndMpType", key = "#devAccountProviderModler.mpType+'-'+#devAccountProviderModler.appId"),
                    @CachePut(cacheNames ="getDevAccountProviderByDevIdAndMpType", key = "#devAccountProviderModler.mpType+'-'+#devAccountProviderModler.devId"),
            }
    )
    DevAccountProviderModler save(DevAccountProviderModler devAccountProviderModler);

    List<DevAccountProviderModler> findAllByDevId(Integer devId);

    @Cacheable(cacheNames = "getDevAccountProviderByAppIdAndMpType",key = "#mpType+'-'+#appId")
    DevAccountProviderModler findOneByAppIdAndMpType(String appId, String mpType);

//    Optional<DevAccountProviderDO> findFirstByAppIdAndMpTypeAndStatus(String appId, String provider, String status);

    @Cacheable(cacheNames = "getDevAccountProviderByDevIdAndMpType",key = "#mpType+'-'+#devId")
    DevAccountProviderModler findOneByDevIdAndMpType(Integer devId, String mpType);
//    DevAccountProviderDO findOneByDevIdAndMpType(Integer devId, String mpType, String status);
}
