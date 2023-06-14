package com.socialuni.social.common.api.enumeration;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 会话聊天的状态，暂不使用，全局使用一样的，全局统一，避免出现文字不一致问题
 */
public class ContentStatus extends SocialuniCommonStatus {
    //启用
    //删除

    //预审核
    public static final String preAudit = ConstStatus.preAudit;

    //审核中
    public static final String auditing = ConstStatus.auditing;

    //违规
    public static final String violation = ConstStatus.violation;

    //他人可见
    public static final List<String> otherCanSeeContentStatus = Collections.singletonList(enable);

    //自己可见，自己能比别人多看见预审核状态的信息
    public static final List<String> selfCanSeeContentStatus = Arrays.asList(enable, ContentStatus.preAudit);

    //查询 不为审核中的内容
    public static final List<String> auditStatus = Arrays.asList(ContentStatus.preAudit, ContentStatus.auditing);
   /* //启用
    public static final String normal = "正常";
    //删除
    public static final String delete = "已删除";
    //待开启，需要有一方发送一条消息后，才改为开启状态
    public static final String waitOpen = "待开启";
    //chat_user match相关
    public static final String waitMatch = "待匹配";*/
}

