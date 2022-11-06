package com.socialuni.social.im.constant;

import com.socialuni.social.common.api.constant.PlatformType;
import com.socialuni.social.common.api.constant.SystemType;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 10:06
 */
public class OpenImPlatformType {

    private static final Integer ios = 1;
    private static final Integer android = 2;
    private static final Integer windows = 3;
    private static final Integer osx = 4;
    private static final Integer web = 5;
    private static final Integer linux = 6;


    public static Integer getOpenImPlatformType(String platformType, String system) {
        if (PlatformType.h5.equals(platformType)) {
            return OpenImPlatformType.web;
        } else if (SystemType.android.equals(system)) {
            return OpenImPlatformType.android;
        } else {
            return OpenImPlatformType.ios;
        }
    }

}
