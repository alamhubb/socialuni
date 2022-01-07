package com.socialuni.social.web.controller;

import com.socialuni.api.model.QO.location.LocationQueryQO;
import com.socialuni.api.model.QO.location.LocationQueryRO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.sdk.platform.QQMapAPI;
import com.socialuni.social.web.sdk.utils.IpUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("location")
public class SocialLocationController {
    @PostMapping("queryLocation")
    ResultRO<LocationQueryRO> queryLocation(@RequestBody @Valid LocationQueryQO queryQO) {
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
