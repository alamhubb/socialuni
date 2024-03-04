package com.socialuni.social.sdk.im.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.sdk.im.dao.repository.SocialuniChatRepository;
import com.socialuni.social.sdk.im.dao.repository.ChatUserRepository;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatUserDO;
import com.socialuni.social.sdk.im.enumeration.ChatUserStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ChatUserVerify {
    @Resource
    private SocialuniChatRepository chatRepository;
    @Resource
    private ChatUserRepository chatUserRepository;

    //基础，不存在就是系统异常
    public ResultRO<SocialuniChatUserDO> checkChatHasUserId(Long chatId, Integer userId) {
//        Optional<ChatUserDO> chatUserDOOptional = chatUserRepository.findFirstByChatIdAndChatStatusAndUserId(chatId, ChatStatus.enable, userId);
//        return chatUserDOOptional.map(ResultRO::new).orElseGet(() -> new ResultRO<>(ErrorCode.SYSTEM_ERROR));
        /*Optional<ChatDO> chatDOOptional = chatRepository.findById(chatId);
        if (chatDOOptional.isPresent()) {
            ChatDO chatDO = chatDOOptional.get();

        }*/
        return null;
    }

    public ResultRO<SocialuniChatUserDO> checkChatHasUserIdAndEnable(Long chatId, Integer userId) {
        ResultRO<SocialuniChatUserDO> ResultRO = this.checkChatHasUserId(chatId, userId);
        if (ResultRO.hasError()) {
            return ResultRO;
        }
        SocialuniChatUserDO chatUserDO = ResultRO.getData();
        if (!chatUserDO.getStatus().equals(ChatUserStatus.enable)) {
//            return new ResultRO<>(ErrorCode.SYSTEM_ERROR);
        }
        return new ResultRO<>(chatUserDO);
    }
}
