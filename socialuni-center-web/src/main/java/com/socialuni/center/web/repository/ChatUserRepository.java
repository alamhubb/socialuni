package com.socialuni.center.web.repository;

import com.socialuni.center.web.model.DO.chat.ChatUserDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * TODO〈一句话功能简述〉
 * TODO〈功能详细描述〉
 *
 * @author qinkaiyuan
 * @since TODO[起始版本号]
 */
public interface ChatUserRepository extends JpaRepository<ChatUserDO, Integer> {
    //根据chat状态，chatuser状态，置顶倒序，置顶等级升序，时间倒序
/*
    @Query("select ChatUserDO from ChatUserDO u,ChatDO where Chatu.chatId=ChatDO.id and ChatDO.type not in (:chatTypes) and ChatDO.status =:chatStatus and Chatu.userId=:userId and Chatu.status =:status")
    List<ChatUserDO> findByChatTypeNotInAndChatStatusAndUserIdAndStatusOrderByTopFlagDescChatTopLevelAscUpdateTimeDesc(List<String> chatTypes, String chatStatus, Integer userId, String status);
*/

    //查询chat下，user的chatUser
    Optional<ChatUserDO> findFirstByChatIdAndChatStatusAndUserId(Integer chatId, String chatStatus, Integer userId);

    //只有发送消息时，才需要使用这个，校验状态，其他情况不需要
    Optional<ChatUserDO> findFirstByChatIdAndChatStatusAndUserIdAndStatus(Integer chatId, String chatStatus, Integer userId, String status);

    Optional<ChatUserDO> findFirstByChatStatusAndUserIdAndReceiveUserId(String chatStatus, Integer userId, Integer receiveUserId);


    //只有发送消息时，才需要使用这个，校验状态，其他情况不需要
    List<ChatUserDO> findByChatIdAndChatStatusAndStatus(Integer chatId, String chatStatus, String status);

    List<ChatUserDO> findByChatIdAndChatStatus(Integer chatId, String chatStatus);

    //根据chatuserId，chatUserStatus，topFlag，update，frontShow

    //先不使用chat状态，查询user下的chatuser,根据topLevel倒序，topflag倒序，更新时间倒序
    List<ChatUserDO> findByChatStatusAndUserIdAndFrontShowTrueOrderByChatTopLevelDescTopFlagDescUpdateTimeDesc(String chatStatus, Integer userId);
}