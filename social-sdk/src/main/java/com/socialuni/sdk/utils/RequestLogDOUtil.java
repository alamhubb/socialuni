package com.socialuni.sdk.utils;

import com.socialuni.sdk.model.OperateLogDO;
import com.socialuni.sdk.store.OperateLogStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
@Slf4j
public class RequestLogDOUtil {
    private static OperateLogStore operateLogStore;

    @Resource
    public void setOperateLogStore(OperateLogStore operateLogStore) {
        RequestLogDOUtil.operateLogStore = operateLogStore;
    }

    /*public static OperateLogDO save(OperateLogDO operateLogDO) {
        return operateLogStore.save(operateLogDO);
    }*/

    public static void saveAsync(OperateLogDO operateLogDO) {
        operateLogStore.saveAsync(operateLogDO);
    }
}


