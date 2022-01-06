package com.socialuni.social.api.model;

import lombok.Data;

/**
 * @author qinkaiyuan
 * @date 2019-02-14 22:10
 */
@Data
public class ResultRO<T> {
    //请求成功
    public static final Integer successCode = 0;
    public static final String successMsg = "请求成功";

    //0成功，1，系统异常，2业务错误，3自定义错误
    private Integer errorCode = ResultRO.successCode;
    private Boolean success = true;
    private String errorMsg = ResultRO.successMsg;
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
