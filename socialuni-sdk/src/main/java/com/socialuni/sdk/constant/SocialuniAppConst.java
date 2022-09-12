package com.socialuni.sdk.constant;

import com.socialuni.sdk.constant.config.SocialuniAppType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SocialuniAppConst {
    public static String appType = SocialuniAppType.defaultType;
    //首页的tab栏目
    public static String followTabName;
    public static String homeTabName;
    public static String cityTabName;
    public static List<String> homeTabNames;

    //配置1，是否成年认证才可以发表自拍
    //配置2，是否填写校园才可以发帖
    //配置3，应用性别

}
