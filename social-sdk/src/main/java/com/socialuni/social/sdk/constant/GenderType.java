package com.socialuni.social.sdk.constant;

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

    //全部，所有性别用户，所有可见性别talk
    public static final GenderTypeQueryVO allVO = new GenderTypeQueryVO(GenderType.all, GenderType.all);
    //女生发布，女生用户，所有可见性别talk
    public static final GenderTypeQueryVO girlVO = new GenderTypeQueryVO(GenderType.girl, GenderType.all);
    //仅女生可见，女生用户，仅女生可见talk
    public static final GenderTypeQueryVO onlyGirlVO = new GenderTypeQueryVO(GenderType.girl, GenderType.girl);
    //男生发布，男生用户，所有可见性别talk
    public static final GenderTypeQueryVO boyVO = new GenderTypeQueryVO(GenderType.boy, GenderType.all);
    //仅男生可见，男生用户，仅男生可见talk
    public static final GenderTypeQueryVO onlyBoyVO = new GenderTypeQueryVO(GenderType.boy, GenderType.boy);

    //查询时使用，获取gender对应的userGender和talkGender
    public static final HashMap<String, GenderTypeQueryVO> GenderTypeQueryMap = new HashMap<String, GenderTypeQueryVO>() {{
        put(GenderType.all, GenderType.allVO);
        put(GenderType.girl, GenderType.girlVO);
        put(GenderType.onlyGirl, GenderType.onlyGirlVO);
        put(GenderType.boy, GenderType.boyVO);
        put(GenderType.onlyBoy, GenderType.onlyBoyVO);
    }};

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
}
