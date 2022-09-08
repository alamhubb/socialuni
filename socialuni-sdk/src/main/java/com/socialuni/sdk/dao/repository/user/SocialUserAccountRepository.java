package com.socialuni.sdk.dao.repository.user;


import com.socialuni.sdk.model.DO.user.SocialUserAccountDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialUserAccountRepository extends JpaRepository<SocialUserAccountDO, Integer> {

    //查询账号是否注册过，某一渠道
    SocialUserAccountDO findByProviderAndUnionId(String provider, String unionId);
    SocialUserAccountDO findByProviderAndAppOpenId(String provider, String appOpenId);
    SocialUserAccountDO findByProviderAndMpOpenId(String provider, String mpOpenId);
    SocialUserAccountDO findByProviderAndH5OpenId(String provider, String h5OpenId);

    //根据渠道和用户id查询用户账户信息
    SocialUserAccountDO findByProviderAndUserId(String provider, Integer userId);


    SocialUserAccountDO findByUserId(Integer userId);
    SocialUserAccountDO findByUserIdOrderByUpdateTimeDesc(Integer userId);


//    @CachePut(cacheNames = RedisKeysConst.userById, key = "#account.userId")
//    UserAccountDO save(SocialUserAccountDO account);

 /*   Optional<SocialUserAccountDO> findFirstOneByQqMpOpenIdOrderByIdAsc(String qqMpOpenId);
    Optional<SocialUserAccountDO> findFirstOneByWxMpOpenIdOrderByIdAsc(String wxMpOpenId);

    Optional<SocialUserAccountDO> findFirstOneByQqUnionIdOrderByIdAsc(String unionId);

    Optional<SocialUserAccountDO> findFirstOneByWxUnionIdOrderByIdAsc(String unionId);

    Optional<SocialUserAccountDO> findOneByUserId(Integer userId);
*/
}

