package com.socialuni.social.user.sdk.dao.repository;


import com.qingchi.qing.constant.CommonRedisKey;
import com.socialuni.social.user.sdk.dao.DO.SocialuniTokenDO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialuniCommonTokenRepository extends JpaRepository<SocialuniTokenDO, Integer> {
    @Cacheable(cacheNames = CommonRedisKey.tokenByToken, key = "#token")
    SocialuniTokenDO findOneByToken(String token);

    @Caching(
            evict = {
                    @CacheEvict(cacheNames = CommonRedisKey.tokenByToken, key = "#tokenDO.token")
            },
            put = {
                    @CachePut(cacheNames = CommonRedisKey.tokenByToken, key = "#tokenDO.token")
            }
    )
    SocialuniTokenDO save(SocialuniTokenDO tokenDO);


//    @CachePut(cacheNames = RedisKeysConst.userById, key = "#account.userId")
//    UserAccountDO save(SocialUserAccountDO account);

 /*   Optional<SocialUserAccountDO> findFirstOneByQqMpOpenIdOrderByIdAsc(String qqMpOpenId);
    Optional<SocialUserAccountDO> findFirstOneByWxMpOpenIdOrderByIdAsc(String wxMpOpenId);

    Optional<SocialUserAccountDO> findFirstOneByQqUnionIdOrderByIdAsc(String unionId);

    Optional<SocialUserAccountDO> findFirstOneByWxUnionIdOrderByIdAsc(String unionId);

    Optional<SocialUserAccountDO> findOneByUserId(Long userId);
*/
}

