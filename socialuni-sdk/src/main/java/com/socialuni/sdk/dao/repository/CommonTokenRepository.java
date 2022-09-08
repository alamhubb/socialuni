package com.socialuni.sdk.dao.repository;


import com.socialuni.sdk.dao.redis.redisKey.RedisKeysConst;
import com.socialuni.sdk.model.DO.user.SocialTokenDO;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommonTokenRepository extends JpaRepository<SocialTokenDO, Integer> {
    @Cacheable(cacheNames = RedisKeysConst.tokenByToken, key = "#token")
    SocialTokenDO findOneByToken(String token);

    @CachePut(cacheNames = RedisKeysConst.tokenByToken, key = "#tokenDO.token")
    SocialTokenDO save(SocialTokenDO tokenDO);


//    @CachePut(cacheNames = RedisKeysConst.userById, key = "#account.userId")
//    UserAccountDO save(SocialUserAccountDO account);

 /*   Optional<SocialUserAccountDO> findFirstOneByQqMpOpenIdOrderByIdAsc(String qqMpOpenId);
    Optional<SocialUserAccountDO> findFirstOneByWxMpOpenIdOrderByIdAsc(String wxMpOpenId);

    Optional<SocialUserAccountDO> findFirstOneByQqUnionIdOrderByIdAsc(String unionId);

    Optional<SocialUserAccountDO> findFirstOneByWxUnionIdOrderByIdAsc(String unionId);

    Optional<SocialUserAccountDO> findOneByUserId(Integer userId);
*/
}

