package com.socialuni.sdk.platform.wangyi;

import lombok.Data;

import java.util.List;

@Data
public class WyTalkCheckAntispamVO {
    //本次请求数据标识，可以根据该标识查询数据最新结果
    private String taskId;
    //检测结果，0：通过，1：嫌疑，2：不通过
    private Integer action;
    //	审核模式，0：纯机审，1：机审+部分人审，2：机审+全量人审
    private Integer censorType;
    //策略版本号,策略更新时该参数会更新，可用于追溯策略调优效果
    private String strategyVersion;
    //语种代码数组，详见
    private List<String> lang;
    //分类信息
    private List<WyTalkCheckLabelVO> labels;
}
