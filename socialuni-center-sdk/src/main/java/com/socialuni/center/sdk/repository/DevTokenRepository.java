package com.socialuni.center.sdk.repository;

import com.socialuni.center.sdk.mode.DevTokenDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author qinkaiyuan
 * @date 2018-10-17 21:59
 */
public interface DevTokenRepository extends JpaRepository<DevTokenDO, Integer> {
    @Query(nativeQuery = true, value = "select t.token_code from dev_token t where t.dev_id =:devId order by id desc limit 1")
    String findFirstTokenCodeByUserId(@Param("devId") Integer devId);
}


