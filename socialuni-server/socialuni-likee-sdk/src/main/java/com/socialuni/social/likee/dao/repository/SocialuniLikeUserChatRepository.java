package com.socialuni.social.likee.dao.repository;

import com.socialuni.social.likee.constant.SocialuniUserLikeRedisKey;
import com.socialuni.social.likee.dao.DO.SocialuniUserLikeChatDO;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author qinkaiyuan
 * @since 1.0.0
 */
public interface SocialuniLikeUserChatRepository extends JpaRepository<SocialuniUserLikeChatDO, Integer> {

    @Cacheable(cacheNames = SocialuniUserLikeRedisKey.findFirstUserLikeByChatId, key = "#chatId")
    SocialuniUserLikeChatDO findByChatId(Long chatId);

    @Caching(
            put = {@CachePut(cacheNames = SocialuniUserLikeRedisKey.findFirstUserLikeByChatId, key = "#socialuniUserLikeChatDO.chatId")}
    )
    default SocialuniUserLikeChatDO savePut(SocialuniUserLikeChatDO socialuniUserLikeChatDO) {
        return this.save(socialuniUserLikeChatDO);
    }

}