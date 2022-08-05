package com.socialuni.center.web.controller;

import com.socialuni.api.feignAPI.SocialuniLocationAPI;
import com.socialuni.api.model.QO.location.LocationQueryQO;
import com.socialuni.api.model.QO.location.LocationQueryRO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.center.web.platform.QQMapAPI;
import com.socialuni.social.web.sdk.utils.IpUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CenterLocationController implements SocialuniLocationAPI {
    @Override
    public ResultRO<LocationQueryRO> queryLocation(LocationQueryQO queryQO) {
        LocationQueryRO locationQueryRO;
        //经纬度查询
        if (queryQO.getLatitude() != null) {
            locationQueryRO = QQMapAPI.geocoder(queryQO);
            //ip查询
        } else if (StringUtils.isNotEmpty(queryQO.getIp())) {
            locationQueryRO = QQMapAPI.getIpLocation(queryQO.getIp());
            //requestIp查询
        } else {
            String ip = IpUtil.getIpAddr();
            locationQueryRO = QQMapAPI.getIpLocation(ip);
        }
        return ResultRO.success(locationQueryRO);
    }
}
