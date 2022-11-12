package com.socialuni.social.tance.sdk.facade;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.tance.sdk.api.ConfigInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wulinghui
 * @version 1.0
 * @module Socialuni
 * @date 2022/10/19 10:37
 * @since 1.0
 */
@Component
public class ConfigFacade {
    private static ConfigInterface configApi;

    public static ConfigInterface getConfigApi() {
        return configApi;
    }

    @Autowired
    public void setConfigApi(ConfigInterface configApi) {
        ConfigFacade.configApi = configApi;
    }

    /**
     * 从配置中格式化字符串内容后，抛出{@code SocialBusinessException} 异常。
     * 通常使用：throwBusinessException("this is {0} for {1}", "qq", "homeUrl") =》 this is 配置中的qq for 配置中的homeUrl<br>
     * @param pattern
     * @param keys
     * @see #getFormat
     * @see SocialBusinessException
     */
    public static void throwBusinessException(CharSequence pattern, String... keys) throws SocialBusinessException{
        String format = getFormat(pattern, keys);
        throw new SocialBusinessException(format);
    }

    /**
     * 从配置中格式化字符串内容.
     * 通常使用：throwBusinessException("this is {0} for {1}", "qq", "homeUrl") =》 this is 配置中的qq for 配置中的homeUrl<br>
     * @param pattern
     * @param keys
     * @return
     * @see StrUtil#indexedFormat
     */
    public static String getFormat(CharSequence pattern, String... keys) {
        Object objects [] = null;
        if(ArrayUtil.isNotEmpty(keys)){
            objects = new Object[keys.length];
            for (int i = 0; i < keys.length; i++) {
                keys[i] = configApi.getString( keys[i]);
            }

        }
        String format = StrUtil.indexedFormat(pattern, objects);
        return format;
    }
}
