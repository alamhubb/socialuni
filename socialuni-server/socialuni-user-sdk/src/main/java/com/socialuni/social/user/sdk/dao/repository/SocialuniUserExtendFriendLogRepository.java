package com.socialuni.social.user.sdk.dao.repository;


import com.socialuni.social.user.sdk.dao.DO.SocialuniUserExtendFriendLogDo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface SocialuniUserExtendFriendLogRepository extends JpaRepository<SocialuniUserExtendFriendLogDo, Integer> {

    @Cacheable(cacheNames = "findUserIdByUpdateTimeLessThan")
    @Query(nativeQuery = true, value = "select s.user_id from s_user_extend_friend_log s where s.update_time>=:updateTime order by s.update_time desc limit 2000")
    List<Long> findUserIdByUpdateTimeLessThan(Date updateTime);

    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "findUserIdByUpdateTimeLessThan", allEntries = true),
            }
    )
    SocialuniUserExtendFriendLogDo save(SocialuniUserExtendFriendLogDo socialuniUserExtendFriendLogDo);
}

