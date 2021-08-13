package com.socialuni.admin.web.controller;

import com.socialuni.entity.model.DevAccountDO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/**
 * @author qinkaiyuan
 * @since 1.0.0
 */
public interface DevAccountRepository extends JpaRepository<DevAccountDO, Integer> {
    @Cacheable(cacheNames = "getDevAccountById", key = "#id")
    DevAccountDO findFirstById(Integer id);

    @Cacheable(cacheNames = "getDevAccountBySecretKey", key = "#secretKey")
    DevAccountDO findFirstBySecretKey(String secretKey);

    @Cacheable(cacheNames = "getDevAccountByDevNum", key = "#devNum")
    Optional<DevAccountDO> findFirstByDevNum(Long devNum);

    //获取最新的开发者账户，用来id相加
    Optional<DevAccountDO> findFirstByOrderByIdDesc();


    Optional<DevAccountDO> findFirstByPhoneNumOrderByIdAsc(String phoneNum);
}