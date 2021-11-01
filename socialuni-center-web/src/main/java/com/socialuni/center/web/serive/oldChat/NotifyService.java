package com.socialuni.center.web.serive.oldChat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qingchi.base.config.ResultException;
import com.qingchi.base.config.websocket.WebsocketServer;
import com.qingchi.base.constant.NotifyType;
import com.qingchi.base.constant.ProviderType;
import com.qingchi.base.model.account.AccountDO;
import com.qingchi.base.model.chat.ChatUserDO;
import com.qingchi.base.model.chat.MessageReceiveDO;
import com.qingchi.base.model.notify.NotifyDO;
import com.qingchi.base.model.user.UserDO;
import com.qingchi.base.modelVO.NotifyVO;
import com.qingchi.base.platform.PushMsgDTO;
import com.qingchi.base.platform.PushUtils.CommentPushUtils;
import com.qingchi.base.platform.PushUtils.ReportResultPushUtils;
import com.qingchi.base.platform.PushUtils.TalkCommentPushUtils;
import com.qingchi.base.platform.PushUtils.ViolationPushUtils;
import com.qingchi.base.platform.qq.QQUtil;
import com.qingchi.base.platform.weixin.WxUtil;
import com.qingchi.base.repository.chat.ChatRepository;
import com.qingchi.base.repository.chat.ChatUserRepository;
import com.qingchi.base.repository.chat.MessageReceiveRepository;
import com.qingchi.base.repository.user.AccountRepository;
import com.qingchi.base.repository.user.UserRepository;
import com.qingchi.base.utils.JsonUtils;
import com.qingchi.base.utils.QingLogger;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author qinkaiyuan
 * @date 2019-06-16 12:39
 */
@Service
public class NotifyService {
    @Resource
    private AccountRepository accountRepository;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private MessageReceiveRepository messageReceiveRepository;
    @Resource
    private ChatUserRepository chatUserRepository;
    @Resource
    private ChatRepository chatRepository;
    @Resource
    private UserRepository userRepository;

    public void sendNotifies(List<NotifyDO> notifies, UserDO requestUser) {
        for (NotifyDO notify : notifies) {
            sendNotify(notify, requestUser);
        }
    }

    //发送通知
    public void sendNotify(NotifyDO notify, UserDO requestUser) throws ResultException {
        //评论动态
//        UserDO receiveUser = userRepository.findById(receiveUserId).get();
        Integer receiveUserId = notify.getReceiveUserId();

        Optional<AccountDO> accountDOOptional = accountRepository.findOneByUserId(receiveUserId);
        if (accountDOOptional.isPresent()) {
            String notifyType = notify.getType();
            AccountDO receiveAccount = accountDOOptional.get();
            String provider = receiveAccount.getProvider();
            if (NotifyType.comments.contains(notifyType)) {
                //显示在动态主页右上角的通知，只需要发送者的名称头像图片
                WebsocketServer.sendMessage(receiveUserId.toString(), new NotifyVO(requestUser));
            }
            PushMsgDTO pushMsgDTO = null;

            if (!NotifyType.notifies.contains(notifyType)) {
                QingLogger.logger.error("错误的通知类型：{}", notifyType);
                throw new ResultException("错误的通知类型");
            }

            if (NotifyType.message.equals(notifyType)) {
                Optional<MessageReceiveDO> messageReceiveDOOptional = messageReceiveRepository.findById(notify.getMessageReceiveId());
                MessageReceiveDO messageReceiveDO = messageReceiveDOOptional.get();
                ChatUserDO chatUserDO = messageReceiveDO.getChatUser();
//                Optional<ChatDO> chatDOOptional = chatRepository.findById();
                //如果群聊，直接发送给两个服务器在线的所有用户，并且查找他们未读的。
                //未登录的时候也查询群聊里面的所有内容
                NotifyVO notifyVO = new NotifyVO(notify, requestUser, messageReceiveDO, chatUserDO, chatUserDO.getChat());
                try {
                    stringRedisTemplate.convertAndSend(receiveUserId.toString(), JsonUtils.objectMapper.writeValueAsString(notifyVO));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            } else {
                //评论动态
                switch (notifyType) {
                    case NotifyType.talk_comment:
                        pushMsgDTO = TalkCommentPushUtils.getTalkPushDTO(provider, notify, requestUser);
                        break;
                    case NotifyType.comment_comment:
                    case NotifyType.reply_comment:
                        pushMsgDTO = CommentPushUtils.getCommentPushDTO(provider, notify, requestUser);
                        //回复评论
                        break;
                    case NotifyType.report_result:
                        pushMsgDTO = ReportResultPushUtils.getReportResultPushDTO(provider, notify);
                        break;
                    case NotifyType.violation:
                        pushMsgDTO = ViolationPushUtils.getViolationPushDTO(provider, notify);
                        break;
                    case NotifyType.message:

                }
                assert pushMsgDTO != null;
                if (provider.equals(ProviderType.qq)) {
                    QQUtil.qqPushMsgCommon(receiveAccount.getQqMpOpenId(), provider, pushMsgDTO, notify);
                } else if (provider.equals(ProviderType.wx)) {
                    WxUtil.wxPushMsgCommon(receiveAccount.getWxMpOpenId(), provider, pushMsgDTO, notify);
                }
            }
        }


//        messagingTemplate.convertAndSendToUser(notify.getReceiveUser().getId().toString(), "/queue/notifications", notifyVO);
        //消息入库
        /*NotifyVO notifyVO = new NotifyVO(notify);*/
        /*//给前台发送消息
        String notifyString = null;
        try {
            notifyString = JsonUtils.objectMapper.writeValueAsString(notifyVO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }*/
        /*//给对方发送喜欢通知，不发送通知
        stringRedisTemplate.convertAndSend(notify.getReceiveUser().getId(), notifyString);
        // 注释掉使用redis部分，暂不使用redis，仅使用websocket
        //给前台发送消息
        String notifyString = objectMapper.writeValueAsString(notifyVO);
        //给对方发送喜欢通知，不发送通知
        stringRedisTemplate.convertAndSend(message.getReceiveUser().getId(), notifyString);*/
    }

    /*
                 暂时没有系统管理员删除功能了，只能举报，审核

                 else if (notifyType.equals(NotifyType.delete_talk)) {
                    if (platform.equals(PlatformType.qq)) {
                        QQPushDeleteMsg.qqPushDeleteTalk(platform, openId, notify);
                    } else if (platform.equals(PlatformType.wx)) {
                        WxPushDeleteMsg.wxPushDeleteTalk(platform, openId, notify);
                    }
                    //删除评论
                } else if (notifyType.equals(NotifyType.delete_comment)) {
                    if (platform.equals(PlatformType.qq)) {
                        QQPushDeleteMsg.qqPushDeleteComment(platform, openId, notify);
                    } else if (platform.equals(PlatformType.wx)) {
                        WxPushDeleteMsg.wxPushDeleteComment(platform, openId, notify);
                    }
                    //举报处理结果
                }*/

}
