package com.socialuni.social.api.model.model;

import com.socialuni.social.api.model.constant.CommonErrorCode;
import com.socialuni.social.api.model.constant.CommonErrorMsg;
import lombok.Data;

/**
 * @author qinkaiyuan
 * @date 2019-02-14 22:10
 */
@Data
public class ResultRO<T> {
    //0成功，1，系统异常，2业务错误，3自定义错误
    private Integer errorCode = CommonErrorCode.success;
    private Boolean success = true;
    private String errorMsg = CommonErrorMsg.successMsg;
    private T data;

    public static <T> ResultRO<T> success() {
        return new ResultRO<>();
    }

    public static <T> ResultRO<T> success(T data) {
        return new ResultRO<>(data);
    }

    public ResultRO() {
    }

    public ResultRO(T data) {
        this.data = data;
    }

    public ResultRO(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.success = false;
    }


    public ResultRO(Integer errorCode, String errorMsg, T data) {
        this(errorCode, errorMsg);
        this.data = data;
    }

    public ResultRO(ResultRO<?> resultRO) {
        this(resultRO.getErrorCode(), resultRO.getErrorMsg());
    }

    public Boolean hasError() {
        return !this.success;
    }
}
