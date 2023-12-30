package com.socialuni.social.common.sdk.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.ApplicationEvent;

/**
 * @author wulinghui
 * @version 1.0
 * @module api-factory
 * @date 2022/9/23 16:26
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WebControllerExceptionEvent extends ApplicationEvent {
    String errorMsg; Integer errorCode; String errorType; String innerMsg; String innerMsgDetail;
    public WebControllerExceptionEvent() {
        super("");
    }
}
