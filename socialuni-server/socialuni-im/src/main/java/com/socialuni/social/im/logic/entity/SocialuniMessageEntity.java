package com.socialuni.social.im.logic.entity;

import com.socialuni.social.common.api.enumeration.SocialuniCommonStatus;
import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.sdk.dao.DO.NotifyDO;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.common.sdk.dao.facede.SocialuniUserContactRepositoryFacede;
import com.socialuni.social.common.sdk.dao.repository.NotifyRepository;
import com.socialuni.social.im.api.model.RO.SocialMessageRO;
import com.socialuni.social.im.config.websocket.WebsocketServer;
import com.socialuni.social.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.im.dao.repository.*;
import com.socialuni.social.im.dao.DO.SocialuniChatUserDO;
import com.socialuni.social.im.dao.DO.SocialuniUserChatConfigDO;
import com.socialuni.social.im.dao.DO.message.SocialuniMessageDO;
import com.socialuni.social.im.dao.DO.message.SocialuniMessageReceiveDO;
import com.socialuni.social.im.enumeration.*;
import com.socialuni.social.im.logic.check.SocialuniChatUserCheck;
import com.socialuni.social.im.logic.domain.NotifyDomain;
import com.socialuni.social.im.logic.foctory.SocaluniNotifyROFactory;
import com.socialuni.social.im.logic.foctory.SocialMessageROFactory;
import com.socialuni.social.im.logic.foctory.SocialuniChatUserDOFactory;
import com.socialuni.social.im.logic.foctory.SocialuniMessageDOFactory;
import com.socialuni.social.im.logic.manage.SocialuniUserChatConfigManage;
import com.socialuni.social.im.model.message.notify.NotifyVO;
import com.socialuni.social.user.sdk.dao.DO.SocialuniUserBlackDO;
import com.socialuni.social.user.sdk.dao.DO.SocialuniUserFollowDO;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SocialuniMessageEntity {

    @Resource
    private SocialuniChatRepository chatRepository;
    @Resource
    private SocialuniMessageRepository messageRepository;
    @Resource
    private ChatUserRepository chatUserRepository;
    @Resource
    private SocialuniMessageReceiveRepository messageReceiveRepository;
    @Resource
    NotifyRepository notifyRepository;
    @Resource
    NotifyDomain notifyDomain;


    //如果用户存在查看会话

    //还得看自己的，你是否把对方拉黑了
    //

    //对方是否把你拉黑了
    //对方不接收陌生人消息的情况下，你是不是对方的好友
    //判断对方是否接收陌生人消息，判断对方是否把你拉黑了。如果对方没有，则可以发送，不需要查看其他了把
    //则代表对方没把你拉黑

    //查询对方是否你俩的 chatUser状态，对方对你的。 你就当对方不允许，但是你可以进入chat 页面，但是不能发送消息。

    //进入就创建,但是 chatUser 状态可以为其他的。

    //如果你给对方发送了消息，但是你不接收陌生人消息怎么办。
    //只看会话吧。 如果你开起陌生人，然后给对方发了个消息。然后关闭了陌生人。这时候对方还能回你消息不。需要做到能回，现在从哪里控制
    //那就是已开启的会话，不受你关闭了陌生人影响？也不行。
    //那就是你对对方的没开启，那就还是要看对方是否接受陌生人消息，先这样做吧。

    //好友和拉黑功能怎么做，

    //你把对方删除，但是你开起了陌生人消息，对方还是不能给你发信息。
    //所以如果你开起了陌生人消息，且你俩不为好友，但是你不想接受她的消息，但是你还不能把对方 chat 删除。能改chat 状态吗，
    //不能改成查不出来的状态，所以 chatUser的职责是什么呢。负责是否展示？负责删除功能。负责前台是否展示？
    // 还有有个好友表。不需要这个表。
    // 这俩级联？
    @Transactional
    public SocialMessageRO sendSingleMsg(Integer beUserId, String msgContent) {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();

        Integer mineUserId = SocialuniUserUtil.getMineUserIdNotNull();

        //校验用户是否存在
        SocialuniUserUtil.getAndCheckUserNotNull(beUserId);

        List<SocialuniChatUserDO> chatSocialuniUserDoS = SocialuniChatUserDOFactory.getOrCreateChatUsersBySingleSendMsg(beUserId, mineUserId);

        SocialuniUserBlackDO socialuniUserBlackDO = SocialuniUserContactRepositoryFacede.findByUserIdAndBeUserIdAndStatus(beUserId, mineUserId, SocialuniCommonStatus.enable, SocialuniUserBlackDO.class);
        //如果您把对方拉黑了，重新关注后则取消拉黑
        if (socialuniUserBlackDO != null) {
            throw new SocialBusinessException("您已被对方拉黑，无法发送消息");
        }

        if (!beUserId.equals(mineUserId)) {
            SocialuniUserBlackDO beSocialuniUserBlackDO = SocialuniUserContactRepositoryFacede.findByUserIdAndBeUserIdAndStatus(beUserId, mineUserId, SocialuniCommonStatus.enable, SocialuniUserBlackDO.class);

            //对方是否把你拉黑了
            if (beSocialuniUserBlackDO != null) {
                throw new SocialBusinessException("您已将对方拉黑，无法发送消息");
            }
        }

        //先看是否已经被关注
        //如果对方不接收陌生人消息
        SocialuniUserFollowDO beFollowDO = SocialuniUserContactRepositoryFacede.findByUserIdAndBeUserId(beUserId, mineUserId, SocialuniUserFollowDO.class);
        //判断是否关注，关注则直接可以发送消息
        if (beFollowDO == null || !beFollowDO.getStatus().equals(SocialuniCommonStatus.enable)) {
            //没有关注，则校验对方是否接收陌生人消息
            SocialuniUserChatConfigDO socialuniBeUserChatConfigDO = SocialuniUserChatConfigManage.getOrCreateUserChatConfigDO(beUserId);
            //对方是否允许陌生人消息
            if (!socialuniBeUserChatConfigDO.getAllowStrangerMsg()) {
                throw new SocialBusinessException("对方不接受陌生人消息，无法发送消息");
            }
            //则发起发也需要开启陌生人消息才可以
            SocialuniUserChatConfigDO mineChatConfig = SocialuniUserChatConfigManage.getOrCreateUserChatConfigDO(mineUserId);
            if (!mineChatConfig.getAllowStrangerMsg()) {
                throw new SocialBusinessException("您未开启陌生人消息功能，无法给陌生人发送消息");
            }
        }

        //如果是官方通知和
        //如果为官方群聊，则所有人都可以发送内容
        //查询用户是否有权限往chat中发送内容

        //如果是官方通知和
        //如果为官方群聊，则所有人都可以发送内容
        //查询用户是否有权限往chat中发送内容


        //如果为私聊


        //则需要校验，chatUser 如果为好友。初始。
        //查询对方对你的关系，是不是好友。那如果你把对方删除了呢，你还能给对方发消息吗。则不能。


        SocialuniMessageReceiveDO mineMessageUser = sendMsgNotifyList(msgContent, mineUser, chatSocialuniUserDoS, MessageType.simple);
        //只需要返回自己的
        return SocialMessageROFactory.getMessageRO(mineMessageUser);
    }

    @Resource
    SocialuniChatUserCheck socialuniChatUserCheck;

    public SocialMessageRO sendGroupMessage(Integer chatId, String msgContent) {
        SocialuniChatDO chat = SocialuniRepositoryFacade.findByUnionId(chatId, SocialuniChatDO.class);

        SocialuniUserDo sendUser = SocialuniUserUtil.getMineUserNotNull();

        if (chat == null) {
            throw new SocialParamsException("不存在的群聊");
        }

        socialuniChatUserCheck.CheckUserInChat(chatId, sendUser.getUserId());

        List<SocialuniChatUserDO> chatSocialuniUserDoS = chatUserRepository.findByChatIdAndStatus(chatId, ChatUserStatus.enable);


        //有权限，则给chat中的所有用户发送内容

        SocialuniMessageReceiveDO mineMessageUser = null;


        //构建消息
        SocialuniMessageDO message = messageRepository.save(SocialuniMessageDOFactory.createMessage(chat.getUnionId(), msgContent, sendUser.getUserId(), MessageType.simple));

        Date curDate = new Date();
        chat.setUpdateTime(curDate);
//            chat.setLastContent(content);
        chatRepository.save(chat);


        //发送消息
        for (SocialuniChatUserDO chatSocialuniUserDo : chatSocialuniUserDoS) {
//                chatSocialuniUserDo.setLastContent(message.getContent());
            chatSocialuniUserDo.setUpdateTime(curDate);
            chatSocialuniUserDo.setLastContent(message.getContent());
            //如果为匹配chat，且为待匹配状态
                /*if (ChatType.match.equals(chat.getType()) && CommonStatus.waitMatch.equals(chat.getMatchStatus())) {
                    //则将用户的chat改为匹配成功
                    chatSocialuniUserDo.setStatus(ChatUserStatus.enable);
                }*/
            //获取当起chatUser的userId
            Integer chatUserId = chatSocialuniUserDo.getUserId();
//            SocialuniMessageReceiveDO messageReceiveDO = new SocialuniMessageReceiveDO(chatSocialuniUserDo, message.getUserId(), message.getUnionId());

            if (!chatUserId.equals(sendUser.getUserId())) {
                //别人的chatUser，要增加未读，自己刚发的消息，别人肯定还没看
                chatSocialuniUserDo.setUnreadNum(chatSocialuniUserDo.getUnreadNum() + 1);
                //接收方，更改前端显示为显示
                chatSocialuniUserDo.checkFrontShowAndSetTrue();
//                messageReceiveDO = messageReceiveRepository.save(messageReceiveDO);
//                NotifyDO notifyDO = new NotifyDO(messageReceiveDO);
//                notifyDO.setType(NotifyType.message);
//                notifyDO.setContentId(messageReceiveDO.getId());
//                notifyDO = notifyRepository.save(notifyDO);
//                notifies.add(notifyDO);
            } else {
                //自己的话不发送通知，自己的话也要构建消息，要不看不见，因为读是读这个表
//                mineMessageUser = messageReceiveRepository.save(messageReceiveDO);
            }
        }
        chatUserRepository.saveAll(chatSocialuniUserDoS);
        //保存message

//                Optional<ChatDO> chatDOOptional = chatRepository.findById();
        //如果群聊，直接发送给两个服务器在线的所有用户，并且查找他们未读的。
        //未登录的时候也查询群聊里面的所有内容
        NotifyVO notifyVO = SocaluniNotifyROFactory.getNotifyROBySendMsg(NotifyType.message, sendUser, message, chat);
        WebsocketServer.sendMessageToAllUsers(notifyVO);

        //默认所有群都要加入群了，才可以发送消息

        //确认是否存在chatUser且状态。

        //
        //只需要返回自己的
        return SocialMessageROFactory.getMessageRO(message, sendUser, true);
    }

    public SocialuniMessageReceiveDO sendMsgNotifyList(String msgContent, SocialuniUserDo sendUser, List<SocialuniChatUserDO> chatSocialuniUserDoS, String msgType) {
        List<NotifyDO> notifies = new ArrayList<>();
        //有权限，则给chat中的所有用户发送内容

        SocialuniMessageReceiveDO mineMessageUser = null;


        SocialuniChatDO chat = SocialuniRepositoryFacade.findByUnionId(chatSocialuniUserDoS.get(0).getChatId(), SocialuniChatDO.class);
        //构建消息
        SocialuniMessageDO message = messageRepository.save(SocialuniMessageDOFactory.createMessage(chat.getUnionId(), msgContent, sendUser.getUserId(), msgType));

        Date curDate = new Date();
        chat.setUpdateTime(curDate);
//            chat.setLastContent(content);
        chatRepository.save(chat);


        //发送消息
        for (SocialuniChatUserDO chatSocialuniUserDo : chatSocialuniUserDoS) {
//                chatSocialuniUserDo.setLastContent(message.getContent());
            chatSocialuniUserDo.setUpdateTime(curDate);
            chatSocialuniUserDo.setLastContent(message.getContent());
            //如果为匹配chat，且为待匹配状态
                /*if (ChatType.match.equals(chat.getType()) && CommonStatus.waitMatch.equals(chat.getMatchStatus())) {
                    //则将用户的chat改为匹配成功
                    chatSocialuniUserDo.setStatus(ChatUserStatus.enable);
                }*/
            //获取当起chatUser的userId
            Integer chatUserId = chatSocialuniUserDo.getUserId();
            SocialuniMessageReceiveDO messageReceiveDO = new SocialuniMessageReceiveDO(chatSocialuniUserDo, message.getUserId(), message.getUnionId());

            if (!chatUserId.equals(sendUser.getUserId())) {
                //别人的chatUser，要增加未读，自己刚发的消息，别人肯定还没看
                chatSocialuniUserDo.setUnreadNum(chatSocialuniUserDo.getUnreadNum() + 1);
                //接收方，更改前端显示为显示
                chatSocialuniUserDo.checkFrontShowAndSetTrue();
                messageReceiveDO = messageReceiveRepository.save(messageReceiveDO);
                NotifyDO notifyDO = new NotifyDO(messageReceiveDO);
                notifyDO.setType(NotifyType.message);
                notifyDO.setContentId(messageReceiveDO.getId());
                notifyDO = notifyRepository.save(notifyDO);
                notifies.add(notifyDO);
            } else {
                //自己的话不发送通知，自己的话也要构建消息，要不看不见，因为读是读这个表
                mineMessageUser = messageReceiveRepository.save(messageReceiveDO);
            }
        }
        chatUserRepository.saveAll(chatSocialuniUserDoS);
        notifyRepository.saveAll(notifies);
        //保存message
        notifyDomain.sendNotifies(notifies, sendUser);
        return mineMessageUser;
    }

    //用户详情页，判断是否能发消息


    private static List<SocialuniChatUserDO> checkUser(Integer beUserId, SocialuniUserDo mineUser) {
        //对方关注了你，你就能给对方发消息。

//        followdo


        //发起付费，则关注对方。
        //发送消息关注吗？

        //允许陌生人消息，谁都可以给你发消息。 你可以选择给对方拉黑， 也可以选择不接受陌生人消息
        //然后给你发消息，就需要付费。 付费以后默认关注了你，你给对方发消息就免费？

        //如果你给对方发了消息，如果对方为陌生人，则你必须要开启接收陌生人消息。
        //如果对方是收费的，则代表你关注了对方，付费就关注，然后对方可以免费给你发消息，付费发3条？， 然后呢你不需要打开陌生人消息

        //能不能发消息，

        return null;
    }


}
