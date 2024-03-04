package com.socialuni.social.sdk.im.enumeration;

/**
 * 置顶类型
 *
 * @author qinkaiyuan
 * @date 2018-09-16 10:58
 */
public class ChatTopLevel {
    //系统公告,优先级别 80
    public static final Integer system = 80;
    //通知
    public static final Integer notify = 70;
    //官方群聊
    public static final Integer system_group = 60;
    //普通
    public static final Integer simple = 10;

    /*public static final Map<String, Integer> chatTopTypeMap = new HashMap<String, Integer>() {
        {
            put(ChatType.system_group, system_group);
            put(ChatType.single, simple);
            put(ChatType.match, simple);
        }
    };*/
}

