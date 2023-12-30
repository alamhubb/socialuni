package com.socialuni.social.common.sdk.config;

import java.util.HashMap;
import java.util.Map;

public class AppStaticData {
    private static final Map<String, Integer> ipMap = new HashMap<>();

    public static void ipCountPlus(String ip) {
        Integer ipCount = AppStaticData.ipMap.get(ip);
        if (ipCount == null) {
            AppStaticData.ipMap.put(ip, 1);
        } else {
            AppStaticData.ipMap.put(ip, ++ipCount);
        }
    }

    public static Integer getIpCount(String ip) {
        Integer ipCount = AppStaticData.ipMap.get(ip);
        return ipCount == null ? 0 : ipCount;
    }
}
