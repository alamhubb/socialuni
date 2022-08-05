package com.socialuni.center.web.controller;

import com.socialuni.center.web.feignAPI.SocialuniChatAPI;
import com.socialuni.center.web.service.SocialChatService;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.center.web.model.RO.message.chat.ChatRO;
import com.socialuni.center.web.model.RO.message.chat.ChatReadVO;
import com.socialuni.center.web.model.RO.message.chat.ChatRemoveVO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:45
 */

@RestController
public class CenterChatController implements SocialuniChatAPI {
    @Resource
    SocialChatService socialChatService;

    @Override
    public ResultRO<?> readChatMessages(ChatReadVO chatVO) {
        return null;
    }

    @Override
    public ResultRO<List<ChatRO>> queryHomeChat() {
        return null;
    }

    @Override
    public ResultRO<List<ChatRO>> queryChats() {
        return socialChatService.queryChatList();
    }

/*    @Override
    public ResultRO<ChatRO> queryChat(UserIdVO receiveUserVO) {
        return null;
    }

    @Override
    public ResultRO<ChatRO> openChat(OpenChatVO chatVO) {
        return null;
    }*/

    @Override
    public ResultRO<?> frontDeleteChat(ChatRemoveVO chatVO) {
        return null;
    }

    @Override
    public ResultRO<?> removeChat(ChatRemoveVO chatVO) {
        return null;
    }

    @Override
    public ResultRO<?> closeChat(ChatRemoveVO chatVO) {
        return null;
    }
}
