package com.socialuni.social.likee.dao.repository;

import com.socialuni.social.likee.dao.DO.SocialuniUserLikeChatDO;
import com.socialuni.social.tance.model.DO.DevAccountDo;
import com.socialuni.social.tance.sdk.api.DevAccountInterface;
import com.socialuni.social.tance.sdk.model.DevAccountModel;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


/**
 * @author qinkaiyuan
 * @since 1.0.0
 */
public interface SocialuniLikeUserChatRepository extends JpaRepository<SocialuniUserLikeChatDO, Integer> {

    SocialuniUserLikeChatDO findByChatId(Integer chatId);
}