package com.socialuni.center.web.repository.dev;

import com.socialuni.center.web.model.DO.dev.DevAccountDO;
import com.socialuni.center.web.model.DO.dev.DevSocialuniIdDO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


/**
 * @author qinkaiyuan
 * @since 1.0.0
 */
public interface DevSocialuniIdRepository extends JpaRepository<DevSocialuniIdDO, Integer> {
    //直接携带秘钥访问
//    @Cacheable(cacheNames = "getDevSocialuniIdBySocialuniId", key = "#socialuniId")
    DevSocialuniIdDO findOneBySocialuniId(String socialuniId);
}