package com.socialuni.social.uni.sdk.repository;

import cn.hutool.core.bean.BeanUtil;
import com.socialuni.social.tance.sdk.api.DevAccountInterface;
import com.socialuni.social.tance.sdk.model.DevAccountModel;
import com.socialuni.social.uni.sdk.model.DevAccountDo;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


/**
 * @author qinkaiyuan
 * @since 1.0.0
 */
public interface DevAccountRepository extends JpaRepository<DevAccountDo, Integer> , DevAccountInterface {

    default DevAccountModel savePut(DevAccountModel devAccountModel){
        DevAccountDo devAccountDo = BeanUtil.copyProperties(devAccountModel, DevAccountDo.class);
        return this.save(devAccountDo);
    }
    @Cacheable(cacheNames = "getDevAccountById", key = "#id")
    DevAccountModel findOneById(Integer id);

    //直接携带秘钥访问
    @Cacheable(cacheNames = "getDevAccountBySecretKey", key = "#secretKey")
    DevAccountModel findOneBySecretKey(String secretKey);


    @Cacheable(cacheNames = "getDevAccountBySocialuniId", key = "#socialuniId")
    DevAccountModel findOneBySocialuniId(String socialuniId);

    DevAccountModel findOneByAppName(String appName);

    DevAccountModel findOneByDevNum(Long devNum);

    //获取最新的开发者账户，用来id相加，不缓存，低频，创建时才是用
    Optional<DevAccountModel> findFirstByOrderByIdDesc();

    //不需要缓存，低频, admin登录使用
    DevAccountModel findOneByPhoneNumOrderByIdAsc(String phoneNum);

    List<DevAccountDo> findAll();
}