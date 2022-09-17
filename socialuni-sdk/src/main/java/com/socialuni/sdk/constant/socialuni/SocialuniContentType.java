package com.socialuni.sdk.constant.socialuni;

import java.util.Arrays;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 10:06
 */
public class SocialuniContentType {
    public final static String user = "用户";
    public final static String userImg = "用户照片";
    public final static String talkImg = "动态照片";
    public final static String talk = "动态";
    public final static String comment = "评论";
    public final static String message = "消息";
    public final static List<String> reportContentTypeTypes = Arrays.asList(userImg, talk, talkImg, comment, message);
}