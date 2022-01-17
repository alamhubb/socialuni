package com.socialuni.social.sdk.repository.dev;

import com.socialuni.social.entity.model.DO.dev.DevTokenDO;
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

    DevTokenDO findFirstByTokenCode(String token);
}


