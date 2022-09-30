package com.socialuni.social.tance.sdk.api;


import com.socialuni.social.tance.sdk.model.DevAccountProviderModler;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DevAccountProviderApi {


    DevAccountProviderModler save(DevAccountProviderModler devAccountProviderModler);

    List<DevAccountProviderModler> findAllByDevId(Integer devId);

    DevAccountProviderModler findOneByAppIdAndMpType(String appId, String mpType);

//    Optional<DevAccountProviderDO> findFirstByAppIdAndMpTypeAndStatus(String appId, String provider, String status);

    DevAccountProviderModler findOneByDevIdAndMpType(Integer devId, String mpType);
//    DevAccountProviderDO findOneByDevIdAndMpType(Integer devId, String mpType, String status);
}
