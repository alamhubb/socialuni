package com.socialuni.sdk.dao.repository.dev;

import com.socialuni.sdk.model.DO.dev.DevSocialuniIdDO;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author qinkaiyuan
 * @since 1.0.0
 */
public interface DevSocialuniIdRepository extends JpaRepository<DevSocialuniIdDO, Integer> {
    //直接携带秘钥访问
//    @Cacheable(cacheNames = "getDevSocialuniIdBySocialuniId", key = "#socialuniId")
    DevSocialuniIdDO findOneBySocialuniId(String socialuniId);
}