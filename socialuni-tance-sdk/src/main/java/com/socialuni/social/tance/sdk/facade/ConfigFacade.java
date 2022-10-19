package com.socialuni.social.tance.sdk.facade;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.socialuni.social.common.exception.exception.SocialBusinessException;
import com.socialuni.social.tance.sdk.api.ConfigApi;

import javax.annotation.Resource;

/**
 * @author wulinghui
 * @version 1.0
 * @module Socialuni
 * @date 2022/10/19 10:37
 * @since 1.0
 */
public class ConfigFacade {
    private static  ConfigApi configApi;
    @Resource
    public static void setConfigApi(ConfigApi configApi) {
        ConfigFacade.configApi = configApi;
    }

    /**
     * 从配置中格式化字符串内容后，抛出{@code SocialBusinessException} 异常。
     * 通常使用：throwBusinessException("this is {0} for {1}", "qq", "homeUrl") =》 this is 配置中的qq for 配置中的homeUrl<br>
     * @param pattern
     * @param keys
     * @see StrUtil#indexedFormat
     * @see SocialBusinessException
     */
    public void throwBusinessException(CharSequence pattern, String... keys){
        Object objects [] = null;
        if(ArrayUtil.isNotEmpty(keys)){
            objects = new Object[keys.length];
            for (int i = 0; i < keys.length; i++) {
                keys[i] = configApi.getString( keys[i]);
            }

        }
        String format = StrUtil.indexedFormat(pattern, objects);
        throw new SocialBusinessException(format);
    }
}
