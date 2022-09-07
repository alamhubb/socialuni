package com.socialuni.sdk.utils;

import java.util.UUID;

public class UUIDUtil {
    public static void main(String[] args) {
        System.out.println(getUUID());
    }
    /**
     * 生成token
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}