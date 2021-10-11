package com.socialuni.center.web.utils;

import com.socialuni.center.web.model.DO.log.ErrorLogDO;
import com.socialuni.center.web.repository.log.ErrorLogRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ErrorLogUtils {
    private static ErrorLogRepository errorLogRepository;

    @Resource
    public void setErrorLogRepository(ErrorLogRepository errorLogRepository) {
        ErrorLogUtils.errorLogRepository = errorLogRepository;
    }


    public static void save(ErrorLogDO errorLogDO) {
        errorLogRepository.save(errorLogDO);
    }
}
