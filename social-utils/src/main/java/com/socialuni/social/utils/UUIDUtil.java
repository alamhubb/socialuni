package com.socialuni.social.utils;

import java.util.UUID;

public class UUIDUtil {
    /**
     * 生成token
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}