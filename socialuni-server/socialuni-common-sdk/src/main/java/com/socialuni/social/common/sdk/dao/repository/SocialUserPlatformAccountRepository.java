package com.socialuni.social.common.sdk.dao.repository;


import com.socialuni.social.common.sdk.dao.DO.SocialUserPlatformAccountDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SocialUserPlatformAccountRepository extends JpaRepository<SocialUserPlatformAccountDO, Integer> {

    //查询账号是否注册过，某一渠道
    SocialUserPlatformAccountDO findByProviderAndUnionId(String provider, String unionId);
    SocialUserPlatformAccountDO findByProviderAndAppOpenId(String provider, String appOpenId);
    SocialUserPlatformAccountDO findByProviderAndMpOpenId(String provider, String mpOpenId);
    SocialUserPlatformAccountDO findByProviderAndH5OpenId(String provider, String h5OpenId);

    //根据渠道和用户id查询用户账户信息
    SocialUserPlatformAccountDO findByProviderAndUserId(String provider, Long userId);

    SocialUserPlatformAccountDO findFirstByUserIdOrderByUpdateTimeDesc(Long userId);

    @Query("select s.userId from SocialUserPlatformAccountDO s where s.provider = :provider")
    List<Integer> findAllUserIdsAllByProvider(String provider);


//    @CachePut(cacheNames = RedisKeysConst.userById, key = "#account.userId")
//    UserAccountDO save(SocialUserAccountDO account);

 /*   Optional<SocialUserAccountDO> findFirstOneByQqMpOpenIdOrderByIdAsc(String qqMpOpenId);
    Optional<SocialUserAccountDO> findFirstOneByWxMpOpenIdOrderByIdAsc(String wxMpOpenId);

    Optional<SocialUserAccountDO> findFirstOneByQqUnionIdOrderByIdAsc(String unionId);

    Optional<SocialUserAccountDO> findFirstOneByWxUnionIdOrderByIdAsc(String unionId);

    Optional<SocialUserAccountDO> findOneByUserId(Long userId);
*/
}

