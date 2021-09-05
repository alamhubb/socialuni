package com.qingchi.web.manage;

import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.constant.ChatStatus;
import com.socialuni.social.constant.ChatUserStatus;
import com.socialuni.social.entity.model.DO.chat.ChatUserDO;
import com.socialuni.social.exception.SocialParamsException;
import com.socialuni.social.sdk.repository.ChatRepository;
import com.socialuni.social.sdk.repository.ChatUserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class ChatUserVerify {
    @Resource
    private ChatRepository chatRepository;
    @Resource
    private ChatUserRepository chatUserRepository;

    //基础，不存在就是系统异常
    public ResultRO<ChatUserDO> checkChatHasUserId(Integer chatId, Integer userId) {
        Optional<ChatUserDO> chatUserDOOptional = chatUserRepository.findFirstByChatIdAndChatStatusAndUserId(chatId, ChatStatus.enable, userId);
        if (chatUserDOOptional.isPresent()) {
            return new ResultRO<>();
        }
        throw new SocialParamsException("会话用户异常");
    }

    public ResultRO<ChatUserDO> checkChatHasUserIdAndEnable(Integer chatId, Integer userId) {
        ResultRO<ChatUserDO> resultRO = this.checkChatHasUserId(chatId, userId);
        if (resultRO.hasError()) {
            return resultRO;
        }
        ChatUserDO chatUserDO = resultRO.getData();
        if (!chatUserDO.getStatus().equals(ChatUserStatus.enable)) {
            throw new SocialParamsException("chat状态不该为可用");
        }
        return new ResultRO<>(chatUserDO);
    }
}
