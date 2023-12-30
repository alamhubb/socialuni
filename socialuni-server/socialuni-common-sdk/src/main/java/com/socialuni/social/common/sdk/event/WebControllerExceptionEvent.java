package com.socialuni.social.common.sdk.event;

import lombok.*;
import org.springframework.context.ApplicationEvent;

/**
 * @author wulinghui
 * @version 1.0
 * @module api-factory
 * @date 2022/9/23 16:26
 * @since 1.0
 */
@Setter
@Getter
public class WebControllerExceptionEvent extends ApplicationEvent {
    String errorMsg;
    Integer errorCode;
    String errorType;
    String innerMsg;
    String innerMsgDetail;

    public WebControllerExceptionEvent() {
        super("");
    }
}
