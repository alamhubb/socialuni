package com.socialuni.social.tance.sdk.api;

import com.socialuni.social.tance.sdk.model.DevSocialuniIdModel;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author qinkaiyuan
 * @since 1.0.0
 */
public interface DevSocialuniIdApi {
    //直接携带秘钥访问
    DevSocialuniIdModel findOneBySocialuniId(String socialuniId);
}