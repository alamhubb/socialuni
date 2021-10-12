package com.socialuni.social.sdk.store;

import com.socialuni.social.entity.model.DO.JpaSqlLogDO;
import com.socialuni.social.entity.model.DO.RequestLogDO;
import com.socialuni.social.sdk.repository.JpaSqlLogRepository;
import com.socialuni.social.sdk.repository.RequestLogRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class JpaSqlLogStore {
    @Resource
    JpaSqlLogRepository jpaSqlLogRepository;

    @Async
    public void saveAsync(JpaSqlLogDO requestLogDO) {
        jpaSqlLogRepository.save(requestLogDO);
    }
}
