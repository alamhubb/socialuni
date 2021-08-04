package com.socialuni.sdk.store;

import com.socialuni.sdk.model.OperateLogDO;
import com.socialuni.sdk.repository.OperateLogRepository;
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
