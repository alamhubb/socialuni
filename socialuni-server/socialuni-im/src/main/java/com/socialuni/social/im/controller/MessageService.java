package com.socialuni.social.im.controller;

import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.sdk.dao.DO.NotifyDO;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.common.sdk.dao.facede.SocialuniUserContactRepositoryFacede;
import com.socialuni.social.im.api.model.RO.SocialMessageRO;
import com.socialuni.social.im.dao.ChatRepository;
import com.socialuni.social.im.dao.ChatUserRepository;
import com.socialuni.social.im.dao.DO.ChatSocialuniUserDo;
import com.socialuni.social.im.dao.DO.ChatUserDO;
import com.socialuni.social.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.im.dao.DO.SocialuniUserChatConfigDO;
import com.socialuni.social.im.dao.DO.message.MessageDO;
import com.socialuni.social.im.dao.DO.message.MessageReceiveDO;
import com.socialuni.social.im.dao.MessageReceiveRepository;
import com.socialuni.social.im.dao.MessageRepository;
import com.socialuni.social.im.enumeration.ChatType;
import com.socialuni.social.im.enumeration.ChatUserStatus;
import com.socialuni.social.im.logic.entity.SocialuniChatUserEntity;
import com.socialuni.social.im.logic.foctory.SocialMessageROFactory;
import com.socialuni.social.im.logic.manage.SocialuniChatDOFactory;
import com.socialuni.social.im.logic.manage.SocialuniUserChatConfigDOFactory;
import com.socialuni.social.im.model.message.message.MessageAddVO;
import com.socialuni.social.report.sdk.logic.service.IllegalWordService;
import com.socialuni.social.report.sdk.utils.SocialuniTextContentUtil;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.user.sdk.logic.check.SocialuniUserCheck;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-06-16 12:39
 */
@Service
public class MessageService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private UserRepository userRepository;
    @Resource
    private ChatRepository chatRepository;
    @Resource
    private MessageRepository messageRepository;
    @Resource
    private NotifyService notifyService;
    @Resource
    private NotifyRepository notifyRepository;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private ChatUserRepository chatUserRepository;
    @Resource
    private MessageReceiveRepository messageReceiveRepository;
    @Resource
    private ReportService reportService;
    @Resource
    private ReportDomain reportDomain;
    @Resource
    private ChatUserVerify chatUserVerify;
    @Resource
    private IllegalWordService illegalWordService;
    @Resource
    private ModelContentCheck modelContentCheck;


    public ResultRO<SocialMessageRO> sendMsg(MessageAddVO msgAddVO) throws IOException {
        String receiveIdUid = msgAddVO.getReceiveId();
        String msgContent = msgAddVO.getContent();
        if (StringUtils.isEmpty(msgContent)) {
            throw new SocialBusinessException("不能发布空内容");
        }
        SocialuniUserCheck.checkUserBindPhoneNumAndStatusNoEnable();

        //校验内容是否违规
        SocialuniTextContentUtil.checkTextHasViolateWords(msgContent);

        SocialuniChatDO chat = SocialuniRepositoryFacade.findById(Integer.valueOf(receiveIdUid), SocialuniChatDO.class);

        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();
        ChatUserDO chatUserDO;

        //创建 chatUser 的逻辑，点击进入页面，会话页加一条
        //发送消息，还有添加好友成功

        //为私聊相关校验
        if (chat == null) {
            //后端区分这个值是群聊还是私聊。
            Integer receiveId = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(receiveIdUid);
            //校验用户是否存在
            SocialuniUserUtil.getAndCheckUserNotNull(receiveId);

            //如果用户存在查看会话
            chatUserDO = SocialuniUserContactRepositoryFacede.findByUserIdAndBeUserId(mineUser.getUserId(), receiveId, ChatUserDO.class);
            if (chatUserDO == null) {
                //会话不存在则创建
                chatUserDO = SocialuniChatUserEntity.createChatUserBySingleSendMsg(mineUser.getUserId(), receiveId);
            }
            //还得看自己的，你是否把对方拉黑了
            //
            if (chatUserDO.getBlacklist()) {
                throw new SocialBusinessException("对方已被您拉黑，无法发送消息");
            }
            //对方是否把你拉黑了
            //对方不接收陌生人消息的情况下，你是不是对方的好友
            //判断对方是否接收陌生人消息，判断对方是否把你拉黑了。如果对方没有，则可以发送，不需要查看其他了把
            ChatUserDO chatBeUserDO = SocialuniUserContactRepositoryFacede.findByUserIdAndBeUserId(receiveId, mineUser.getUserId(), ChatUserDO.class);
            //则代表对方没把你拉黑
            if (chatBeUserDO != null) {
                if (chatBeUserDO.getBlacklist()) {
                    throw new SocialBusinessException("您已被对方拉黑，无法发送消息");
                }
            }
            SocialuniUserChatConfigDO socialuniUserChatConfigDO = SocialuniUserChatConfigDOFactory.getOrCreateUserChatConfigDO(receiveId);


            //好友和拉黑功能怎么做，

            //你把对方删除，但是你开起了陌生人消息，对方还是不能给你发信息。
            //所以如果你开起了陌生人消息，且你俩不为好友，但是你不想接受她的消息，但是你还不能把对方 chat 删除。能改chat 状态吗，
            //不能改成查不出来的状态，所以 chatUser的职责是什么呢。负责是否展示？负责删除功能。负责前台是否展示？
            // 还有有个好友表。不需要这个表。
            // 这俩级联？


//            if (chatUserDO.)


//                chat = SocialuniRepositoryFacade.findById(chatUserDO.getChatId(), SocialuniChatDO.class);
            //如果是官方通知和
            //如果为官方群聊，则所有人都可以发送内容
            //查询用户是否有权限往chat中发送内容


        }
        //构建消息
        MessageDO message = messageRepository.save(new MessageDO(chat.getId(), msgContent, mineUser.getUserId()));

        Date curDate = new Date();
        chat.setUpdateTime(curDate);
//            chat.setLastContent(content);
        chatRepository.save(chat);


        //查询对方是否你俩的 chatUser状态，对方对你的。 你就当对方不允许，但是你可以进入chat 页面，但是不能发送消息。

        //进入就创建,但是 chatUser 状态可以为其他的。

        //如果你给对方发送了消息，但是你不接收陌生人消息怎么办。
        //只看会话吧。 如果你开起陌生人，然后给对方发了个消息。然后关闭了陌生人。这时候对方还能回你消息不。需要做到能回，现在从哪里控制
        //那就是已开启的会话，不受你关闭了陌生人影响？也不行。
        //那就是你对对方的没开启，那就还是要看对方是否接受陌生人消息，先这样做吧。


        //校验 chat 中是否包含了用户。 只考虑正向逻辑。
        //你给用户发送消息，是否考虑简单考虑，插头 User 必须创建成功才能发送消息。
        if (chat.getType().equals(ChatType.system_group)) {
            SocialMessageRO messageVO = SocialMessageROFactory.getMessageRO(message, mineUser.getUserId());
            return ResultRO.success(messageVO);
        } else {


            //如果是官方通知和
            //如果为官方群聊，则所有人都可以发送内容
            //查询用户是否有权限往chat中发送内容


            //如果为私聊


            //则需要校验，chatUser 如果为好友。初始。
            //查询对方对你的关系，是不是好友。那如果你把对方删除了呢，你还能给对方发消息吗。则不能。


            List<NotifyDO> notifies = new ArrayList<>();
            //有权限，则给chat中的所有用户发送内容

            MessageReceiveDO mineMessageUser = new MessageReceiveDO();
            //发送消息
            for (ChatSocialuniUserDo chatSocialuniUserDo : chatSocialuniUserDoS) {
//                chatSocialuniUserDo.setLastContent(message.getContent());
                chatSocialuniUserDo.setUpdateTime(curDate);
                //如果为匹配chat，且为待匹配状态
                if (ChatType.match.equals(chat.getType()) && CommonStatus.waitMatch.equals(chat.getMatchStatus())) {
                    //则将用户的chat改为匹配成功
                    chatSocialuniUserDo.setStatus(ChatUserStatus.enable);
                }
                //获取当起chatUser的userId
                Integer chatUserId = chatSocialuniUserDo.getUserId();
                MessageReceiveDO messageReceiveDO = new MessageReceiveDO(chatSocialuniUserDo, user.getId(), chatUserId, message);
                //自己的话不发送通知，自己的话也要构建消息，要不看不见，因为读是读这个表
                if (chatUserId.equals(user.getId())) {
                    messageReceiveDO.setIsMine(true);
                    messageReceiveDO.setIsRead(true);
                    mineMessageUser = messageReceiveRepository.save(messageReceiveDO);
                } else {
                    //别人的chatUser，要增加未读，自己刚发的消息，别人肯定还没看
                    chatSocialuniUserDo.setUnreadNum(chatSocialuniUserDo.getUnreadNum() + 1);
                    //接收方，更改前端显示为显示
                    chatSocialuniUserDo.checkFrontShowAndSetTrue();
                    MessageReceiveDO messageReceiveDO1 = messageReceiveRepository.save(messageReceiveDO);
                    NotifyDO notifyDO = notifyRepository.save(new NotifyDO(messageReceiveDO1));
                    notifies.add(notifyDO);
                }
            }
            chatUserRepository.saveAll(chatSocialuniUserDoS);
            notifyRepository.saveAll(notifies);
            //保存message
            notifyService.sendNotifies(notifies, user);
            //只需要返回自己的
            return new ResultRO<>(new MessageVO(mineMessageUser));
        }


        //生成消息，先不管群聊只管私聊，私聊会有receiveUser，用来判断已读未读
        //只管群聊
        //查看chat的类型
        if (chat.getType().equals(ChatType.system_group)) {

            //不为官方群聊
        } else {

        }
    }

}
