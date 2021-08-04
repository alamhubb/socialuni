package com.socialuni.sdk.exception;

import com.socialuni.sdk.constant.ErrorCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author qinkaiyuan
 * @date 2019-02-14 22:10
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ResultException extends RuntimeException {
    private final Integer errorCode = ErrorCode.PARAMS_ERROR;
    private String errorMsg;

    public ResultException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }
}
