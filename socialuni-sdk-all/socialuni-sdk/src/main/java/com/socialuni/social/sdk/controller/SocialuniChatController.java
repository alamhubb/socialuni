package com.socialuni.social.sdk.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.sdk.feignAPI.SocialuniChatAPI;
import com.socialuni.social.sdk.model.RO.message.chat.ChatRO;
import com.socialuni.social.sdk.model.RO.message.chat.ChatReadVO;
import com.socialuni.social.sdk.model.RO.message.chat.ChatRemoveVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:45
 */

@RestController
@RequestMapping("socialuni/chat")
public class SocialuniChatController implements SocialuniChatAPI {
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
        List<ChatRO> list = new ArrayList<>();
        return ResultRO.success(list);
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
