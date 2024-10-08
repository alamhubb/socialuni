package com.socialuni.social.common.sdk.model.RO.app;

import lombok.Data;

import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2019-09-26 16:00
 */
@Data
public class SocialUnreadNotifyVO {

    private String contentId;
    //前台获取图片路径时使用的
//    private String talkUserId;
    private String avatar;
    private String userId;
    private String nickname;
    private String content;
    private String replyContent;
    private String replyImg;
    private Date createTime;
    private Boolean vipFlag;
    private Boolean hasRead;

    public SocialUnreadNotifyVO() {
    }
}
