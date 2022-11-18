package com.socialuni.social.user.sdk.repository;


import com.socialuni.social.common.api.constant.CommonRedisKey;
import com.socialuni.social.user.sdk.model.DO.SocialTokenDO;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialuniCommonTokenRepository extends JpaRepository<SocialTokenDO, Integer> {
    @Cacheable(cacheNames = CommonRedisKey.tokenByToken, key = "#token")
    SocialTokenDO findOneByToken(String token);

    @CachePut(cacheNames = CommonRedisKey.tokenByToken, key = "#tokenDO.token")
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
