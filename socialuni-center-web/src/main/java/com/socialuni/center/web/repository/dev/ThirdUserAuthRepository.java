package com.socialuni.center.web.repository.dev;


import com.socialuni.center.web.model.DO.dev.ThirdUserAuthDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author qinkaiyuan
 * @since 1.0.0
 */
public interface ThirdUserAuthRepository extends JpaRepository<ThirdUserAuthDO, Long> {
    ThirdUserAuthDO findByDevIdAndUserIdAndAuthTypeAndStatus(Integer devId, Integer userId, String authType, String status);
}