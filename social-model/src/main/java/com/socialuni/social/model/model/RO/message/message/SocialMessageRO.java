package com.socialuni.social.model.model.RO.message.message;


import com.socialuni.social.model.model.RO.user.base.SocialUserRO;
import lombok.Data;

import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2019-08-14 17:24
 */
@Data
public class SocialMessageRO {

    // 前端遍历使用
    private Integer id;
    private String content;
    private SocialUserRO user;
    // 前端对比是否为当前用户使用，聊天页面
    //用来帮助前端判断是否为自己发送的
    private Boolean isMine;
    private String readStatus;
    private Boolean isRead;
    private Integer readNum;
    private Date createTime;
    //前台不需要这个时间
//    private Date updateTIme;
    private String type;


}
