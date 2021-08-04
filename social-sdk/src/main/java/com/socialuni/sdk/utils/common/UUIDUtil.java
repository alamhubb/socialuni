package com.socialuni.sdk.utils.common;

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