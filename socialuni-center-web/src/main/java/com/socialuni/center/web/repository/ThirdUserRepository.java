package com.socialuni.center.web.repository;

import com.socialuni.center.web.model.DO.ThirdUserDO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author qinkaiyuan
 * @since 1.0.0
 */
public interface ThirdUserRepository extends JpaRepository<ThirdUserDO, Long> {

    @Cacheable(cacheNames = "findThreeUserByDevIdAndUserId")
    ThirdUserDO findByDevIdAndUserId(Integer devId, Integer userId);
}