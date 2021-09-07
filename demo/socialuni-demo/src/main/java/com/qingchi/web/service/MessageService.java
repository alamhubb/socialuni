/*
package com.qingchi.web.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qingchi.web.manage.ChatUserVerify;
import com.socialuni.constant.ChatType;
import com.socialuni.constant.ChatStatus;
import com.socialuni.constant.ChatUserStatus;
import com.socialuni.constant.MatchType;
import com.socialuni.sdk.domain.notify.NotifyDomain;
import com.socialuni.sdk.domain.report.ReportDomain;
import com.socialuni.sdk.exception.SocialBusinessException;
import com.socialuni.sdk.exception.SocialParamsException;
import com.socialuni.entity.model.DO.NotifyDO;
import com.socialuni.entity.model.DO.chat.ChatDO;
import com.socialuni.entity.model.DO.chat.ChatUserDO;
import com.socialuni.entity.model.DO.message.MessageDO;
import com.socialuni.entity.model.DO.message.MessageReceiveDO;
import com.socialuni.entity.model.DO.user.UserDO;
import com.socialuni.sdk.model.NotifyVO;
import com.socialuni.sdk.redis.RedisSubListenerConfig;
import com.socialuni.sdk.repository.*;
import com.socialuni.sdk.service.ReportService;
import com.socialuni.sdk.service.comment.IllegalWordService;
import com.socialuni.sdk.service.content.ModelContentCheck;
import com.socialuni.sdk.utils.common.JsonUtil;
import com.socialuni.social.api.model.model.ResultRO;
import com.socialuni.social.model.model.RO.message.message.MessageAddVO;
import com.socialuni.social.model.model.RO.message.message.SocialMessageRO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

*/
/**
 * @author qinkaiyuan
 * @date 2019-06-16 12:39
 *//*

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
    private NotifyDomain notifyDomain;
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


    public ResultRO<SocialMessageRO> sendMsg(UserDO user, MessageAddVO msgAddVO) throws IOException {
        Integer chatId = msgAddVO.getChatId();
        String talkContent = msgAddVO.getContent();
        if (StringUtils.isEmpty(talkContent)) {
            throw new SocialParamsException("不能发布空内容");
        }
        modelContentCheck.checkUser(user);
        //校验内容是否违规

        ResultRO<ChatUserDO> resultRO = chatUserVerify.checkChatHasUserIdAndEnable(chatId, user.getId());
        if (resultRO.hasError()) {
            return new ResultRO<>(resultRO);
        }

        ChatDO chat = resultRO.getData().getChat();

        //生成消息，先不管群聊只管私聊，私聊会有receiveUser，用来判断已读未读
        //只管群聊
        //查看chat的类型
        if (chat.getType().equals(ChatType.system_group)) {
            modelContentCheck.checkContent(talkContent);
            //校验内容是否违规

            //发送群聊时更新chat时间
            Date curDate = new Date();
            chat.setUpdateTime(curDate);
            //下版本可删除
//            chat.setLastContent(content);
            chatRepository.save(chat);

            //不校验权限，只要是系统用户就可以发送
            MessageDO message = messageRepository.save(new MessageDO(chat.getId(), msgAddVO.getContent(), user.getId()));
            //校验是否触发关键词，如果触发生成举报，修改动态为预审查，只能用户自己可见
            reportDomain.checkKeywordsCreateReport(message);

            NotifyVO notifyVO = new NotifyVO(chat, user, message);
            //如果官方群聊，则给所有人发送信息
            try {
                stringRedisTemplate.convertAndSend(RedisSubListenerConfig.allUserKey, JsonUtil.objectMapper.writeValueAsString(notifyVO));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return new ResultRO<>(new SocialMessageRO(message, user.getId()));
            //不为官方群聊
        } else {
            //如果是官方通知和
            //如果为官方群聊，则所有人都可以发送内容
            //查询用户是否有权限往chat中发送内容

            String content = msgAddVO.getContent();
            //构建消息
            MessageDO message = messageRepository.save(new MessageDO(chat.getId(), content, user.getId()));

            Date curDate = new Date();
            chat.setUpdateTime(curDate);
//            chat.setLastContent(content);
            chatRepository.save(chat);

            List<NotifyDO> notifies = new ArrayList<>();
            //有权限，则给chat中的所有用户发送内容

            List<ChatUserDO> chatUserDOS = chatUserRepository.findByChatIdAndChatStatusAndStatus(chatId, ChatStatus.enable, ChatUserStatus.enable);
            //包含自己，起码要有两个人
            if (chatUserDOS.size() <= 1) {
                //用户给自己被踢出来，或者自己删除的内容发消息。提示异常
                throw new SocialBusinessException("对方已退出聊天");
            }
            MessageReceiveDO mineMessageUser = new MessageReceiveDO();
            //发送消息
            for (ChatUserDO chatUserDO : chatUserDOS) {
//                chatUserDO.setLastContent(message.getContent());
                chatUserDO.setUpdateTime(curDate);
                //如果为匹配chat，且为待匹配状态
                if (ChatType.match.equals(chat.getType()) && MatchType.waitMatch.equals(chat.getMatchStatus())) {
                    //则将用户的chat改为匹配成功
                    chatUserDO.setStatus(ChatUserStatus.enable);
                }
                //获取当起chatUser的userId
                Integer chatUserId = chatUserDO.getUserId();
                MessageReceiveDO messageReceiveDO = new MessageReceiveDO(chatUserDO, user.getId(), chatUserId, message);
                //自己的话不发送通知，自己的话也要构建消息，要不看不见，因为读是读这个表
                if (chatUserId.equals(user.getId())) {
                    messageReceiveDO.setIsMine(true);
                    messageReceiveDO.setIsRead(true);
                    mineMessageUser = messageReceiveRepository.save(messageReceiveDO);
                } else {
                    //别人的chatUser，要增加未读，自己刚发的消息，别人肯定还没看
                    chatUserDO.setUnreadNum(chatUserDO.getUnreadNum() + 1);
                    //接收方，更改前端显示为显示
                    chatUserDO.checkFrontShowAndSetTrue();
                    MessageReceiveDO messageReceiveDO1 = messageReceiveRepository.save(messageReceiveDO);
//                    NotifyDO notifyDO = notifyRepository.save(new NotifyDO(messageReceiveDO1));
//                    notifies.add(notifyDO);
                }
            }
            chatUserRepository.saveAll(chatUserDOS);
            notifyRepository.saveAll(notifies);
            //保存message
            notifyDomain.sendNotifies(notifies, user);
            //只需要返回自己的
            return new ResultRO<>(new SocialMessageRO(mineMessageUser));
        }
    }

}
*/
