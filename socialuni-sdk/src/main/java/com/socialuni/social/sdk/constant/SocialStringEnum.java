package com.socialuni.social.sdk.constant;


import com.socialuni.social.common.sdk.exception.exception.SocialParamsException;
import lombok.Data;

import java.util.Map;

@Data
public class SocialStringEnum {
    /**
     * 枚举值
     */
    private final String value;
    /**
     * 枚举中文名
     */
    private final String name;


    public SocialStringEnum(String value) {
        this.value = value;
        this.name = value;
    }

    public SocialStringEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public static SocialStringEnum getEnum(Map<String, SocialStringEnum> map, String value) {
        SocialStringEnum stringEnum = map.get(value);
        if (stringEnum == null) {
            throw new SocialParamsException("错误的枚举值：" + value);
        }
        return stringEnum;
    }

    public static String getEnumName(Map<String, SocialStringEnum> map, String value) {
        return SocialStringEnum.getEnum(map, value).getName();
    }
}
