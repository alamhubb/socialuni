package com.socialuni.social.sdk.im.dao.repository;


import com.socialuni.social.sdk.im.constant.ChatUserRedisKey;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatUserDO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * TODO〈一句话功能简述〉
 * TODO〈功能详细描述〉
 *
 * @author qinkaiyuan
 * @since TODO[起始版本号]
 */
public interface SocialuniChatUserRepository extends JpaRepository<SocialuniChatUserDO, Integer> {
    //根据chat状态，chatuser状态，置顶倒序，置顶等级升序，时间倒序
/*
    @Query("select ChatUserDO from ChatUserDO u,ChatDO where Chatu.chatId=ChatDO.id and ChatDO.type not in (:chatTypes) and ChatDO.status =:Status and Chatu.userId=:userId and Chatu.status =:status")
    List<ChatUserDO> findByChatTypeNotInAndStatusAndUserIdAndStatusOrderByTopFlagDescChatTopLevelAscUpdateTimeDesc(List<String> chatTypes, String Status, Long userId, String status);
*/



//    SocialuniChatUserDO findFirstByStatusAndUserIdAndBeUserId(String Status, Long userId, Long beUserId);

//    两种，群聊是 chatId+userId是唯一标识，  私聊的话呢，是两个userId为唯一标识
    @Cacheable(cacheNames = ChatUserRedisKey.findFirstByChatIdAndUserId, key = "#chatId+'-'+#userId")
    @Query("select s.id from SocialuniChatUserDO s where s.chatId =:chatId and s.userId =:userId")
    Integer findFirstByChatIdAndUserId(Long chatId, Long userId);

    @Cacheable(cacheNames = ChatUserRedisKey.findFirstByUserIdAndBeUserId, key = "#userId+'-'+#beUserId")
    @Query("select s.id from SocialuniChatUserDO s where s.userId =:userId and s.beUserId =:beUserId")
    Integer findFirstByUserIdAndBeUserId(Long userId, Long beUserId);

    //只有发送消息时，才需要使用这个，校验状态，其他情况不需要
    SocialuniChatUserDO findFirstByChatIdAndUserIdAndStatus(Long chatId, Long userId, String Status);


    //只有发送消息时，才需要使用这个，校验状态，其他情况不需要
//    List<SocialuniChatUserDO> findByChatIdAndStatusAndStatus(Long chatId, String Status, String status);

    List<SocialuniChatUserDO> findByChatIdAndStatus(Long chatId, String Status);

    @Cacheable(cacheNames = ChatUserRedisKey.findChatUserIdsByChatIdAndStatus, key = "#chatId")
    @Query("select s.id from SocialuniChatUserDO s where s.chatId =:chatId and s.status =:status")
    List<Integer> findChatUserIdsByChatIdAndStatus(Long chatId, String status);


    @Cacheable(cacheNames = ChatUserRedisKey.findFirstChatUserById, key = "#id")
    SocialuniChatUserDO findFirstById(Integer id);
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = ChatUserRedisKey.findChatUserIdsByChatIdAndStatus, key="#socialuniChatUserDO.chatId"),
                    @CacheEvict(cacheNames = ChatUserRedisKey.findByUserIdAndStatusOrderByUpdateTimeDesc, key="#socialuniChatUserDO.userId"),
                    @CacheEvict(cacheNames = ChatUserRedisKey.findFirstByChatIdAndUserId, key = "#socialuniChatUserDO.chatId+'-'+#socialuniChatUserDO.userId"),
                    @CacheEvict(cacheNames = ChatUserRedisKey.findFirstByUserIdAndBeUserId, key = "#socialuniChatUserDO.beUserId+'-'+#socialuniChatUserDO.userId"),
            },
            put = {
                    @CachePut(cacheNames = ChatUserRedisKey.findFirstChatUserById, key = "#socialuniChatUserDO.id"),
                    @CachePut(cacheNames = ChatUserRedisKey.findFirstByChatIdAndUserId, key = "#socialuniChatUserDO.chatId+'-'+#socialuniChatUserDO.userId"),
                    @CachePut(cacheNames = ChatUserRedisKey.findFirstByUserIdAndBeUserId, key = "#socialuniChatUserDO.userId+'-'+#socialuniChatUserDO.beUserId"),
            }
    )
    default SocialuniChatUserDO savePut(SocialuniChatUserDO socialuniChatUserDO) {
        return this.save(socialuniChatUserDO);
    }


    @Caching(
            evict = {
                    @CacheEvict(cacheNames = ChatUserRedisKey.findChatUserIdsByChatIdAndStatus, key="#socialuniChatUserDOs[0].chatId"),
            }
    )
    default List<SocialuniChatUserDO> saveAllPut(List<SocialuniChatUserDO> socialuniChatUserDOs) {
        return this.saveAll(socialuniChatUserDOs);
    }


    //根据chatuserId，chatUserStatus，topFlag，update，frontShow

    //先不使用chat状态，查询user下的chatuser,根据topLevel倒序，topflag倒序，更新时间倒序
    @Cacheable(cacheNames = ChatUserRedisKey.findByUserIdAndStatusOrderByUpdateTimeDesc, key = "#userId")
    @Query("select s.id from SocialuniChatUserDO s where s.userId =:userId and s.status =:status order by s.updateTime desc")
    List<Integer> findByUserIdAndStatusOrderByUpdateTimeDesc(Long userId,String status);


    @Query(value = "SELECT s.id FROM SocialuniChatUserDO s where s.id in (:chatUserIds) and ((s.updateTime<:queryTime) or (:queryTime is null)) order by s.updateTime desc")
    List<Integer> queryChatUserIdsByIds(
            @Param("chatUserIds") List<Integer> chatUserIds,
            @Param("queryTime") Date queryTime,
            Pageable pageable);

    List<SocialuniChatUserDO> findByStatusAndUserIdAndFrontShowTrueOrderByTopFlagDescTopFlagDescUpdateTimeDesc(String Status, Long userId);
}