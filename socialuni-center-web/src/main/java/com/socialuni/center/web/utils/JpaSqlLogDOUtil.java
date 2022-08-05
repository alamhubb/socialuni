package com.socialuni.center.web.utils;

import com.socialuni.center.web.store.JpaSqlLogStore;
import com.socialuni.center.web.model.DO.JpaSqlLogDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
@Slf4j
public class JpaSqlLogDOUtil {
    private static JpaSqlLogStore jpaSqlLogStore;

    @Resource
    public void setJpaSqlLogStore(JpaSqlLogStore jpaSqlLogStore) {
        JpaSqlLogDOUtil.jpaSqlLogStore = jpaSqlLogStore;
    }


    /*public static RequestLogDO save(RequestLogDO operateLogDO) {
        return operateLogStore.save(operateLogDO);
    }*/

    public static void saveAsync(JpaSqlLogDO requestLogDO) {
        jpaSqlLogStore.saveAsync(requestLogDO);
    }
}


