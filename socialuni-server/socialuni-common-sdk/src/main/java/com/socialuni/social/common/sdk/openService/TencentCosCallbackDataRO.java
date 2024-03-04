package com.socialuni.social.common.sdk.openService;

import lombok.Data;

@Data
public class TencentCosCallbackDataRO {
    //唯一ID标识。
    private String trace_id;
    //若您设置了自动冻结，该字段表示视频的冻结状态。0：未冻结，1：已被冻结。
    private Integer forbidden_status;
    //触发的事件,此处固定为 ReviewImage
    private String event;
    //审核场景为涉黄的审核结果信息
    private TencentCosCallbackDataPornInfo porn_info;
    //审核场景为广告引导的审核结果信息
    private TencentCosCallbackDataPornInfo ads_info;
    //该字段表示本次判定的审核结果，您可以根据该结果，进行后续的操作；建议您按照业务所需，对不同的审核结果进行相应处理。
    // 有效值：0（审核正常），1 （判定为违规敏感文件），2（疑似敏感，建议人工复核）。
    private Integer result;
    //被审核图片的完整 URL 链接
    private String url;
}
