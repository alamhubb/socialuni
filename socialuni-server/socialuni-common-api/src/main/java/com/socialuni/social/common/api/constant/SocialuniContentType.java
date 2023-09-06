package com.socialuni.social.common.api.constant;

import java.util.Arrays;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 10:06
 */
public class SocialuniContentType {
    public final static String user = "用户";
    public final static String chat = "会话";
//    public final static String chatUser = "用户会话";
    public final static String userImg = "用户照片";
    public final static String talkImg = "动态照片";
    public final static String talk = "动态";
    public final static String comment = "评论";
    public final static String message = "消息";

    public final static String userAvatar = "用户头像";
    public final static String peiwanImg = "陪玩图片";
    public final static String userNickname = "用户昵称";
    public final static List<String> reportContentTypeTypes = Arrays.asList(user, userImg, userAvatar, userNickname, talk, talkImg, comment, message);
    //支持有独自实体类的
    public final static List<String> unionIdSupportTypes = Arrays.asList(user, userImg, talk, talkImg, comment);

//    public final static List<String> imgTypes = Arrays.asList(userImg, talkImg);
           

}