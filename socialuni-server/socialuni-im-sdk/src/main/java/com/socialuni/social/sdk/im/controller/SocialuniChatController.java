package com.socialuni.social.sdk.im.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.im.api.feign.SocialuniChatAPI;
import com.socialuni.social.im.api.model.QO.SocialuniChatQueryQO;
import com.socialuni.social.im.api.model.RO.SocialuniImUserDetailRO;
import com.socialuni.social.sdk.im.logic.service.chat.ChatService;
import com.socialuni.social.im.api.model.QO.chat.OpenChatVO;
import com.socialuni.social.im.api.model.RO.ChatRO;
import com.socialuni.social.im.api.model.QO.chat.ChatReadVO;
import com.socialuni.social.im.api.model.QO.chat.ChatRemoveVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:45
 */

@RestController
@RequestMapping("socialuni/chat")
public class SocialuniChatController implements SocialuniChatAPI {
    @Resource
    ChatService chatService;

    @Override
    public ResultRO<?> readChatMessages(ChatReadVO chatVO) {
        return chatService.readChatMessages(chatVO);
    }

    @Override
    public ResultRO<List<ChatRO>> queryHomeChat() {
        return null;
    }

    @Override
    public ResultRO<List<ChatRO>> queryChatList() {
        return chatService.queryChatList();
    }

    @Override
    public ResultRO<ChatRO> queryChat(SocialuniChatQueryQO socialuniChatQueryQO) {
        return chatService.queryChat(socialuniChatQueryQO);
    }

    @Override
    public ResultRO<List<ChatRO>> openChat(OpenChatVO chatVO) {
        return null;
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
    public ResultRO<?> frontDeleteChat(SocialuniChatQueryQO chatVO) {
        return null;
    }

    @Override
    public ResultRO<?> removeChat(SocialuniChatQueryQO chatVO) {
        return null;
    }

    @Override
    public ResultRO<?> quitGroupChat(SocialuniChatQueryQO chatVO) {
        return chatService.quitGroupChat(chatVO);
    }
}
