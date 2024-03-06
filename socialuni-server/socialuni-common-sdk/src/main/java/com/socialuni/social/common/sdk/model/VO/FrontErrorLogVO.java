package com.socialuni.social.common.sdk.model.VO;


import lombok.Data;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:48
 */
//记录前端错误日志，不该出现的错误
@Data
public class FrontErrorLogVO {
    //出错的url
    private String uri;
    private String frontPage;

    //出错的业务备注
    private String detail;

    //传入了什么参数导致报错
    private String params;

    //错误信息
    private String errorMsg;
    private String platform;
    private String provider;
    private String appVersion;
}
