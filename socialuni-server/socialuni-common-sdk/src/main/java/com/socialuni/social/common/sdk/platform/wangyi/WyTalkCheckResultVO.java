package com.socialuni.social.common.sdk.platform.wangyi;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class WyTalkCheckResultVO {
    //antispam 数据结构
    private WyTalkCheckAntispamVO antispam;
    //情感分析结果
    private Object emotionAnalysis ;
}
