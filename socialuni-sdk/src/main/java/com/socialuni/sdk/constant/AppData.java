package com.socialuni.sdk.constant;

import com.socialuni.sdk.model.RO.app.SocialDistrictRO;

import java.util.List;

public class AppData {
    private static List<SocialDistrictRO> hotDistricts;
    private static List<SocialDistrictRO> allDistricts;

    public static List<SocialDistrictRO> getHotDistricts() {
        return hotDistricts;
    }

    public static void setHotDistricts(List<SocialDistrictRO> hotDistricts) {
        AppData.hotDistricts = hotDistricts;
    }

    public static List<SocialDistrictRO> getAllDistricts() {
        return allDistricts;
    }

    public static void setAllDistricts(List<SocialDistrictRO> allDistricts) {
        AppData.allDistricts = allDistricts;
    }
}
