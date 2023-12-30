package com.socialuni.social.common.sdk.platform.wangyi;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class WyCommonResultVO {
    //接口调用状态，200:正常，其他值：调用出错，返回码见 响应返回码
    private Integer code;
    //结果说明，如果接口调用出错，那么返回错误描述，成功返回 ok
    private String msg;
    //接口返回结果，各个接口自定义，若为空标识返回正常
    private WyTalkCheckResultVO result;
}
