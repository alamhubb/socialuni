package com.socialuni.social.im.logic.domain;

import com.socialuni.social.common.api.exception.base.SocialException;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.im.config.websocket.WebsocketServer;
import com.socialuni.social.im.dao.DO.SocialuniChatUserDO;
import com.socialuni.social.im.logic.foctory.SocaluniNotifyROFactory;
import com.socialuni.social.im.enumeration.NotifyType;
import com.socialuni.social.common.sdk.dao.DO.NotifyDO;
import com.socialuni.social.im.dao.DO.message.SocialuniMessageReceiveDO;
import com.socialuni.social.common.sdk.dao.DO.SocialUserAccountDO;
import com.socialuni.social.im.dao.repository.MessageReceiveRepository;
import com.socialuni.social.common.sdk.dao.repository.NotifyRepository;
import com.socialuni.social.common.sdk.dao.repository.SocialUserAccountRepository;
import com.socialuni.social.im.model.message.notify.NotifyVO;
import com.socialuni.social.common.sdk.platform.PushMsgDTO;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
@Slf4j
public class NotifyDomain {

//    @Resource
//    private CommentRepository commentApi;

    @Resource
    private NotifyRepository notifyRepository;

    /*public List<NotifyDO> saveCreateCommentNotifies(SocialuniCommentDO commentDO, SocialuniTalkDO talkDO, SocialuniCommentDO parentCommentDO, SocialuniCommentDO replyCommentDO, SocialuniUserDo requestUser) {
        List<NotifyDO> notifies = new ArrayList<>();
        Integer talkUserId = talkDO.getUserId();
        Integer commentId = commentDO.getUnionId();
        Integer talkId = talkDO.getUnionId();
        Integer commentUserId = commentDO.getUserId();
        //如果评论的自己的动态，则给所有二级评论人发通知
        if (commentUserId.equals(talkUserId)) {
            //自己评论了自己的talk则要通知所有 其他评论了这个talk的人
            //判断不为子评论，本人回复了别人就是子评论，不给其他评论了这个talk的人发送通知
            if (parentCommentDO == null) {
                List<?  extends SocialuniCommentDO> commentDOS = commentApi.findTop50ByTalkIdAndStatusInAndParentCommentIdIsNullOrderByUpdateTimeDesc(talkUserId, ContentStatus.selfCanSeeContentStatus);
                for (SocialuniCommentDO childComment : commentDOS) {
                    //不给自己发送通知
                    if (!childComment.getUserId().equals(commentUserId)) {
                        NotifyDO notify = new NotifyDO(commentUserId, childComment.getUserId(), commentId, talkId, NotifyType.talk_comment);
                        notifies.add(notify);
                    }
                }
            }
        } else {
            //如果不是评论自己的talk，给talk的主人发消息
            NotifyDO talkNotify = new NotifyDO(commentUserId, talkDO.getUserId(), commentId, talkId, NotifyType.talk_comment);
            notifies.add(talkNotify);
        }


        //如果回复的评论
        if (parentCommentDO != null) {
            Integer parentCommentUserId = parentCommentDO.getUserId();
            //这里查个逻辑，如果自己评论了自己的评论，则给所有子评论发送通知


            //判断不会重复通知,talk和comment 用户不相同，才发送通知，如果自己评论了自己的评论
            //父评论用户不为talk的用户，不是talk用户自己评论自己的
            //是talk用户怎么样，是talk用户不用发送通知
            //因为上面给talk发过，所以做了个判断您，不为talk
            //评论自己的话就不给自己发通知了
            if (!parentCommentUserId.equals(commentDO.getUserId())) {
                NotifyDO commentNotify = new NotifyDO(commentUserId, parentCommentUserId, commentId, talkId, NotifyType.comment_comment);
                notifies.add(commentNotify);
            } else {
                //但是要给所有这条评论的子评论用户发通知
                List<?  extends SocialuniCommentDO> childComments = commentApi.findByParentCommentId(parentCommentDO.getUnionId());
                for (SocialuniCommentDO childCommentDO : childComments) {
                    //但是不能给自己发
                    if (!childCommentDO.getUserId().equals(commentUserId)) {
                        NotifyDO notify = new NotifyDO(commentUserId, childCommentDO.getUserId(), commentId, talkId, NotifyType.comment_comment);
                        notifies.add(notify);
                    }
                }
            }

            Date curDate = new Date();
            //只有子评论需要replyUser
            if (replyCommentDO != null) {
                //判断不重复发送通知，如果子回复和talk或者和评论相同，则不需要再发送通知了

                //如果自己回复自己就不需要发通知了
                NotifyDO replyCommentNotify = new NotifyDO(commentUserId, replyCommentDO.getUserId(), commentId, talkId, NotifyType.reply_comment);
                notifies.add(replyCommentNotify);
            }
        }
        Collections.reverse(notifies);
        notifies = notifies.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() ->
                new TreeSet<>(Comparator.comparing(NotifyDO::getMessageReceiveId))), ArrayList::new));
        notifies = notifyRepository.saveAll(notifies);
        return notifies;
    }*/

    @Resource
    private SocialUserAccountRepository socialUserAccountRepository;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private MessageReceiveRepository messageReceiveRepository;

    public void sendNotifies(List<NotifyDO> notifies, SocialuniUserDo mineUser) {
        for (NotifyDO notify : notifies) {
            sendNotify(notify, mineUser);
        }
    }

    //发送通知
    public void sendNotify(NotifyDO notify, SocialuniUserDo sendUser) throws SocialException {
        //评论动态
//        UserDO receiveUser = userRepository.findById(receiveUserId).get();
        Integer receiveUserId = notify.getBeUserId();

        SocialUserAccountDO receiveAccount = socialUserAccountRepository.findByUserIdOrderByUpdateTimeDesc(receiveUserId);
//        if (receiveAccount != null) {
        String notifyType = notify.getType();
//        String provider = receiveAccount.getProvider();
        if (NotifyType.comments.contains(notifyType)) {
            //显示在动态主页右上角的通知，只需要发送者的名称头像图片
//                WebsocketServer.sendMessage(receiveUserId.toString(), new NotifyVO(requestUser));
        }
        PushMsgDTO pushMsgDTO = null;

        if (!NotifyType.notifies.contains(notifyType)) {
            log.error("错误的通知类型：{}", notifyType);
            throw new SocialParamsException("错误的通知类型");
        }

        if (NotifyType.message.equals(notifyType)) {
            SocialuniMessageReceiveDO messageReceiveDO = messageReceiveRepository.getReferenceById(notify.getContentId());
            SocialuniChatUserDO chatUserDO = SocialuniRepositoryFacade.findById(messageReceiveDO.getChatUserId(), SocialuniChatUserDO.class);
//                Optional<ChatDO> chatDOOptional = chatRepository.findById();
            //如果群聊，直接发送给两个服务器在线的所有用户，并且查找他们未读的。
            //未登录的时候也查询群聊里面的所有内容
            NotifyVO notifyVO = SocaluniNotifyROFactory.getNotifyROBySendMsg(notify, sendUser, messageReceiveDO, chatUserDO);
            WebsocketServer.sendMessage(receiveUserId, notifyVO);
                /*try {
                    stringRedisTemplate.convertAndSend(receiveUserId.toString(), JsonUtil.objectMapper.writeValueAsString(notifyVO));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }*/
        } else {
            //评论动态
                /*switch (notifyType) {
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
                if (provider.equals(SocialuniSupportProviderType.qq)) {
                    QQUtil.qqPushMsgCommon(receiveAccount.getMpOpenId(), provider, pushMsgDTO, notify);
                } else if (provider.equals(SocialuniSupportProviderType.wx)) {
                    WxUtil.wxPushMsgCommon(receiveAccount.getMpOpenId(), provider, pushMsgDTO, notify);
                }*/
        }
//        }


//        messagingTemplate.convertAndSendToUser(notify.getReceiveUser().getId().toString(), "/queue/notifications", notifyVO);
        //消息入库
        /*NotifyVO notifyVO = new NotifyVO(notify);*/
        //给前台发送消息
        /*String notifyString = null;
        try {
            notifyString = JsonUtil.objectMapper.writeValueAsString(notifyVO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //给对方发送喜欢通知，不发送通知
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
