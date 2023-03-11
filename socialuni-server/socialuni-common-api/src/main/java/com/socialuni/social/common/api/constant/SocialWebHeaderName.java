package com.socialuni.social.common.api.constant;

import lombok.Data;

@Data
public class SocialWebHeaderName {
    public static String systemHeaderName = "system";
    public static String platformHeaderName = "platform";
    public static String providerHeaderName = "provider";
    public static String city_adCode = "city_adCode";
    public static String city_lon = "city_lon";
    public static String city_lat = "city_lat";
    public static String requestIpHeaderName = "requestIp";
}
