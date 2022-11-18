package com.socialuni.social.tance.repository;

import com.socialuni.social.tance.entity.DevSocialuniIdDo;
import com.socialuni.social.tance.sdk.api.DevSocialuniIdInterface;
import com.socialuni.social.tance.sdk.model.DevSocialuniIdModel;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author qinkaiyuan
 * @since 1.0.0
 */
public interface DevSocialuniIdRepository extends JpaRepository<DevSocialuniIdDo, Integer>, DevSocialuniIdInterface {
    //直接携带秘钥访问
    DevSocialuniIdModel findOneBySocialuniId(String socialuniId);
}