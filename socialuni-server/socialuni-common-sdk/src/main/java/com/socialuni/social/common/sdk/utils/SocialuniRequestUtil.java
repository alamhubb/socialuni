package com.socialuni.social.common.sdk.utils;

import com.socialuni.social.common.api.constant.SocialWebHeaderName;
import com.socialuni.social.common.api.utils.RequestUtil;
import com.socialuni.social.common.sdk.model.RO.SocialuniRectangleRO;
import com.socialuni.social.common.sdk.utils.PositionUtil;
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
        SocialuniRectangleRO rectangleVO = PositionUtil.getRectangle();
        if (rectangleVO != null) {
            RequestUtil.setAttribute(SocialWebHeaderName.socialuniCityLat, rectangleVO.getLat().toString());
            RequestUtil.setAttribute(SocialWebHeaderName.socialuniCityLon, rectangleVO.getLon().toString());
            RequestUtil.setAttribute(SocialWebHeaderName.socialuniCityAdCode, rectangleVO.getAdCode());
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
