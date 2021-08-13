package com.socialuni.social.constant;

import java.util.Arrays;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 10:06
 */
public class MatchType {
    public static final String all = "全部";
    public static final String ilike = "我喜欢的";
    public static final String likeMe = "喜欢我的";

    public static final String like = "喜欢";
    public static final String unlick = "不喜欢";
    //俩人互相喜欢，第一个人喜欢，第二个人喜欢边成待匹配，第一个人变成已匹配，第一个人说了话，第二个人也变成已匹配
    public static final String waitMatch = "待匹配";
    public static final String matched = "已匹配";
    public static final String lifted = "已解除";
    public static final String looked = "已看过";

    public static final List<String> operatedes = Arrays.asList(unlick, like);
}
