package com.socialuni.social.common.sdk.utils;

import com.qingchi.qing.constant.SocialWebHeaderName;
import com.socialuni.social.common.sdk.model.RO.SocialuniRectangleRO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class SocialuniRequestUtil {
    public static String getCityLat() {
        String lat = com.socialuni.social.common.api.utils.SocialuniRequestUtil.getRequestValue(SocialWebHeaderName.socialuniCityLat);
        if (StringUtils.isEmpty(lat)) {
            resetReqeustPositonByIp();
        }
        return com.socialuni.social.common.api.utils.SocialuniRequestUtil.getRequestValue(SocialWebHeaderName.socialuniCityLat);
    }

    private static void resetReqeustPositonByIp() {
        SocialuniRectangleRO rectangleVO = PositionUtil.getRectangle();
        if (rectangleVO != null) {
            com.socialuni.social.common.api.utils.SocialuniRequestUtil.setAttribute(SocialWebHeaderName.socialuniCityLat, rectangleVO.getLat().toString());
            com.socialuni.social.common.api.utils.SocialuniRequestUtil.setAttribute(SocialWebHeaderName.socialuniCityLon, rectangleVO.getLon().toString());
            com.socialuni.social.common.api.utils.SocialuniRequestUtil.setAttribute(SocialWebHeaderName.socialuniCityAdCode, rectangleVO.getAdCode());
        }
    }


    public static String getCityLon() {
        String lon = com.socialuni.social.common.api.utils.SocialuniRequestUtil.getRequestValue(SocialWebHeaderName.socialuniCityLon);
        if (StringUtils.isEmpty(lon)) {
            resetReqeustPositonByIp();
        }
        return com.socialuni.social.common.api.utils.SocialuniRequestUtil.getRequestValue(SocialWebHeaderName.socialuniCityLon);
    }

    public static String getCityAdCode() {
        String adCode = com.socialuni.social.common.api.utils.SocialuniRequestUtil.getRequestValue(SocialWebHeaderName.socialuniCityAdCode);
        if (StringUtils.isEmpty(adCode)) {
            resetReqeustPositonByIp();
        }
        return com.socialuni.social.common.api.utils.SocialuniRequestUtil.getRequestValue(SocialWebHeaderName.socialuniCityAdCode);
    }
}
