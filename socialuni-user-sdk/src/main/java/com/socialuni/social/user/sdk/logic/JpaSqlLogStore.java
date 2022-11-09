package com.socialuni.social.user.sdk.logic;

import com.socialuni.social.user.sdk.model.DO.JpaSqlLogDO;
import com.socialuni.social.user.sdk.repository.JpaSqlLogRepository;
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
