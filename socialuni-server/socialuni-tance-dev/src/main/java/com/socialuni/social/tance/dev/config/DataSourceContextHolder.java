package com.socialuni.social.tance.dev.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class DataSourceContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setDataSourceType(String dataSourceType) {
        log.info("设置当前数据库：{}", dataSourceType);
        contextHolder.set(dataSourceType);
    }

    public static String getDataSourceType() {
        String dataSourceType = contextHolder.get();
        log.info("获取：{}", dataSourceType);
        if (StringUtils.isEmpty(dataSourceType)){
            DataSourceContextHolder.setDataSourceType("center");
            if (SocialuniDevConfig.hasCenterServer()) {
                DataSourceContextHolder.setDataSourceType("child");
            } else {
                DataSourceContextHolder.setDataSourceType("center");
            }
        }
        log.info("获取：{}", contextHolder.get());
        return contextHolder.get();
    }

    public static void clearDataSourceType() {
        contextHolder.remove();
    }
}