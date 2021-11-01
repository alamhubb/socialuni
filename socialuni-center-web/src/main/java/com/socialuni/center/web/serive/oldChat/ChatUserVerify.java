package com.socialuni.center.web.serive.oldChat;

import com.socialuni.social.constant.ErrorCode;
import com.socialuni.social.model.common.ResultRO;
import com.socialuni.web.constant.status.ChatStatus;
import com.socialuni.web.constant.status.ChatUserStatus;
import com.socialuni.web.model.chat.ChatUserDO;
import com.socialuni.web.repository.chat.ChatRepository;
import com.socialuni.web.repository.chat.ChatUserRepository;
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
    public ResultRO<ChatUserDO> checkChatHasUserId(Long chatId, Integer userId) {
        Optional<ChatUserDO> chatUserDOOptional = chatUserRepository.findFirstByChatIdAndChatStatusAndUserId(chatId, ChatStatus.enable, userId);
        return chatUserDOOptional.map(ResultRO::new).orElseGet(() -> new ResultRO<>(ErrorCode.SYSTEM_ERROR));
        /*Optional<ChatDO> chatDOOptional = chatRepository.findById(chatId);
        if (chatDOOptional.isPresent()) {
            ChatDO chatDO = chatDOOptional.get();

        }*/
    }

    public ResultRO<ChatUserDO> checkChatHasUserIdAndEnable(Long chatId, Integer userId) {
        ResultRO<ChatUserDO> resultRO = this.checkChatHasUserId(chatId, userId);
        if (resultRO.hasError()) {
            return resultRO;
        }
        ChatUserDO chatUserDO = resultRO.getData();
        if (!chatUserDO.getStatus().equals(ChatUserStatus.enable)) {
            return new ResultRO<>(ErrorCode.SYSTEM_ERROR);
        }
        return new ResultRO<>(chatUserDO);
    }
}
