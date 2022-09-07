package com.socialuni.sdk.model.DO.chat;

import com.socialuni.sdk.constant.socialuni.ChatType;
import com.socialuni.sdk.constant.socialuni.ChatUserStatus;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:48
 * 用来限制用户能不能看到这里面的内容，能不能往里面发内容，不用来控制显示别人的内容
 */
@Data
@Entity
@Table(name = "s_chat_user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"userId", "chat_id"}),
        @UniqueConstraint(columnNames = {"userId", "receiveUserId"})
})
public class ChatUserDO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //置顶标识
    //用户手动操作的，系统没有操作方法，暂时没用
    private Boolean topFlag;

    @ManyToOne
    private ChatDO chat;

    private Integer userId;

    /**
     * 如果是私聊状态，则为对方用户,就是chat的user列表中的另一个人，私聊只有两个人
     */
    private Integer receiveUserId;

    private Date createTime;

    private Date updateTime;

    //每一个用户对于这个群聊的状态
    //需要有一个地方记录，状态有哪些
//    只有enable时可以发送消息，其他状态必须先转为开启才能发送消息
    //1，如果为关闭，则显示发起开启，和待开启一样，只要状态不为代开起，就没有pay
    //2.如果为代开起，付费，则需要点击开启，或者发送时提示是否要给对方发送并开启会话
    //3. 如果为被关闭，则不显示，发送消息报错
    private String status;
    private Integer unreadNum;
    //仅前台展示字段,前台自己判断生成
//    private String lastContent;
    //是否为陌生人
    private Boolean stranger;
    //是否在前台显示
//    前台删除，前台删除时改变，
//    1。进入时，从用户详情页进入时，如果为关闭，则打开
//    2。接受消息时，如果为关闭，则打开
// 作废因为有1 。  发送消息时，如果为关闭，则打开
// 作废因为有1 。2.   则不需要开启会话时的打开的逻辑。
    private Boolean frontShow;

    //开启会话的类型，付费开启，被付费开启，普通发起，普通被发起
    private String openChatType;
    //你是否能向对方发送消息，如果能，则允许，如果不能，则判断剩余条数
    //如果关注，或者开启了buyMsg，且数量大于0

    //这样的话，发消息的时候就不需要再去查询是否是好友和关注了
    //只需要判断这一个，每次更改，对方未关注你，且还不是你的好友
    //这个状态需要实时查询，所以不需要存储
//    private Boolean allowSendMsg;

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

    //群聊，不需要对方用户
    //这个方法暂时没生效，群的时候没有使用chatUser表的数据
    //私聊群聊创建ChatUserDO的逻辑
    public ChatUserDO(ChatDO chat, Integer userId) {
        this.chat = chat;
        this.userId = userId;
        String chatType = chat.getType();
        if (chatType.equals(ChatType.single)) {
            //私聊聊，直接是开启，创建时 只能为待开启和 不在前台显示
            this.frontShow = false;
            this.status = ChatUserStatus.waitOpen;
        } else {
            //官方群聊，匹配，直接是开启
            this.status = ChatUserStatus.enable;
            this.frontShow = true;
        }
        //如果是系统的，则默认指定
        this.topFlag = false;
        Date curDate = new Date();
        this.createTime = curDate;
        this.updateTime = curDate;
        //为什么不设置成99，因为此版本没有阅读功能？先试试99
        this.unreadNum = 0;
    }

    //私聊，比群聊多一个对方用户id,对方用户，方便获取对方的头像昵称，展示, 匹配模块有使用
    public ChatUserDO(ChatDO chat, Integer userId, Integer receiveUserId) {
        this(chat, userId);
        //这里需要看一下，匹配情况，是否要改为1
        this.receiveUserId = receiveUserId;
    }

    public void checkFrontShowAndSetTrue() {
        //进入chat页，列表中进入，肯定是展示的，所以不会走这里
        //    前台删除，前台删除时改变，
//    1。进入时，从用户详情页进入时，如果为关闭，则打开
//    2。接受消息时，如果为关闭，则打开
// 作废因为有1 。  发送消息时，如果为关闭，则打开
// 作废因为有1 。2.   则不需要开启会话时的打开的逻辑。
        if (!this.getFrontShow()) {
            this.setFrontShow(true);
        }
    }

    //只关闭自己
    public void closeChat() {
        this.setUpdateTime(new Date());
        this.setFrontShow(false);
        this.setStatus(ChatUserStatus.close);
    }

    public void frontShowFalse() {
        this.setUpdateTime(new Date());
        this.setFrontShow(false);
    }

    /*public void changeStatusBeClose(Date date) {
        this.setUpdateTime(date);
        this.setStatus(ChatUserStatus.beClose);
    }*/

    //创建私聊时，需要根据chat状态决定chatUser状态，有两种情况，直接开启和待开启，msg可删除时，则无需再使用lastcontent
    /*public ChatUserDO(ChatDO chatDO, Integer userId, Integer receiveUserId) {
        this(chatDO, userId, receiveUserId, chatDO.getType());
        this.status = chatDO.getStatus();
        //只有支付开启的时候，直接在对方前台显示
        //待开启的情况，只把自己的改为show
        *//*if (chatDO.getStatus().equals(CommonStatus.enable)) {
            this.frontShow = true;
        }*//*
        //如果直接开启，则前台需要改为显示状态
        //没有msg的时候显示lastcontent,
        *//*
        //前台根据状态判断显示就好
        if (chatDO.getStatus().equals(CommonStatus.waitOpen)) {
            this.lastContent = "会话未开启";
        } else {
            this.lastContent = "会话已开启";
        }*//*
    }*/

}
