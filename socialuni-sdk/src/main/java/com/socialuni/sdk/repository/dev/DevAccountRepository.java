package com.socialuni.sdk.repository.dev;

import com.socialuni.sdk.model.DO.dev.DevAccountDO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


/**
 * @author qinkaiyuan
 * @since 1.0.0
 */
public interface DevAccountRepository extends JpaRepository<DevAccountDO, Integer> {
    @Cacheable(cacheNames = "getDevAccountById", key = "#id")
    DevAccountDO findOneById(Integer id);

    //直接携带秘钥访问
    @Cacheable(cacheNames = "getDevAccountBySecretKey", key = "#secretKey")
    DevAccountDO findOneBySecretKey(String secretKey);


    @Cacheable(cacheNames = "getDevAccountBySocialuniId", key = "#socialuniId")
    DevAccountDO findOneBySocialuniId(String socialuniId);

    DevAccountDO findOneByAppName(String appName);

    DevAccountDO findOneByDevNum(Long devNum);

    //获取最新的开发者账户，用来id相加，不缓存，低频，创建时才是用
    Optional<DevAccountDO> findFirstByOrderByIdDesc();

    //不需要缓存，低频, admin登录使用
    DevAccountDO findOneByPhoneNumOrderByIdAsc(String phoneNum);

    List<DevAccountDO> findAll();
}