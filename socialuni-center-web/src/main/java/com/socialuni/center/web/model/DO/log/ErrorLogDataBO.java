package com.socialuni.center.web.model.DO.log;

import lombok.Data;


@Data
//错误日志
public class ErrorLogDataBO {
    Object data;

    public ErrorLogDataBO() {
    }

    public ErrorLogDataBO(Object data) {
        this.data = data;
    }
}