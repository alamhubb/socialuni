package com.socialuni.social.im.dao.DO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.qingchi.base.constant.ChatType;
import com.qingchi.base.constant.ChatUserStatus;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:48
 * 用来限制用户能不能看到这里面的内容，能不能往里面发内容，不用来控制显示别人的内容
 */
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "chatUser", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"userId", "chatId"})
})
@ToString(exclude = "chat")
public class ChatUserDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //置顶标识
    private Boolean topFlag;

    private Long chatId;

    private Integer userId;

    /**
     * 如果是私聊状态，则为对方用户,就是chat的user列表中的另一个人，私聊只有两个人
     */
    private Integer receiveUserId;

    private Date createTime;

    private Date updateTime;

    //每一个用户对于这个群聊的状态
    //需要有一个地方记录，状态有哪些
    private String status;
    private Integer unreadNum;
    private String lastContent;
    private Boolean stranger;
    //你是否能向对方发送消息，如果能，则允许，如果不能，则判断剩余条数
    //如果关注，或者开启了buyMsg，且数量大于0

    //这样的话，发消息的时候就不需要再去查询是否是好友和关注了
    //只需要判断这一个，每次更改，对方未关注你，且还不是你的好友
    private Boolean allowSendMsg;
    //只有当对方未关注你，且还不是你的好友，才需要使用这个字段判断。
    private Boolean byMsg;

    //剩余允许发送数量，只有没被关注，且购买了，才需要这个
    private Integer msgRemainNum;

    //发送消息时，需要判断，你能否给对方发消息。就这么个功能吧。
    //每次打开都需要判断一下

    //发送界面呢。发送时判断，你是否被拉黑。显示一种内容。
    //否则，提示需要支付、。 这是一个字段
    //这个字段，根据对方是否关注了你，你是否购买了，是否还有购买次数。
    //剩余次数。

    //用户详情页面，只要买过就不显示5b。
    //usedetail是否显示buysend

    public ChatUserDO() {
    }

    //群聊不需要对方用户
    //这个方法暂时没生效，群的时候没有使用chatUser表的数据
    public ChatUserDO(Long chatId, Integer userId, String chatType) {
        this.chatId = chatId;
        this.userId = userId;
        this.status = ChatUserStatus.enable;
        if (ChatType.systemChats.contains(chatType)) {
            this.topFlag = true;
        } else {
            this.topFlag = false;
        }
        Date curDate = new Date();
        this.createTime = curDate;
        this.updateTime = curDate;
        //为什么不设置成99，因为此版本没有阅读功能？先试试99
        this.unreadNum = 99;
        this.lastContent = "";
    }

    //私聊，对方用户，方便获取对方的头像昵称，展示
    public ChatUserDO(Long chatId, Integer userId, Integer receiveUserId, String chatType) {
        this(chatId, userId,chatType);
        this.unreadNum = 1;
        this.receiveUserId = receiveUserId;
    }
}
