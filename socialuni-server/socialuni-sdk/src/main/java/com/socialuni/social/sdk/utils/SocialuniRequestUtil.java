package com.socialuni.social.sdk.utils;

import com.socialuni.social.common.api.constant.SocialWebHeaderName;
import com.socialuni.social.common.api.utils.IpUtil;
import com.socialuni.social.common.api.utils.RequestUtil;
import com.socialuni.social.user.sdk.model.RO.LocationQueryRO;
import com.socialuni.social.user.sdk.platform.QQMapAPI;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class SocialuniRequestUtil {
    public static String getCityLat() {
        String lat = RequestUtil.getRequestValue(SocialWebHeaderName.socialuniCityLat);
        if (StringUtils.isEmpty(lat)) {
            resetReqeustPositonByIp();
        }
        return RequestUtil.getRequestValue(SocialWebHeaderName.socialuniCityLat);
    }

    private static void resetReqeustPositonByIp() {
        LocationQueryRO locationQueryRO = QQMapAPI.getIpLocation(IpUtil.getIpAddr());
        if (locationQueryRO != null && locationQueryRO.getLocation() != null) {
            RequestUtil.setAttribute(SocialWebHeaderName.socialuniCityLat, locationQueryRO.getLocation().getLat().toString());
            RequestUtil.setAttribute(SocialWebHeaderName.socialuniCityLon, locationQueryRO.getLocation().getLng().toString());
            RequestUtil.setAttribute(SocialWebHeaderName.socialuniCityAdCode, locationQueryRO.getAdcode());
        }
    }


    public static String getCityLon() {
        String lon = RequestUtil.getRequestValue(SocialWebHeaderName.socialuniCityLon);
        if (StringUtils.isEmpty(lon)) {
            resetReqeustPositonByIp();
        }
        return RequestUtil.getRequestValue(SocialWebHeaderName.socialuniCityLon);
    }

    public static String getCityAdCode() {
        String adCode = RequestUtil.getRequestValue(SocialWebHeaderName.socialuniCityAdCode);
        if (StringUtils.isEmpty(adCode)) {
            resetReqeustPositonByIp();
        }
        return RequestUtil.getRequestValue(SocialWebHeaderName.socialuniCityAdCode);
    }
}
