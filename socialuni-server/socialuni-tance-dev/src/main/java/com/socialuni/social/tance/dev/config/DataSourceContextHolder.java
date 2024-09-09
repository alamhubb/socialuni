package com.socialuni.social.tance.dev.config;

import com.socialuni.social.tance.dev.facade.DevAccountFacade;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class DataSourceContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

/*    public static void setDataSourceType(String dataSourceType) {
        log.info("设置当前数据库：{}", dataSourceType);
        contextHolder.set(dataSourceType);
    }*/

    public static String getDataSourceType() {
        if (DevAccountFacade.hasDevKey()) {
            return "center";
        } else {
            return "child";
        }
//        return DevAccountFacade.hasDevKey();
    }

    public static void clearDataSourceType() {
        contextHolder.remove();
    }
}