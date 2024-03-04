package com.socialuni.social.common.sdk.dao.repository.dev;


import com.socialuni.social.common.sdk.dao.DO.dev.ThirdUserAuthDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author qinkaiyuan
 * @since 1.0.0
 */
public interface ThirdUserAuthRepository extends JpaRepository<ThirdUserAuthDO, Long> {
    ThirdUserAuthDO findByDevIdAndUserIdAndAuthTypeAndStatus(Integer devId, Integer userId, String authType, String status);
}