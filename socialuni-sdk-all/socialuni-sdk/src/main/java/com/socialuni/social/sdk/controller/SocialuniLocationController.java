package com.socialuni.social.sdk.controller;

import com.socialuni.social.sdk.feignAPI.openData.SocialuniLocationAPI;
import com.socialuni.social.sdk.model.QO.location.LocationQueryQO;
import com.socialuni.social.sdk.model.QO.location.LocationQueryRO;
import com.socialuni.social.user.sdk.platform.QQMapAPI;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.utils.IpUtil;
import com.socialuni.social.tance.sdk.enumeration.SocialuniSystemConst;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("socialuni/location")
@RestController
public class SocialuniLocationController implements SocialuniLocationAPI {
    @Resource
    SocialuniLocationAPI socialuniLocationAPI;

    public ResultRO<LocationQueryRO> queryLocation(LocationQueryQO queryQO) {
        //如果应用，则调用中心
        if (SocialuniSystemConst.serverIsChild()) {
            return socialuniLocationAPI.queryLocation(queryQO);
        }
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
