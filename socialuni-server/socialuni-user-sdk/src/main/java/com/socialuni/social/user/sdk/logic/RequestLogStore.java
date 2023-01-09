package com.socialuni.social.user.sdk.logic;

import com.socialuni.social.user.sdk.model.DO.RequestLogDO;
import com.socialuni.social.user.sdk.repository.RequestLogRepository;
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
