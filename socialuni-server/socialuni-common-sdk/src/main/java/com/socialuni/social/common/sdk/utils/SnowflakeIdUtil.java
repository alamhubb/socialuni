package com.socialuni.social.common.sdk.utils;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 生成id的工具类。可以根据id获得对应的主机mac地址和 所属那个联盟中心
 *
 * @author wulinghui
 * @version 1.0
 * @module Socialuni
 * @date 2022/11/14 14:55
 * @since 1.0
 */
@Component
public class SnowflakeIdUtil {
    private final static Snowflake snowflake = new Snowflake(1, 1);

    public static long nextId() {
        return snowflake.nextId();
    }

    public static String nextIdStr() {
        return snowflake.nextIdStr();
    }
}
