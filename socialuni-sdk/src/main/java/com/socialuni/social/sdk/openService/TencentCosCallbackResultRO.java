package com.socialuni.social.sdk.openService;

import lombok.Data;

@Data
public class TencentCosCallbackResultRO {
    private Integer code;
    private String message;
    private TencentCosCallbackDataRO data;
}
