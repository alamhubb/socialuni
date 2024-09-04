package com.socialuni.social.likee.dao.repository;

import com.socialuni.social.likee.constant.SocialuniUserLikeRedisKey;
import com.socialuni.social.likee.dao.DO.SocialuniUserLikeChatDO;
import com.socialuni.social.sdk.im.constant.ChatRedisKey;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.tance.model.DO.DevAccountDo;
import com.socialuni.social.tance.sdk.api.DevAccountInterface;
import com.socialuni.social.tance.sdk.model.DevAccountModel;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


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