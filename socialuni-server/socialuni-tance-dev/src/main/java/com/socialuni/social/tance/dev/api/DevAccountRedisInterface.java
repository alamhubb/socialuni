package com.socialuni.social.tance.dev.api;

import com.socialuni.social.tance.dev.dao.DO.DevAccountDo;


/**
 * @author qinkaiyuan
 * @since 1.0.0
 */
public interface DevAccountRedisInterface {
    DevAccountDo saveDevAccount(DevAccountDo devAccount);
}