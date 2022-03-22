package com.socialuni.social.entity.model.DO.chat;


import com.socialuni.social.constant.ChatStatus;
import com.socialuni.social.constant.ChatType;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:48
 */@Entity
@Table(name = "s_chat")
@Data
public class ChatDO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /*@OneToMany(mappedBy = "chat")
    @Where(clause = "status in ('" + CommonStatus.normal + "')")
    private List<MessageDO> messages;*/

   /* @OneToMany(mappedBy = "chat", cascade = CascadeType.PERSIST)
    private List<ChatUserDO> chatUsers;*/

    /**
     * chat的type 为匹配，或者普通聊天，或者群聊，或者通知等等,4 匹配中，系统置顶群聊
     */
    private String type;

    //待匹配，匹配成功
//    private String matchStatus;

    private Date updateTime;

    private Date createTime;

    //状态
    private String status;

    /**
     * chat的type 为匹配，或者普通聊天，或者群聊，或者通知等等,4 匹配中
     */
    private String chatName;
    private String avatar;
    //置顶标识
//    private Boolean topFlag;
//    private String lastContent;
    //同为置顶时，区分级别
    private Integer topLevel;

    public ChatDO() {}

    //创建chat时，
    public ChatDO(String type) {
        Date curDate = new Date();
        this.createTime = curDate;
        this.updateTime = curDate;
//        this.topLevel = ChatTopLevel.simple;
        this.type = type;
        //如果为匹配
        if (ChatType.match.equals(type)) {
            this.status = ChatStatus.enable;
//            this.matchStatus = MatchType.waitMatch;
        } else if (ChatType.single.equals(type)) {
            //如果为私聊，则默认为待开启状态，需要有一方发送消息后，才改为开启状态
//            this.status = CommonStatus.waitOpen;
            this.status = ChatStatus.enable;
        }
    }

    //私聊开启会话，可能是直接付费开启，或者进入chat页面，不开启
    /*public ChatDO(String type, String status) {
        this(type);
        this.status = status;
    }*/


    /*public UserDO getReceiveUser(Integer userId) {
        return this.getUsers().stream().filter((Integer userIdItem) -> !userItem.getId().equals(user.getId())).findAny().get();
    }

    public List<UserDO> getReceiveUsers(Integer userId) {
        return this.getUsers().stream().filter((Integer userIdItem) -> !userItem.getId().equals(user.getId())).collect(Collectors.toList());
    }*/
}
