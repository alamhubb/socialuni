package com.socialuni.social.likee.dao.repository;

import com.socialuni.social.likee.dao.DO.SocialuniUserLikeChatDO;
import com.socialuni.social.likee.dao.DO.SocialuniUserLikeDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * @author qinkaiyuan
 * @since 1.0.0
 */
public interface SocialuniLikeUseRepository extends JpaRepository<SocialuniUserLikeDO, Integer> {

    @Query(nativeQuery = true, value = "select s.be_user_id from s_user_like s order by s.update_time desc limit 2000")
    List<Long> findBeLikeUserIdsOrderByUpdateTimeDesc();
}