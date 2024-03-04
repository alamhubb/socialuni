package com.socialuni.social.sdk.im.enumeration;

import java.util.Arrays;
import java.util.List;

/**
 * 关系类型枚举
 *
 * @author qinkaiyuan
 * @date 2018-09-16 10:58
 */
public class ChatOpenType {
    public static final String open = "开放";
    public static final String openApply = "审批进入";

    public static final String password = "密码加入并需要审批";
//    public static final String passwordApply = "密码进入并需要审批";

    //仅限邀请，你设置个别人都不知道的密码，然后你邀请进入就可以了，管理员不知道密码也进不去
//    public static final String invite = "邀请进入";
//    public static final String inviteApply = "邀请申请并需要审批";

    public static final List<String> needApply = Arrays.asList(openApply, password);
}

