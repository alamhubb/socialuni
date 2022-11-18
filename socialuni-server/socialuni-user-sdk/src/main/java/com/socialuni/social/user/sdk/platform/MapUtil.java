package com.socialuni.social.user.sdk.platform;

import com.socialuni.social.common.api.utils.IpUtil;
import com.socialuni.social.user.sdk.model.RO.LocationQueryRO;
import com.socialuni.social.user.sdk.model.RO.RectangleVO;

public class MapUtil {
    public static RectangleVO getRectangle() {
        String ip = IpUtil.getIpAddr();
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {  //如果是WinDOws系统
            ip = "125.33.221.85";
        }
        LocationQueryRO locationQueryRO = QQMapAPI.getIpLocation(ip);
        if (locationQueryRO == null || locationQueryRO.getLocation() == null) {
            return null;
        }
        RectangleVO rectangleVO = new RectangleVO();
        rectangleVO.setLon(locationQueryRO.getLocation().getLng());
        rectangleVO.setLat(locationQueryRO.getLocation().getLat());
        rectangleVO.setAdCode(locationQueryRO.getAdcode());
        return rectangleVO;
    }
}