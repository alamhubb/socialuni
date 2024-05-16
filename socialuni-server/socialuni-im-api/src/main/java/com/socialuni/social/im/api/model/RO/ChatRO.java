package com.socialuni.social.im.api.model.RO;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:48
 */
@Data//chatdo+chatUserdo
public class ChatRO {
    //私聊的时候这是userId，后台会自动转换
    private String id;
    private String nickname;
    private Boolean allowStrangerMsg;
    private Boolean beFollow;
    //是否被拉黑
    private Boolean blackUser;
    private Boolean beBlackUser;
    //暂时未用，未来有用的，参考微信私聊右上角，会从那里可以进入用户的详情
    private String receiveUserId;
    private String avatar;
    private String type;
    //为chatUser的status
    private String status;
    private Date updateTime;
    private List<SocialMessageRO> messages;
    private Integer unreadNum;
    private Integer topLevel;
    private Boolean topFlag;
    private Boolean vipFlag;
    private String loadMore;
    private String lastContent;
    //需要消费的金币数量
//    private Integer payCoinNum;

    //只有当对方未关注你，且还不是你的好友，才需要使用这个字段判断。
    //需要支付开启会话
    //只有为私聊，待开启，未关注的时候才需要为true
    private Boolean needPayOpen;
}
