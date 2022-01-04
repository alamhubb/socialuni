package com.socialuni.social.web.sdk.store;

import com.socialuni.social.web.sdk.model.ErrorRequestLogDO;
import com.socialuni.social.web.sdk.repository.ErrorRequestLogRepository;
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
}
