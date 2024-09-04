package com.socialuni.social.common.sdk.component;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 生成id的工具类。可以根据id获得对应的主机mac地址和 所属那个联盟中心
 * @author wulinghui
 * @version 1.0
 * @module Socialuni
 * @date 2022/11/14 14:55
 * @since 1.0
 */
@Component
public class SnowflakeIdComponent {

    private Snowflake snowflake;
    @Value("${socialuni.app.data-center-id:1}")
    public void setSnowflake(long datacenterId) {
        // mac地址   和   设置的中心服务器。
        this.snowflake = new Snowflake( IdUtil.getDataCenterId(~(-1L << 5L)), datacenterId );
    }

    public long getWorkerId(long id) {
        return snowflake.getWorkerId(id);
    }

    public long getDataCenterId(long id) {
        return snowflake.getDataCenterId(id);
    }

    public long getGenerateDateTime(long id) {
        return snowflake.getGenerateDateTime(id);
    }

    public long nextId() {
        return snowflake.nextId();
    }

    public String nextIdStr() {
        return snowflake.nextIdStr();
    }
}
