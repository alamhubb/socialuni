package com.socialuni.social.common.sdk.dao.store;

import com.socialuni.social.common.api.dao.DO.RequestLogDO;
import com.socialuni.social.common.sdk.dao.repository.RequestLogRepository;
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
