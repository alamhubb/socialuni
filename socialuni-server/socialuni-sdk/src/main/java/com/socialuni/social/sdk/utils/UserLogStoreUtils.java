package com.socialuni.social.sdk.utils;

import com.socialuni.social.user.sdk.dao.DO.UserLogDO;
import com.socialuni.social.user.sdk.repository.UserLogRepository;
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

