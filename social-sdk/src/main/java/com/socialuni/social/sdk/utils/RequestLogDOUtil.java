package com.socialuni.social.sdk.utils;

import com.socialuni.social.web.sdk.model.RequestLogDO;
import com.socialuni.social.sdk.store.RequestLogStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
@Slf4j
public class RequestLogDOUtil {
    private static RequestLogStore requestLogStore;

    @Resource
    public void setOperateLogStore(RequestLogStore requestLogStore) {
        RequestLogDOUtil.requestLogStore = requestLogStore;
    }

    /*public static RequestLogDO save(RequestLogDO operateLogDO) {
        return operateLogStore.save(operateLogDO);
    }*/

    public static void saveAsync(RequestLogDO requestLogDO) {
        RequestLogUtil.remove();
        requestLogStore.saveAsync(requestLogDO);
    }
}


