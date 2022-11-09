package com.socialuni.social.user.sdk.utils;

import com.socialuni.social.user.sdk.logic.JpaSqlLogStore;
import com.socialuni.social.user.sdk.model.DO.JpaSqlLogDO;
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


