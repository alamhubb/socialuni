package com.socialuni.social.common.sdk.utils;

import com.socialuni.social.common.sdk.dao.repository.log.UserLogRepository;
import com.socialuni.social.common.sdk.dao.DO.UserLogDO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserLogStoreUtils {

    private static UserLogRepository userLogRepository;

    @Resource
    public void setUserLogRepository(UserLogRepository userLogRepository) {
        UserLogStoreUtils.userLogRepository = userLogRepository;
    }

    public static void save(UserLogDO userLogDO) {
        userLogRepository.save(userLogDO);
    }
}

