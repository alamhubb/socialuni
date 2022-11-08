package com.socialuni.social.sdk.constant;


import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import lombok.Data;

import java.util.Map;

@Data
public class SocialNumEnum {
    /**
     * 枚举值
     */
    private final Integer value;
    /**
     * 枚举中文名
     */
    private final String name;


    public SocialNumEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public static SocialNumEnum getEnum(Map<Integer, SocialNumEnum> map, Integer value) {
        SocialNumEnum stringEnum = map.get(value);
        if (stringEnum == null) {
            throw new SocialParamsException("错误的枚举值：" + value);
        }
        return stringEnum;
    }

    public static String getEnumName(Map<Integer, SocialNumEnum> map, Integer value) {
        return SocialNumEnum.getEnum(map, value).getName();
    }
}
