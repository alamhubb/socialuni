package com.socialuni.sdk.openService;

import lombok.Data;

@Data
public class TencentCosCallbackDataPornInfo {
    //是否命中该审核分类，0表示未命中，1表示命中，2表示疑似。
    private Integer hit_flag;
    //该字段表示审核结果命中审核信息的置信度，取值范围：0（置信度最低）-100（置信度最高 ），
    // 越高代表该内容越有可能属于当前返回审核信息。通常[0,60]分可以认为图片属于正常，
    // [61,90]分表示图片疑似敏感，[91,100]分表示图片为敏感的。
    //例如：色情 99，则表明该内容非常有可能属于色情内容。
    private Integer score;
    //命中的标签名称。
    private String label;
}
