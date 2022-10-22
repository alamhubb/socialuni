package com.socialuni.social.sdk.constant;

import java.util.Collections;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-06-22 22:23
 */

//什么算是公共常量，就是放到任何项目都可以用的
public class SocialuniConst {
    //默认系统用户为 11个9
    public static final String systemUserPhoneNum = "99999999999";

    public static final List<Integer> emptyIds = Collections.singletonList(0);
    public static final List<Long> emptyLongIds = Collections.singletonList(0L);

    public static final Integer initNum = 0;
    public static final String boy = "男";
    public static final String girl = "女";

    public static final Integer defaultAge = 19;
    public static final String initBirthday = "2022-01-01";
    //30个清池币1个月会员
    public static final Integer VIP_QCB_VALUE = 30;


    public static final String chinaDistrictCode = "100000";
    public static final String devEnvTagName = "测试环境";
    public static final String schoolCircleTypeName = "大学";
}
