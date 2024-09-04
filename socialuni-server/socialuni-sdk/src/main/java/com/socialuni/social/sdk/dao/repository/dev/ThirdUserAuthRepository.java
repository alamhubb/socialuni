package com.socialuni.social.sdk.dao.repository.dev;


import com.socialuni.social.sdk.dao.DO.dev.ThirdUserAuthDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author qinkaiyuan
 * @since 1.0.0
 */
public interface ThirdUserAuthRepository extends JpaRepository<ThirdUserAuthDO, Long> {
    ThirdUserAuthDO findByDevIdAndUserIdAndAuthTypeAndStatus(Integer devId, Long userId, String authType, String status);
}