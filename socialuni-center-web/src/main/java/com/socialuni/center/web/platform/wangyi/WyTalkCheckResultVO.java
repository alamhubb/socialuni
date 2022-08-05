package com.socialuni.center.web.platform.wangyi;

import lombok.Data;

@Data
public class WyTalkCheckResultVO {
    //antispam 数据结构
    private WyTalkCheckAntispamVO antispam;
    //情感分析结果
    private Object emotionAnalysis ;
}
