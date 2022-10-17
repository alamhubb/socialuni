package com.socialuni.social.tance.service;

import cn.hutool.extra.spring.SpringUtil;
import com.socialuni.social.tance.sdk.api.ConfigApi;
import org.springframework.stereotype.Component;

/**
 * @author wulinghui
 * @version 1.0
 * @module Socialuni
 * @date 2022/10/15 23:43
 * @since 1.0
 */
@Component
public class ConfigService implements ConfigApi {

    @Override
    public String getString(Integer devId, String key) {
        // 不支持devKey的。就只配置自己的配置内容。
        return SpringUtil.getProperty("socialuni."+key);
    }
}
