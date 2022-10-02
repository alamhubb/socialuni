package com.socialuni.social.tance.repository;

import cn.hutool.core.bean.BeanUtil;
import com.socialuni.social.tance.entity.DevAccountDo;
import com.socialuni.social.tance.entity.DevTokenDo;
import com.socialuni.social.tance.sdk.api.DevAccountApi;
import com.socialuni.social.tance.sdk.model.DevAccountModel;
import com.socialuni.social.tance.sdk.model.DevTokenModler;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


/**
 * @author qinkaiyuan
 * @since 1.0.0
 */
public interface DevAccountRepository extends JpaRepository<DevAccountDo, Integer>, DevAccountApi {
    @Override
    default DevAccountModel savePut(DevAccountModel devAccountModel){
        DevAccountDo devAccountDo = BeanUtil.copyProperties(devAccountModel, DevAccountDo.class);
        return this.save(devAccountDo);
    }
    @Cacheable(cacheNames = "getDevAccountById", key = "#id")
    DevAccountDo findOneById(Integer id);

    //直接携带秘钥访问
    @Cacheable(cacheNames = "getDevAccountBySecretKey", key = "#secretKey")
    DevAccountDo findOneBySecretKey(String secretKey);


    @Cacheable(cacheNames = "getDevAccountBySocialuniId", key = "#socialuniId")
    DevAccountDo findOneBySocialuniId(String socialuniId);

    DevAccountDo findOneByAppName(String appName);

    DevAccountDo findOneByDevNum(Long devNum);

    //获取最新的开发者账户，用来id相加，不缓存，低频，创建时才是用
    Optional<DevAccountDo> findFirstByOrderByIdDesc();

    //不需要缓存，低频, admin登录使用
    DevAccountDo findOneByPhoneNumOrderByIdAsc(String phoneNum);

    List<DevAccountDo> findAll();
}