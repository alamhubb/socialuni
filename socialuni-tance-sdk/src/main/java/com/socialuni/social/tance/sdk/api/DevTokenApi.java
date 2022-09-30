package com.socialuni.social.tance.sdk.api;

import com.socialuni.social.tance.sdk.model.DevTokenModler;

/**
 * @author qinkaiyuan
 * @date 2018-10-17 21:59
 */
public interface DevTokenApi {
    String findFirstTokenCodeByUserId(Integer devId);

    DevTokenModler findFirstByTokenCode(String token);

    DevTokenModler save(DevTokenModler devAccountModel);
}


