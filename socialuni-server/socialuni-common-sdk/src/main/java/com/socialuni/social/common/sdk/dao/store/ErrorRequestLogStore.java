package com.socialuni.social.common.sdk.dao.store;

import com.socialuni.social.common.api.dao.DO.ErrorRequestLogDO;
import com.socialuni.social.common.sdk.dao.repository.ErrorRequestLogRepository;
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
