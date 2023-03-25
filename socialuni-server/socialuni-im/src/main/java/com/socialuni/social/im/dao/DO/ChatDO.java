package com.socialuni.social.im.dao.DO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.qingchi.base.constant.ChatTopType;
import com.qingchi.base.constant.ChatType;
import com.qingchi.base.constant.CommonStatus;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:48
 */
@Entity
@Table(name = "chat")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ChatDO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private String matchStatus;

    private Date updateTime;

    private Date createTime;

    //状态
    private String status;

    /**
     * chat的type 为匹配，或者普通聊天，或者群聊，或者通知等等,4 匹配中
     */
    private String nickname;
    private String avatar;
    //置顶标识
    private Boolean topFlag;
    private String lastContent;
    //同为置顶时，区分级别
    private Integer topLevel;

    public ChatDO() {
        Date curDate = new Date();
        this.createTime = curDate;
        this.updateTime = curDate;
        this.status = CommonStatus.normal;
        this.topFlag = false;
        this.topLevel = ChatTopType.simple;
    }

    public ChatDO(String type) {
        this();
        this.type = type;
        if (ChatType.match.equals(type)) {
            this.matchStatus = CommonStatus.waitMatch;
        }
    }

    /*public UserDO getReceiveUser(Integer userId) {
        return this.getUsers().stream().filter((Integer userIdItem) -> !userItem.getId().equals(user.getId())).findAny().get();
    }

    public List<UserDO> getReceiveUsers(Integer userId) {
        return this.getUsers().stream().filter((Integer userIdItem) -> !userItem.getId().equals(user.getId())).collect(Collectors.toList());
    }*/
}
