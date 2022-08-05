package com.socialuni.center.web.store;

import com.socialuni.social.entity.model.DO.JpaSqlLogDO;
import com.socialuni.center.web.repository.JpaSqlLogRepository;
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
