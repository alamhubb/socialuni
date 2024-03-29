package com.socialuni.social.common.sdk.dao.store;

import com.socialuni.social.common.sdk.dao.DO.JpaSqlLogDO;
import com.socialuni.social.common.sdk.dao.repository.JpaSqlLogRepository;
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
