package com.socialuni.social.user.sdk.constant;


import com.socialuni.social.tance.sdk.enumeration.GenderType;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 10:06
 */
public enum GenderTypeNumEnum {
    notFilled(0, GenderType.notFilled),
    male(1, GenderType.boy),
    female(2, GenderType.girl);
    /**
     * 枚举值
     */
    private final Integer value;
    /**
     * 枚举中文名
     */
    private final String name;

    GenderTypeNumEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return this.value;
    }

    public String getName() {
        return this.name;
    }

    /**
     * 从int到enum的转换函数
     *
     * @param value 整数值
     * @return 枚举值，如果没有对应的枚举返回为null
     */
    public static GenderTypeNumEnum getEnumByValue(Integer value) {
        for (GenderTypeNumEnum iEnum : GenderTypeNumEnum.values()) {
            if (value.equals(iEnum.value)) {
                return iEnum;
            }
        }
        return null;
    }

    public static String getNameByValue(Integer value) {
        return GenderTypeNumEnum.getEnumByValue(value).getName();
    }

    public static GenderTypeNumEnum getEnumByName(String name) {
        for (GenderTypeNumEnum iEnum : GenderTypeNumEnum.values()) {
            if (iEnum.name.equals(name)) {
                return iEnum;
            }
        }
        return null;
    }

    public static Integer getValueByName(String name) {
        return GenderTypeNumEnum.getEnumByName(name).getValue();
    }
}
