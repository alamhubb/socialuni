package com.socialuni.sdk.constant.socialuni;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 10:06
 */
public class GenderType {
    public static final String girlTagTypeName = "女生专属";
    public static final String girlTagName = "女生世界";
    public static final String boyTagName = "男生世界";
    public static final String boyTagTypeName = "男生专属";

    //全国
    public static final String all = "all";
    public static final String girl = "girl";
    public static final String boy = "boy";
    public static final String notFilled = "";

    //查询的时候选择的，可以选择仅女生，仅男生
    public static final String onlyGirl = "onlyGirl";
    public static final String onlyBoy = "onlyBoy";

    public static final String allOld = "全部";
    public static final String girlOld = "女";
    public static final String boyOld = "男";

    //校验普通的genderType，queryTalk，addTalk，addTag，校验visibleGender
    public static final HashMap<String, String> GenderTypeMap = new HashMap<String, String>() {{
        put(GenderType.all, GenderType.all);
        put(GenderType.girl, GenderType.girl);
        put(GenderType.boy, GenderType.boy);
    }};

    //修改用户性别时校验使用
    public static final HashMap<String, String> GenderTypeUserEditMap = new HashMap<String, String>() {{
        put(GenderType.girl, GenderType.girl);
        put(GenderType.boy, GenderType.boy);
    }};

    //兼容使用，记录了旧版gender值，对应得新版值,talk查询时使用
    public static final HashMap<String, String> GenderTypeTalkQueryOldMap = new HashMap<String, String>() {{
        put(GenderType.allOld, GenderType.all);
        put(GenderType.girlOld, GenderType.girl);
        put(GenderType.boyOld, GenderType.boy);
    }};

    //兼容使用，记录了旧版gender值，对应得新版值，用户修改时使用
    public static final HashMap<String, String> GenderTypeUserEditOldMap = new HashMap<String, String>() {{
        put(GenderType.girlOld, GenderType.girl);
        put(GenderType.boyOld, GenderType.boy);
    }};

    public static final List<String> genders = Arrays.asList(girl, boy);
    public static final List<String> onlyGenders = Arrays.asList(onlyBoy, onlyGirl);
}
