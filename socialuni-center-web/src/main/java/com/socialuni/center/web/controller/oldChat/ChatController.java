package com.socialuni.center.web.controller.oldChat;

import com.qingchi.web.model.DO.user.SocialUserDO;
import com.socialuni.base.constants.status.BaseStatus;
import com.socialuni.social.constant.ErrorCode;
import com.socialuni.social.model.DO.user.SocialFollowDO;
import com.socialuni.social.model.common.ResultRO;
import com.socialuni.web.constant.ChatType;
import com.socialuni.web.constant.status.ChatStatus;
import com.socialuni.web.constant.status.ChatUserStatus;
import com.socialuni.web.constant.status.MessageStatus;
import com.socialuni.web.dbUtil.UnionIdDbUtil;
import com.socialuni.web.domain.PayShellOpenChatDomain;
import com.socialuni.web.model.chat.ChatDO;
import com.socialuni.web.model.chat.ChatUserDO;
import com.socialuni.web.model.chat.MessageReceiveDO;
import com.socialuni.web.modelVO.*;
import com.socialuni.web.modelVO.message.MessageAddVO;
import com.socialuni.web.modelVO.message.MessageVO;
import com.socialuni.web.modelVO.user.UserIdVO;
import com.socialuni.web.repository.center.follow.SocialFollowRepository;
import com.socialuni.web.repository.chat.ChatRepository;
import com.socialuni.web.repository.chat.ChatUserRepository;
import com.socialuni.web.repository.chat.MessageReceiveRepository;
import com.socialuni.web.repository.notify.NotifyRepository;
import com.socialuni.web.repository.shell.UserContactRepository;
import com.socialuni.web.service.ChatService;
import com.socialuni.web.service.ChatUserService;
import com.socialuni.web.service.MessageService;
import com.socialuni.web.service.NotifyService;
import com.socialuni.web.utils.QingLogger;
import com.socialuni.web.utils.UserUtils;
import com.socialuni.web.verify.ChatUserVerify;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:45
 */

@RestController
@RequestMapping("chat")
public class ChatController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Resource
    private ChatRepository chatRepository;
    @Resource
    private ChatUserRepository chatUserRepository;
    @Resource
    private NotifyService notifyService;
    @Resource
    private NotifyRepository notifyRepository;
    @Resource
    private MessageReceiveRepository messageReceiveDORepository;
    @Resource
    private ChatUserService chatUserService;
    @Resource
    private ChatService chatService;
    @Resource
    private SocialFollowRepository socialFollowRepository;
    @Resource
    private ChatUserVerify chatUserVerify;
    @Resource
    private MessageService messageService;

    /**
     * 传入的ids应该为前台不为自己的，且未读的
     *
     * @param user
     * @param chatVO
     * @return
     */
    @PostMapping("readChat")
    public ResultRO<?> readChatMessages(SocialUserDO user, @RequestBody @Valid ChatReadVO chatVO) {
        if (user != null) {
            Long chatId = chatVO.getChatId();
            Optional<ChatDO> chatDOOptional = chatRepository.findFirstByIdAndStatus(chatId, ChatStatus.enable);
            if (!chatDOOptional.isPresent()) {
                log.error("被攻击了，出现了不存在的消息:{}", chatVO.getChatId());
                return new ResultRO<>("该聊天不存在");
            }
            ChatDO chat = chatDOOptional.get();
            //为什么判断非系统组别
            if (!chat.getType().equals(ChatType.system_group)) {
                //查询用户是否有chat权限，并且chat正常
                /*Optional<ChatUserDO> chatUserDOOptional = chatUserRepository.findFirstByChatIdAndChatStatusAndUserId(chat.getId(), ChatStatus.enable, user.getId());
                if (!chatUserDOOptional.isPresent()) {
                    log.error("用户已经被踢出来了，不具备给这个chat发送消息的权限");
                    //用户给自己被踢出来，或者自己删除的内容发消息。提示异常
                    return new ResultVO<>("聊天已关闭，请刷新");
                }*/
                ResultRO<ChatUserDO> resultRO = chatUserVerify.checkChatHasUserId(chatId, user.getId());
                if (resultRO.hasError()) {
                    return new ResultRO<>(resultRO);
                }
                ChatUserDO chatUserDb = resultRO.getData();

                //将此chat下的所有变为已读 toDO 这里有问题，应该前台把id传过来改为已读
                //查出来这些msg的id，
                //如果是私聊，则将自己的  和 对方的msg改为已读，然后弄出一个msgvolist，推送给前台。
                //如果是群聊，则将自己的改为已读，列表中的已读数量+1

                //全部已读
                chatUserDb.setUnreadNum(0);
                //进入chat页，列表中进入，肯定是展示的，所以不会走这里
                chatUserDb.checkFrontShowAndSetTrue();
                //目前不根据点击时间更新，只根据消息时间更新
//                chatUserDb.setUpdateTime(new Date());
                chatUserRepository.save(chatUserDb);
                //toDO 这里需要细想怎么个逻辑
                //需要将chatUser的未读数量更新一下
//            messageReceiveDORepository.updateMessageReceiveRead(chatUserDb, readVO.getMessageIds());
                List<MessageReceiveDO> messageReceiveDOS = messageReceiveDORepository.findByChatUserIdAndChatUserStatusAndStatusAndIsReadFalse(chatUserDb.getId(), ChatUserStatus.enable, MessageStatus.enable);
//                List<MessageReceiveDO> messageReceiveDOS = new ArrayList<>();
                //把具体的每一条改为已读
                if (messageReceiveDOS.size() > 0) {
                    Date curDate = new Date();
                    for (MessageReceiveDO messageReceiveDO : messageReceiveDOS) {
                        messageReceiveDO.setUpdateTime(curDate);
                        messageReceiveDO.setIsRead(true);
                        /*
                        toDO 暂时不需要的逻辑，这个逻辑是把msg改为已读，并且已读次数加1
                        MessageDO messageDO = messageReceiveDO.getMessage();
                        messageDO.setReadStatus(CommonStatus.read);
                        if (ChatType.groupChats.contains(chat.getType())) {
                            messageDO.setReadNum(messageDO.getReadNum() + 1);
                        }*/
                    }
                    messageReceiveDORepository.saveAll(messageReceiveDOS);
                }
                //将chat的未读改为0
            /*List<MessageReceiveDO> messageReceiveDOS = messageReceiveDORepository.findByChatUserIdAndIdInOrderByCreateTimeDescIdDesc(chatUserDb, readVO.getMessageIds());
            if (messageReceiveDOS.size() > 0) {
                ChatUserDO receiveChatUser = messageReceiveDOS.get(0).getChatUser();
                //需要得到msgids
                List<MessageDO> messageDOS = messageReceiveDOS.stream().map(MessageReceiveDO::getMessage).collect(Collectors.toList());
                List<MessageReceiveDO> receiveDOS = messageReceiveDORepository.findByChatUserIdAndMessageInOrderByCreateTimeDescIdDesc(receiveChatUser, messageDOS);
            } else {
                log.error("异常，不该存在数量为0的情况");
                return new ResultVO<>(ErrorCode.SYSTEM_ERROR);
            }*/
            }
        }
        return new ResultRO<>();
    }

    @PostMapping("queryHomeChat")
    public ResultRO<List<ChatVO>> queryHomeChat(SocialUserDO user) {
        if (user != null) {
            return new ResultRO<>(chatUserService.getChats(user));
        }
        return new ResultRO<>(chatUserService.getChats());
    }

    @PostMapping("queryChats")
    public ResultRO<List<ChatVO>> queryChatList(SocialUserDO user) {
        if (user != null) {
            return new ResultRO<>(chatUserService.getChats(user));
        }
        return new ResultRO<>(chatUserService.getChats());
    }

    @PostMapping("queryChat")
    public ResultRO<ChatVO> queryChat(SocialUserDO user, @RequestBody UserIdVO receiveUserVO) {
        ResultRO<Integer> userIdResult = UnionIdDbUtil.getUserIdByUid(receiveUserVO.getUserId(),  user);
        if (userIdResult.hasError()) {
            return new ResultRO(userIdResult);
        }
        Integer userId = userIdResult.getData();


        SocialUserDO receiveUser = UserUtils.get(userId);
        if (receiveUser == null) {
            return new ResultRO<>("该用户不存在");
        }

        //如果对方用户已经违规
        /*if (receiveUser.getStatus().equals(CommonStatus.violation)) {
            return new ResultVO<>("该用户已被封禁，无法开启会话，请刷新后重试");
        }*/

        //进入页面之前，获取chat
        //查询是否之前已经创建过chat、
        ChatVO chat = new ChatVO();
//        ChatVO chat = chatService.getSingleChatVO(user, receiveUser.getId());

        /*new ChatVO(chat);
        chat = chatUserDOOptional.map(chatUserDO -> new ChatVO(chatUserDO.getChat())).orElseGet(() -> );*/
        return new ResultRO<>(chat);
    }


    @Resource
    UserContactRepository userContactRepository;
    @Resource
    PayShellOpenChatDomain payShellOpenChatDomain;

    //开启对话
    //支付贝壳开启对话
    @PostMapping("openChat")
    public ResultRO<ChatVO> openChat(SocialUserDO user, @RequestBody @Valid @NotNull OpenChatVO chatVO) throws IOException {
        Boolean needPayOpen = chatVO.getNeedPayOpen();

        //需要查询出来判断状态，区分返回不同的错误消息
        Optional<ChatDO> chatDOOptional = chatRepository.findFirstByIdAndTypeAndStatus(chatVO.getId(), ChatType.single, ChatStatus.enable);

        if (!chatDOOptional.isPresent()) {
            QingLogger.logger.error("不存在的chat");
            return new ResultRO<>(ErrorCode.SYSTEM_ERROR);
        }

        ChatDO chatDO = chatDOOptional.get();
        //如果不为待开启 chat 状态始终为开启
        /*if (!chatDO.getStatus().equals(ChatStatus.waitOpen)) {
            return new ResultVO<>("会话已开启，请刷新后重试");
        }*/

        //查询chatUser
        ResultRO<ChatUserDO> chatUserResultRO = checkChatUserAndStatusIsWaitOpen(chatDO.getId(), user.getId());
        if (chatUserResultRO.hasError()) {
            return new ResultRO<>(chatUserResultRO);
        }

        //获取chatUser
        ChatUserDO chatUserDO = chatUserResultRO.getData();

        //获取receiveUserId
        Integer receiveUserId = chatUserDO.getReceiveUserId();

        //查询对方是否关注了自己，只有未关注的情况，才能支付
        //查询俩人的关注状态
        Optional<SocialFollowDO> followHeOptional = socialFollowRepository.findFirstByUserIdAndBeUserIdAndStatus(receiveUserId, chatUserDO.getUserId(), BaseStatus.enable);

        //小于1，需要付费支付
        Boolean dbNeedPayOpen = !followHeOptional.isPresent();

        //未关注，需要付费支付
        if (dbNeedPayOpen) {
            //需要付费支付，前台传值错误
            if (!dbNeedPayOpen.equals(needPayOpen)) {
                return new ResultRO<>("对方未关注您，需要付费开启会话，请刷新后重试");
            }

            /*Integer userShell = user.getShell();
            if (userShell < 10) {
                QingLogger.logger.error("系统被攻击，不该触发这里，用户不够10贝壳，无法开启对话");
                return new ResultRO<>(ErrorCode.SYSTEM_ERROR);
            }*/

            //如果未曾经开启过
            /*Optional<UserContactDO> userContactDOOptional = userContactRepository.findFirstByUserIdAndBeUserIdAndStatusAndType(user.getId(), receiveUserId, CommonStatus.normal, ExpenseType.openChat);
            if (userContactDOOptional.isPresent()) {
                QingLogger.logger.error("会话已开启了，不应该还能开启");
                return new ResultVO<>("会话已开启，请刷新后重试");
            }*/
        } else {
            //不需要付费支付，前台传值错误
            if (!dbNeedPayOpen.equals(needPayOpen)) {
                return new ResultRO<>("对方已经关注了您，无需支付贝壳，即可开启对话，请刷新后重试");
            }
        }

        SocialUserDO receiveUser = UserUtils.get(receiveUserId);

        //如果对方用户已经违规,不向其他展示用户是否被封禁
        /*if (receiveUser.getStatus().equals(CommonStatus.violation)) {
            return new ResultVO<>("该用户已被封禁，无法开启会话，请刷新后重试");
        }*/

        //查询receiveChatUserDO
        ResultRO<ChatUserDO> receiveChatUserResultRO = checkChatUserAndStatusIsWaitOpen(chatDO.getId(), receiveUser.getId());
        if (receiveChatUserResultRO.hasError()) {
            return new ResultRO<>(receiveChatUserResultRO);
        }

        ChatUserDO receiveChatUserDO = receiveChatUserResultRO.getData();


        //如果未关注，则扣除贝壳 user, receiveUser,
        ResultRO<ChatVO> resultRO = payShellOpenChatDomain.openChat(user, receiveUser, chatDO, chatUserDO, receiveChatUserDO, dbNeedPayOpen);


        ChatVO chatVO1 = resultRO.getData();

        MessageAddVO messageAddVO = new MessageAddVO();
        String msgContent = chatVO.getContent();
        if (StringUtils.isEmpty(msgContent)) {
            msgContent = "我开起了和您的会话";
        }
        messageAddVO.setChatId(chatDO.getId());
        messageAddVO.setContent(msgContent);

        ResultRO<MessageVO> resultRO1 = messageService.sendMsg(user, messageAddVO);
        if (resultRO1.hasError()) {
            return new ResultRO<>(resultRO1);
        }

        chatVO1.getMessages().add(resultRO1.getData());

        /*new ChatVO(chat);
        chat = chatUserDOOptional.map(chatUserDO -> new ChatVO(chatUserDO.getChat())).orElseGet(() -> );*/
        return resultRO;
    }

    private ResultRO<ChatUserDO> checkChatUserAndStatusIsWaitOpen(Long chatId, Integer userId) {
        ResultRO<ChatUserDO> resultRO = chatUserVerify.checkChatHasUserId(chatId, userId);
        if (resultRO.hasError()) {
            return new ResultRO<>(resultRO);
        }
        ChatUserDO chatUserDO = resultRO.getData();

        if (chatUserDO.getStatus().equals(ChatUserStatus.enable)) {
            return new ResultRO<>("会话已开启，请刷新后重试");
        }
        return new ResultRO<>(chatUserDO);
    }

    @PostMapping("frontDeleteChat")
    public ResultRO<?> frontDeleteChat(SocialUserDO user, @RequestBody @Valid @NotNull ChatRemoveVO chatVO) {
        Long chatId = chatVO.getChatId();
        /*Optional<ChatDO> chatDOOptional = chatRepository.findFirstByIdAndStatus(chatId, ChatStatus.enable);
        if (!chatDOOptional.isPresent()) {
            log.error("被攻击了，出现了不存在的消息:{}", chatId);
            return new ResultVO<>("该聊天不存在");
        }
        ChatDO chat = chatDOOptional.get();
        if (chat.getType().equals(ChatType.system_group)) {
            return new ResultVO<>("暂时无法删除官方群聊");
        }*/
        //查询用户是否有chat权限，并且chat正常
        ResultRO<ChatUserDO> resultRO = chatUserVerify.checkChatHasUserId(chatId, user.getId());
        if (resultRO.hasError()) {
            return new ResultRO<>(resultRO);
        }
        ChatUserDO chatUserDO = resultRO.getData();
        ChatDO chatDO = chatUserDO.getChat();
        if (chatDO.getType().equals(ChatType.system_group)) {
            return new ResultRO<>("无法删除系统群聊");
        }

        /*ResultVO<ChatUserDO> receiverResultVO = chatUserVerify.checkChatHasUserId(chatId, chatUserDO.getReceiveUserId());
        if (receiverResultVO.hasError()) {
            return new ResultVO<>(receiverResultVO);
        }
        ChatUserDO receiveChatUserDO = receiverResultVO.getData();*/

//        List<ChatUserDO> chatUserDOS = new ArrayList<>();

        //双方都为开启，则关闭，自己变为关闭，对方变为被关闭
        //只要自己未已开启，就可以关闭自己的会话，对方发送时候会看chat下的两个是不是都为开启决定
        if (chatUserDO.getFrontShow()) {
            chatUserDO.frontShowFalse();
//            receiveChatUserDO.changeStatusBeClose(curDate);
//            chatUserDOS.add(chatUserDO);
//            chatUserDOS.add(receiveChatUserDO);
            //自己为被关闭，对方关闭，自己也可以关闭
        }/* else if (chatUserDO.getStatus().equals(ChatUserStatus.enable) &&
                receiveChatUserDO.getStatus().equals(ChatUserStatus.close)
        ) {
            chatUserDO.changeStatusClose(curDate);
            chatUserDOS.add(chatUserDO);
        }*/ else {
            //不应该走到这里，只有开起中的才能关闭
            log.error("不应该走到这里，只有展示的才能删除");
            return new ResultRO<>(ErrorCode.SYSTEM_ERROR);
        }
        //查询chat
        chatUserRepository.save(chatUserDO);
        return new ResultRO<>();
    }


    @PostMapping("removeChat")
    public ResultRO<?> removeChat(SocialUserDO user, @RequestBody @Valid @NotNull ChatRemoveVO chatVO) {
        Long chatId = chatVO.getChatId();
        /*Optional<ChatDO> chatDOOptional = chatRepository.findFirstByIdAndStatus(chatId, ChatStatus.enable);
        if (!chatDOOptional.isPresent()) {
            log.error("被攻击了，出现了不存在的消息:{}", chatId);
            return new ResultVO<>("该聊天不存在");
        }
        ChatDO chat = chatDOOptional.get();
        if (chat.getType().equals(ChatType.system_group)) {
            return new ResultVO<>("暂时无法删除官方群聊");
        }*/
        //查询用户是否有chat权限，并且chat正常
        ResultRO<ChatUserDO> resultRO = chatUserVerify.checkChatHasUserId(chatId, user.getId());
        if (resultRO.hasError()) {
            return new ResultRO<>(resultRO);
        }
        ChatUserDO chatUserDO = resultRO.getData();
        ChatDO chatDO = chatUserDO.getChat();
        if (chatDO.getType().equals(ChatType.system_group)) {
            return new ResultRO<>("无法关闭系统群聊");
        }
        /*ResultVO<ChatUserDO> receiverResultVO = chatUserVerify.checkChatHasUserId(chatId, chatUserDO.getReceiveUserId());
        if (receiverResultVO.hasError()) {
            return new ResultVO<>(receiverResultVO);
        }
        ChatUserDO receiveChatUserDO = receiverResultVO.getData();*/

//        List<ChatUserDO> chatUserDOS = new ArrayList<>();

        //双方都为开启，则关闭，自己变为关闭，对方变为被关闭
        //只要自己未已开启，就可以关闭自己的会话，对方发送时候会看chat下的两个是不是都为开启决定
        chatUserDO.closeChat();
        /*if (chatUserDO.getStatus().equals(ChatUserStatus.enable)) {
//            receiveChatUserDO.changeStatusBeClose(curDate);
//            chatUserDOS.add(chatUserDO);
//            chatUserDOS.add(receiveChatUserDO);
            //自己为被关闭，对方关闭，自己也可以关闭
        }*//* else if (chatUserDO.getStatus().equals(ChatUserStatus.enable) &&
                receiveChatUserDO.getStatus().equals(ChatUserStatus.close)
        ) {
            chatUserDO.changeStatusClose(curDate);
            chatUserDOS.add(chatUserDO);
        }*//* else {
            //不应该走到这里，只有开起中的才能关闭
            log.error("不应该走到这里，只有开起中的才能关闭");
            return new ResultVO<>(ErrorCode.SYSTEM_ERROR);
        }*/
        //查询chat
        chatUserRepository.save(chatUserDO);
        return new ResultRO<>();
    }

    @PostMapping("closeChat")
    public ResultRO<?> closeChat(SocialUserDO user, @RequestBody @Valid @NotNull ChatRemoveVO chatVO) {
        Long chatId = chatVO.getChatId();
        /*Optional<ChatDO> chatDOOptional = chatRepository.findFirstByIdAndStatus(chatId, ChatStatus.enable);
        if (!chatDOOptional.isPresent()) {
            log.error("被攻击了，出现了不存在的消息:{}", chatId);
            return new ResultVO<>("该聊天不存在");
        }
        ChatDO chat = chatDOOptional.get();
        if (chat.getType().equals(ChatType.system_group)) {
            return new ResultVO<>("暂时无法删除官方群聊");
        }*/
        //查询用户是否有chat权限，并且chat正常
        ResultRO<ChatUserDO> resultRO = chatUserVerify.checkChatHasUserId(chatId, user.getId());
        if (resultRO.hasError()) {
            return new ResultRO<>(resultRO);
        }
        ChatUserDO chatUserDO = resultRO.getData();
        ChatDO chatDO = chatUserDO.getChat();
        if (chatDO.getType().equals(ChatType.system_group)) {
            return new ResultRO<>("无法关闭系统群聊");
        }
        /*ResultVO<ChatUserDO> receiverResultVO = chatUserVerify.checkChatHasUserId(chatId, chatUserDO.getReceiveUserId());
        if (receiverResultVO.hasError()) {
            return new ResultVO<>(receiverResultVO);
        }
        ChatUserDO receiveChatUserDO = receiverResultVO.getData();*/

//        List<ChatUserDO> chatUserDOS = new ArrayList<>();

        //双方都为开启，则关闭，自己变为关闭，对方变为被关闭
        //只要自己未已开启，就可以关闭自己的会话，对方发送时候会看chat下的两个是不是都为开启决定
        chatUserDO.closeChat();
        /*if (chatUserDO.getStatus().equals(ChatUserStatus.enable)) {
//            receiveChatUserDO.changeStatusBeClose(curDate);
//            chatUserDOS.add(chatUserDO);
//            chatUserDOS.add(receiveChatUserDO);
            //自己为被关闭，对方关闭，自己也可以关闭
        }*//* else if (chatUserDO.getStatus().equals(ChatUserStatus.enable) &&
                receiveChatUserDO.getStatus().equals(ChatUserStatus.close)
        ) {
            chatUserDO.changeStatusClose(curDate);
            chatUserDOS.add(chatUserDO);
        }*//* else {
            //不应该走到这里，只有开起中的才能关闭
            log.error("不应该走到这里，只有开起中的才能关闭");
            return new ResultVO<>(ErrorCode.SYSTEM_ERROR);
        }*/
        //查询chat
        chatUserRepository.save(chatUserDO);
        return new ResultRO<>();
    }
}


//这个是在个人详情页面，开启chat的方法
    /*@PostMapping("payShellOpenChat")
    public ResultVO<ChatVO> payShellOpenChat(UserDO user, @RequestBody UserQueryVO receiveUserVO) {
        //校验用户余额是否够10贝壳
        Integer userShell = user.getShell();
        if (userShell < 10) {
            QingLogger.logger.error("系统被攻击，不该触发这里，用户不够10贝壳，无法开启对话");
            return new ResultVO<>("余额不足，请充值");
        }
        Optional<UserDO> optionalReceiveUserDO = UserUtils.getUserOpt(receiveUserVO.getUserId());
        if (!optionalReceiveUserDO.isPresent()) {
            QingLogger.logger.error("不存在的用户");
            return new ResultVO<>("不存在的用户");
        }
        UserDO receiveUser = optionalReceiveUserDO.get();

        //查询chatUser，只有待开启的进不去页面，
        ChatUserDO chatUserDO = null;

        Optional<ChatUserDO> chatUserDOOptional = chatUserRepository.findFirstByUserIdAndReceiveUserId(user.getId(), receiveUser.getId());
        if (chatUserDOOptional.isPresent()) {
            chatUserDO = chatUserDOOptional.get();
            //只有waitOpen的才需要开启，其他的有各自的逻辑，不冲突，这里只处理waitOpen的逻辑
            if (!chatUserDO.getStatus().equals(CommonStatus.waitOpen)) {
                return new ResultVO<>("会话已开启，请刷新后重试");
            }
        }
        //如果已存在，则可能是对方查看过，如果有，则判断状态是否为已开启。为已开启提示


        //查询对方是否关注了自己，只有未关注的情况，才能支付
        Integer followCount = followRepository.countByUserIdAndBeUserIdAndStatus(receiveUser.getId(), user.getId(), CommonStatus.normal);
        if (followCount > 0) {
            return new ResultVO<>("对方已经关注了您，无需支付贝壳，即可开启对话，请刷新后重试");
        }

        //如果未曾经开启过
        Optional<UserContactDO> userContactDOOptional = userContactRepository.findFirstByUserIdAndBeUserIdAndStatus(user.getId(), receiveUser.getId(), CommonStatus.normal, ExpenseType.openChat);
        if (userContactDOOptional.isPresent()) {
            QingLogger.logger.error("会话已开启了，不应该还能开启");
            return new ResultVO<>("会话已开启，请刷新后重试");
        }

        //如果未关注，则扣除贝壳

        *//*new ChatVO(chat);
        chat = chatUserDOOptional.map(chatUserDO -> new ChatVO(chatUserDO.getChat())).orElseGet(() -> );*//*
        return payShellOpenChatDomain.payShellOpenChat(user, receiveUser, chatUserDO);
    }*/


        /*NotifyDO notifyDO = notifyRepository.save(new NotifyDO(user, chat.getReceiveUser(user), NotifyType.remove_chat));
        notifyService.sendNotify(notifyDO);*/
//chat状态判断
//如果状态为未锁定，则messages的接受人是否为当前用户，
        /*if (chat.getType() == ChatTypeEnum.MATCH_UNLOCKED.getValue()) {
            //则解除匹配，惩罚当前用户，不需要考虑时间问题，反正都是惩罚
            if (chat.getMessages().get(0).getReceiveUser().getId().equals(user.getId())) {
                //惩罚用户
                punishmentService.punishmentUser(user);
            }
            //如果状态为已匹配，
        } else if (chat.getType() == ChatTypeEnum.MATCH_CHAT.getValue()) {
            //则查看双方聊天消息，是谁最后说的话，是否已过五分钟，已过5分钟双方惩罚，未过5分钟，只惩罚解除方，当前用户
            Message message = chat.getMessages().get(chat.getMessages().size() - 1);
            //只有正处于聊天，正聊着天呢，你解除了，则你受到惩罚，对方不受到惩罚，则看最后一个聊天的时间，是否超过5分钟，超过5分钟双方惩罚，不足5分钟，惩罚解除方
            //如果5分钟没说话了
            if (new Date().getTime() > message.getCreateTime().getTime() + MatchConstants.MINUTE_5) {
                //惩罚双方
                punishmentService.punishmentUser(chat.getUsers());
            } else {
                //惩罚当前用户
                punishmentService.punishmentUser(user);
            }
        }*/
//其他情况只解除，不惩罚

    /*List<Chat> filterChats = new ArrayList<>();
        for (Chat chat : chats) {
        //如果待成功匹配，三分钟不说话删除
        if (chat.getType() == ChatTypeEnum.MATCH_UNLOCKED.getValue()) {
            if (chat.getMessages().size() == 1) {
                if (chat.getMessages().get(0).getReceiveUser().getId().equals(user.getId())) {
                    //如果超过未超过三分钟，可以查询，否则不可以查询到了
                    if (chat.getCreateTime().getTime() + MatchConstants.MATCH_UNLOCKED_CANCEL_MINUTE * MatchConstants.MINUTE > new Date().getTime()) {
                        filterChats.add(chat);
                    } else {
                        //需要加入惩罚
                        chat.setStatus(CommonStatus.delete);
                        chat.getMessages().forEach(item -> item.setStatus(CommonStatus.delete));
                        chatRepository.save(chat);
                    }
                }
            }
            //如果匹配成功，3小时不说话删除
        } else if (chat.getType() == ChatTypeEnum.MATCH_CHAT.getValue()) {
            if (chat.getUpdateTime().getTime() + MatchConstants.MATCH_CANCEL_HOUR * MatchConstants.HOUR > new Date().getTime()) {
                filterChats.add(chat);
            } else {
                chat.setStatus(CommonStatus.delete);
                chat.getMessages().forEach(item -> item.setStatus(CommonStatus.delete));
                chatRepository.save(chat);
            }
        } else {
            filterChats.add(chat);
        }
    }*/
