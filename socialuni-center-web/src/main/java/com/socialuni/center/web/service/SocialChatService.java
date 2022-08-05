package com.socialuni.center.web.service;

import com.socialuni.center.web.model.OpenChatVO;
import com.socialuni.center.web.model.RO.UserIdVO;
import com.socialuni.center.web.domain.chat.ChatQueryDomain;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.center.web.model.RO.message.chat.ChatRO;
import com.socialuni.center.web.model.RO.message.chat.ChatReadVO;
import com.socialuni.center.web.model.RO.message.chat.ChatRemoveVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:45
 */

@Service
public class SocialChatService {

    @Resource
    ChatQueryDomain chatQueryDomain;


    public ResultRO<?> readChatMessages(ChatReadVO chatVO) {
        return null;
    }

    public ResultRO<List<ChatRO>> queryHomeChat() {
        return null;
    }


    public ResultRO<List<ChatRO>> queryChatList() {
        return new ResultRO<>(chatQueryDomain.getChats());
//        return new ResultRO<>();
    }

    public ResultRO<ChatRO> queryChat(UserIdVO receiveUserVO) {
        return null;
    }


    public ResultRO<ChatRO> openChat(OpenChatVO chatVO) {
        return null;
    }


    public ResultRO<?> frontDeleteChat(ChatRemoveVO chatVO) {
        return null;
    }


    public ResultRO<?> removeChat(ChatRemoveVO chatVO) {
        return null;
    }


    public ResultRO<?> closeChat(ChatRemoveVO chatVO) {
        return null;
    }
}
