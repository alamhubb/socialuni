package com.socialuni.social.tance.sdk.api;

import com.socialuni.social.tance.sdk.model.DevAccountModel;


/**
 * @author qinkaiyuan
 * @since 1.0.0
 */
public interface DevAccountRedisInterface {
    DevAccountModel saveDevAccount(DevAccountModel devAccount);
}