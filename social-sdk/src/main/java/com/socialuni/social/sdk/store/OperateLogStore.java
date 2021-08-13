package com.socialuni.social.sdk.store;

import com.socialuni.social.sdk.model.OperateLogDO;
import com.socialuni.social.sdk.repository.OperateLogRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class OperateLogStore {
    @Resource
    OperateLogRepository operateLogRepository;

    @Async
    public void saveAsync(OperateLogDO operateLogDO) {
        operateLogRepository.save(operateLogDO);
    }

    public OperateLogDO save(OperateLogDO operateLogDO) {
        return operateLogRepository.save(operateLogDO);
    }
}
