package com.socialuni.center.web.serive.oldChat;

import com.qingchi.base.constant.ChatType;
import com.qingchi.base.constant.status.ChatStatus;
import com.qingchi.base.model.chat.ChatDO;
import com.qingchi.base.model.chat.ChatUserDO;
import com.qingchi.base.model.user.UserDO;
import com.qingchi.base.modelVO.ChatVO;
import com.qingchi.base.repository.chat.ChatRepository;
import com.qingchi.base.repository.chat.ChatUserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-06-16 12:39
 */
@Service
public class ChatUserService {
    @Resource
    ChatRepository chatRepository;
    @Resource
    ChatUserRepository chatUserRepository;

    //登录情况下查询用户有权限的chatuser
    //初始化和查询chat列表触发的
    public List<ChatVO> getChats(UserDO user) {
        //未登录的情况只插叙你官方的chats
        List<ChatUserDO> chatUsers = chatUserRepository.findByChatStatusAndUserIdAndFrontShowTrueOrderByChatTopLevelDescTopFlagDescUpdateTimeDesc(ChatStatus.enable, user.getId());
        //查询的时候chat列表展示不为当前用户的
        /*return chatUsers.stream().map((ChatUserDO chatUserDO) -> {
            //只有启用的才显示消息列表
            if (chatUserDO.getStatus().equals(ChatUserStatus.enable)) {
                return new ChatVO(chatUserDO.getChat(), chatUserDO, true);
            } else {
                return new ChatVO(chatUserDO.getChat(), chatUserDO);
            }
        }).collect(Collectors.toList());*/
        return ChatVO.chatUserDOToVOS(chatUsers);
    }

    //未登录的情况下查询官方chat，官方群聊
    public List<ChatVO> getChats() {
        //未登录的情况只插叙你官方的chats
        List<ChatDO> chats = chatRepository.findByStatusAndTypeInOrderByTopLevelDescUpdateTimeDesc(ChatStatus.enable, ChatType.systemChats);
        return ChatVO.chatDOToVOS(chats);
    }
}
