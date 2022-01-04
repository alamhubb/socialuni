package com.socialuni.social.sdk.store;

import com.socialuni.social.web.sdk.model.RequestLogDO;
import com.socialuni.social.sdk.repository.RequestLogRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RequestLogStore {
    @Resource
    RequestLogRepository requestLogRepository;

    @Async
    public void saveAsync(RequestLogDO requestLogDO) {
        requestLogRepository.save(requestLogDO);
    }

    public RequestLogDO save(RequestLogDO requestLogDO) {
        return requestLogRepository.save(requestLogDO);
    }
}
