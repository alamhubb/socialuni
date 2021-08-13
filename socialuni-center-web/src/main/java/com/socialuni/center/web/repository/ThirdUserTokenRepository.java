package com.socialuni.center.web.repository;

import com.socialuni.center.web.model.DO.ThirdUserTokenDO;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author qinkaiyuan
 * @date 2018-10-17 21:59
 */
public interface ThirdUserTokenRepository extends JpaRepository<ThirdUserTokenDO, Integer> {
    @Cacheable(cacheNames = "getTokenCodeByUserId", key = "#userId")
    @Query(nativeQuery = true, value = "select t.token from third_user_token t where t.user_id =:userId order by id desc limit 1")
    String findFirstOneTokenByUserId(Integer userId);


    @CachePut(cacheNames = "getTokenByToken", key = "#tokenDO.token")
    ThirdUserTokenDO save(ThirdUserTokenDO tokenDO);

    @Cacheable(cacheNames = "getTokenByToken", key = "#token")
    ThirdUserTokenDO findFirstByToken(String token);
}


