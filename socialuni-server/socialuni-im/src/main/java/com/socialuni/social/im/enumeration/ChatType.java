package com.socialuni.social.im.enumeration;

import java.util.Arrays;
import java.util.List;

/**
 * 关系类型枚举
 *
 * @author qinkaiyuan
 * @date 2018-09-16 10:58
 */
public class ChatType {
    /**
     * chat的type 为匹配，或者普通聊天，或者群聊，或者通知等等,4 匹配中
     */
    //系统
    public static final String system = "公告";
    //系统
    public static final String notify = "通知";
    //私聊
    public static final String single = "私聊";
    public static final String match = "匹配";
    //群聊
//    public static final String group = "群聊";
    //群聊
    public static final String system_group = "官方群聊";
    //启用,暂时只用了system_group等同于 system_group
    public static final List<String> systemChats = Arrays.asList(system, system_group);
//    public static final List<String> groupChats = Arrays.asList(group, system_group);
}

