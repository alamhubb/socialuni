package com.socialuni.social.user.sdk.repository;


import com.socialuni.social.common.api.constant.CommonRedisKey;
import com.socialuni.social.common.sdk.dao.DO.SocialuniTagDO;
import com.socialuni.social.common.sdk.dao.redis.TagRedisKey;
import com.socialuni.social.user.sdk.dao.DO.SocialuniUserExtendFriendLogDo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface SocialuniUserExtendFriendLogRepository extends JpaRepository<SocialuniUserExtendFriendLogDo, Integer> {

    @Cacheable(cacheNames = "findUserIdByUpdateTimeLessThan")
    @Query(nativeQuery = true, value = "select DISTINCT s.user_id from s_user_extend_friend_log s where s.update_time>=:updateTime order by s.update_time desc limit 1000")
    List<Integer> findUserIdByUpdateTimeLessThan(Date updateTime);

    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "findUserIdByUpdateTimeLessThan", allEntries = true),
            }
    )
    SocialuniUserExtendFriendLogDo save(SocialuniUserExtendFriendLogDo socialuniUserExtendFriendLogDo);
}

