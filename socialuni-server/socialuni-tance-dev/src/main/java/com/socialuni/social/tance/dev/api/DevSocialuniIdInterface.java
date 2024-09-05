package com.socialuni.social.tance.dev.api;

import com.socialuni.social.tance.dev.model.DevSocialuniIdModel;


/**
 * @author qinkaiyuan
 * @since 1.0.0
 */
public interface DevSocialuniIdInterface {
    //直接携带秘钥访问
    DevSocialuniIdModel findOneBySocialuniId(String socialuniId);
}