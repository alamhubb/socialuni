package com.socialuni.social.user.sdk.logic;

import com.socialuni.social.user.sdk.model.DO.ErrorRequestLogDO;
import com.socialuni.social.user.sdk.repository.ErrorRequestLogRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ErrorRequestLogStore {
    @Resource
    ErrorRequestLogRepository errorRequestLogRepository;

    @Async
    public void saveAsync(ErrorRequestLogDO requestLogDO) {
        errorRequestLogRepository.save(requestLogDO);
    }

    public ErrorRequestLogDO save(ErrorRequestLogDO requestLogDO) {
        return errorRequestLogRepository.save(requestLogDO);
    }
}
