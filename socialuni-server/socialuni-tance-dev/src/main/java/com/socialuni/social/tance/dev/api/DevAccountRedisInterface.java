package com.socialuni.social.tance.dev.api;

import com.socialuni.social.tance.dev.model.DevAccountModel;


/**
 * @author qinkaiyuan
 * @since 1.0.0
 */
public interface DevAccountRedisInterface {
    DevAccountModel saveDevAccount(DevAccountModel devAccount);
}