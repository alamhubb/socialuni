package com.socialuni.social.im.controller;

import com.socialuni.social.common.api.constant.ErrorCode;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.im.dao.ChatRepository;
import com.socialuni.social.im.dao.ChatUserRepository;
import com.socialuni.social.im.dao.DO.ChatUserDO;
import com.socialuni.social.im.enumeration.ChatStatus;
import com.socialuni.social.im.enumeration.ChatUserStatus;
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
//        Optional<ChatUserDO> chatUserDOOptional = chatUserRepository.findFirstByChatIdAndChatStatusAndUserId(chatId, ChatStatus.enable, userId);
//        return chatUserDOOptional.map(ResultRO::new).orElseGet(() -> new ResultRO<>(ErrorCode.SYSTEM_ERROR));
        /*Optional<ChatDO> chatDOOptional = chatRepository.findById(chatId);
        if (chatDOOptional.isPresent()) {
            ChatDO chatDO = chatDOOptional.get();

        }*/
        return null;
    }

    public ResultRO<ChatUserDO> checkChatHasUserIdAndEnable(Long chatId, Integer userId) {
        ResultRO<ChatUserDO> ResultRO = this.checkChatHasUserId(chatId, userId);
        if (ResultRO.hasError()) {
            return ResultRO;
        }
        ChatUserDO chatUserDO = ResultRO.getData();
        if (!chatUserDO.getStatus().equals(ChatUserStatus.init)) {
//            return new ResultRO<>(ErrorCode.SYSTEM_ERROR);
        }
        return new ResultRO<>(chatUserDO);
    }
}
