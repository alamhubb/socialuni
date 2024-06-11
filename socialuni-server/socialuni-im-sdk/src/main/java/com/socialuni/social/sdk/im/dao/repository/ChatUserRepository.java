package com.socialuni.social.sdk.im.dao.repository;


import com.socialuni.social.common.api.constant.CommonRedisKey;
import com.socialuni.social.sdk.im.constant.ChatUserRedisKey;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatUserDO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * TODO〈一句话功能简述〉
 * TODO〈功能详细描述〉
 *
 * @author qinkaiyuan
 * @since TODO[起始版本号]
 */
public interface ChatUserRepository extends JpaRepository<SocialuniChatUserDO, Integer> {
    //根据chat状态，chatuser状态，置顶倒序，置顶等级升序，时间倒序
/*
    @Query("select ChatUserDO from ChatUserDO u,ChatDO where Chatu.chatId=ChatDO.id and ChatDO.type not in (:chatTypes) and ChatDO.status =:Status and Chatu.userId=:userId and Chatu.status =:status")
    List<ChatUserDO> findByChatTypeNotInAndStatusAndUserIdAndStatusOrderByTopFlagDescChatTopLevelAscUpdateTimeDesc(List<String> chatTypes, String Status, Integer userId, String status);
*/

    //只有发送消息时，才需要使用这个，校验状态，其他情况不需要
    SocialuniChatUserDO findFirstByChatIdAndUserIdAndStatus(Integer chatId, Integer userId, String Status);

    Optional<SocialuniChatUserDO> findFirstByStatusAndUserIdAndBeUserId(String Status, Integer userId, Integer BeUserId);


    SocialuniChatUserDO findOneByChatIdAndUserId(Integer chatId, Integer userId);


    //只有发送消息时，才需要使用这个，校验状态，其他情况不需要
//    List<SocialuniChatUserDO> findByChatIdAndStatusAndStatus(Integer chatId, String Status, String status);

    List<SocialuniChatUserDO> findByChatIdAndStatus(Integer chatId, String Status);

    @Cacheable(cacheNames = ChatUserRedisKey.findChatUserIdsByChatIdAndStatus, key = "#chatId + '-' + #status")
    @Query("select s.id from SocialuniChatUserDO s where s.chatId =:chatId and s.status =:status")
    List<Integer> findChatUserIdsByChatIdAndStatus(Integer chatId, String status);

    //根据chatuserId，chatUserStatus，topFlag，update，frontShow

    //先不使用chat状态，查询user下的chatuser,根据topLevel倒序，topflag倒序，更新时间倒序
    List<SocialuniChatUserDO> findByStatusAndUserIdOrderByUpdateTimeDesc(String Status, Integer userId);

    List<SocialuniChatUserDO> findByStatusAndUserIdAndFrontShowTrueOrderByTopFlagDescTopFlagDescUpdateTimeDesc(String Status, Integer userId);
}