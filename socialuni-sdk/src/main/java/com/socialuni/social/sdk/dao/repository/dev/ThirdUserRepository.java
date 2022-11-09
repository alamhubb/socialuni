package com.socialuni.social.sdk.dao.repository.dev;

import com.socialuni.social.sdk.dao.DO.dev.ThirdUserDO;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author qinkaiyuan
 * @since 1.0.0
 */
public interface ThirdUserRepository extends JpaRepository<ThirdUserDO, Long> {
    @Cacheable(cacheNames = "findThreeUserByDevIdAndUserId", key = "#devId+'-'+#userId")
    ThirdUserDO findByDevIdAndUserId(Integer devId, Integer userId);


    @Caching(
            put = {
                    @CachePut(cacheNames = "findThreeUserByDevIdAndUserId", key = "#thirdUser.devId+'-'+#thirdUser.userId")
            }
    )
    ThirdUserDO save(ThirdUserDO thirdUser);
}