package com.socialuni.admin.web.repository;

import com.socialuni.admin.web.model.DO.DevTokenDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author qinkaiyuan
 * @date 2018-10-17 21:59
 */
public interface DevTokenRepository extends JpaRepository<DevTokenDO, Integer> {
    @Query(nativeQuery = true, value = "select t.token_code from dev_token t where t.user_id =:userId order by id desc limit 1")
    String findFirstTokenCodeByUserId(Integer userId);
}


