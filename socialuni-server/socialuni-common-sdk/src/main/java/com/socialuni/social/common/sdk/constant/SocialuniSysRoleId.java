package com.socialuni.social.common.sdk.constant;

import java.util.Arrays;
import java.util.List;

public class SocialuniSysRoleId {
    //普通用户
    public static final String user = "user";
    //管理员
    public static final String admin = "admin";
    //系统
    public static final String sys = "sys";
    //所有者
    public static final String owner = "owner";

    public final static List<String> hasOperateAuthList = Arrays.asList(SocialuniSysRoleId.sys, SocialuniSysRoleId.admin);


}
