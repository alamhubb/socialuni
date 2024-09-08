package com.socialuni.social.tance.dev.config;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataSourceContext {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setCurrentDataSource(String dataSourceType) {
        log.info("设置当前数据库：{}", dataSourceType);
        contextHolder.set(dataSourceType);
    }

    public static String getCurrentDataSource() {
        log.info("获取：{}", contextHolder.get());
        return contextHolder.get();
    }

    public static void clearDataSource() {
        contextHolder.remove();
    }
}