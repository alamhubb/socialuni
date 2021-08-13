package com.socialuni.social.sdk.constant;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 10:06
 */
public class VisibleType {
    //系统
    public static final String fullNetwork = "fullNetwork";
        public static final String smartRecommend = "smartRecommend";
    public static final String selfSoft = "selfSoft";

    public static final List<String> values = Arrays.asList(VisibleType.fullNetwork, VisibleType.selfSoft);

    //兼容使用，兼容旧版本智能推荐
    public static final HashMap<String, String> VisibleTypeOldMap = new HashMap<String, String>() {{
        put(VisibleType.smartRecommend, VisibleType.fullNetwork);
    }};
}
