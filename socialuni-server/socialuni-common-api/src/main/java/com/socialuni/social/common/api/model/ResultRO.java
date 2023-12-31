package com.socialuni.social.common.api.model;

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
    private Integer code = ResultRO.successCode;
    private Integer errorCode = ResultRO.successCode;
    private Boolean success = true;
    private String errorMsg = ResultRO.successMsg;
    private String msg = ResultRO.successMsg;
    private T data;

    public static <T> ResultRO<T> success() {
        return new ResultRO<>();
    }

    public static <T> ResultRO<T> failed(Integer errorCode, String errorMsg) {
        return new ResultRO<>(errorCode, errorMsg);
    }

    public static <T> ResultRO<T> success(T data) {
        return new ResultRO<>(data);
    }

    public static <T> ResultRO<T> success(T data, String msg) {
        return new ResultRO<>(data, msg);
    }

    public ResultRO() {
    }

    public ResultRO(T data) {
        this.data = data;
    }

    public ResultRO(T data, String msg) {
        this(data);
        this.msg = msg;
    }


    public ResultRO(Integer errorCode, String errorMsg) {
        this.code = errorCode;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.msg = errorMsg;
        this.success = false;
    }


    public ResultRO(Integer errorCode, String errorMsg, T data) {
        this(errorCode, errorMsg);
        this.data = data;
    }

    public ResultRO(ResultRO<?> resultRO) {
        this(resultRO.getCode(), resultRO.getErrorMsg());
    }

    public Boolean hasError() {
        return !this.success;
    }
}
