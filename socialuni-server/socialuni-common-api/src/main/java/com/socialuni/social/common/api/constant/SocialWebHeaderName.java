package com.socialuni.social.common.api.constant;

import lombok.Data;

@Data
public class SocialWebHeaderName {
    private static String baseName = "socialuni_";
    public static String deviceUidKey = SocialWebHeaderName.baseName + "deviceUid";
    public static String systemHeaderName = "system";
    public static String platformHeaderName = "platform";
    public static String providerHeaderName = "provider";
    public static String socialuniCityAdCode = "socialuniCityAdCode";
    public static String socialuniCityLon = "socialuniCityLon";
    public static String socialuniCityLat = "socialuniCityLat";
    public static String requestIpHeaderName = "requestIp";
}
