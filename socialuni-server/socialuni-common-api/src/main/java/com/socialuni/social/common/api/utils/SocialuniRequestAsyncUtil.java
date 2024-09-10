package com.socialuni.social.common.api.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;

@Slf4j
public class SocialuniRequestAsyncUtil {
    public static void runAsync(Runnable runnable) {
//        RequestContextHolder.setRequestAttributes(RequestContextHolder.getRequestAttributes(), true);
        HttpServletRequest request = RequestUtil.getRequest();
        // 开启异步处理
        AsyncContext asyncContext = request.startAsync();
        // 异步处理逻辑
        asyncContext.start(() -> {
            RequestStoreUtil.setRequest(request);
            try {
                runnable.run();
            } catch (Exception e) {
                e.printStackTrace();
                log.info(e.getMessage());
            } finally {
                // 任务完成后，调用 complete() 来结束异步处理
                asyncContext.complete();
            }
        });
    }
}
