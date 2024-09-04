package com.socialuni.social.common.api.utils;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class UUIDUtil {
    public static void main(String[] args) {
        System.out.println(getSnowflakeId());
        System.out.println(getSnowflakeId());
        System.out.println(getSnowflakeId());
        System.out.println(getSnowflakeId());
        System.out.println(getSnowflakeId());
        System.out.println(getSnowflakeId());
        System.out.println(getSnowflakeId());

        CompletableFuture.runAsync(() -> {
           throw new RuntimeException("haha");
        }).exceptionally(t -> {
            System.out.println("执行失败！"+t.getMessage());
            throw new RuntimeException("haha1");
        });
    }
    /**
     * 生成token
     *
     * @return
     */
    public static long getSnowflakeId() {
        return SnowflakeIdUtil.nextId();
    }

    public static String getUUIDByString(String content) {
        UUID uuid = UUID.nameUUIDFromBytes(content.getBytes());
        return uuid.toString().replace("-", "");
    }
}