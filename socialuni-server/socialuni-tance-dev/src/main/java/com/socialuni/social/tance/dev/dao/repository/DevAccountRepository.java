package com.socialuni.social.tance.dev.dao.repository;

import com.socialuni.social.tance.dev.api.DevAccountInterface;
import com.socialuni.social.tance.dev.dao.DO.DevAccountDo;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


/**
 * @author qinkaiyuan
 * @since 1.0.0
 */
public interface DevAccountRepository extends JpaRepository<DevAccountDo, Integer>, DevAccountInterface {
    DevAccountDo save(DevAccountDo devAccountDo);
    @Cacheable(cacheNames = "getDevAccountById", key = "#id")
    DevAccountDo findFirstById(Integer id);

    @Cacheable(cacheNames = "getDevAccountByUserId", key = "#userId")
    DevAccountDo findFirstByUserId(Long userId);

    //直接携带秘钥访问
    @Cacheable(cacheNames = "getDevAccountBySecretKey", key = "#secretKey")
    DevAccountDo findOneBySecretKey(String secretKey);


    @Cacheable(cacheNames = "getDevAccountBySocialuniId", key = "#socialuniId")
    DevAccountDo findOneBySocialuniId(String socialuniId);

    DevAccountDo findOneByAppName(String appName);

    DevAccountDo findOneByDevNum(Long devNum);

    //获取最新的开发者账户，用来id相加，不缓存，低频，创建时才是用
    Optional<DevAccountDo> findFirstByOrderByIdDesc();

    @Cacheable(cacheNames = "findOneByPhoneNumOrderByIdAsc", key = "#phoneNum")
    //不需要缓存，低频, admin登录使用
    DevAccountDo findOneByPhoneNumOrderByIdAsc(String phoneNum);

    List<DevAccountDo> findAll();
}