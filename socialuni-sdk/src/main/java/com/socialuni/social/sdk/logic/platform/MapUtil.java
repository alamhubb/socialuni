package com.socialuni.social.sdk.logic.platform;

import com.socialuni.social.sdk.model.QO.location.LocationQueryRO;
import com.socialuni.social.sdk.model.RectangleVO;
import com.socialuni.social.common.utils.IpUtil;

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