package com.socialuni.social.common.api.constant;

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

    //修改用户性别时校验使用
    public static final HashMap<String, String> GenderTypeUserEditMap = new HashMap<String, String>() {{
        put(GenderType.girl, GenderType.girl);
        put(GenderType.boy, GenderType.boy);
    }};

    public static final List<String> onlyGenders = Arrays.asList(onlyBoy, onlyGirl);

    public static final List<String> genderTypeList = Arrays.asList(GenderType.boy, GenderType.girl);
}
