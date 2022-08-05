package com.socialuni.center.web.platform.wangyi;

import lombok.Data;

import java.util.List;


@Data
public class WyTalkCheckLabelVO {
    //本次请求数据标识，可以根据该标识查询数据最新结果
    private Integer label;
    //检测结果，0：通过，1：嫌疑，2：不通过
    private Integer level;
    //	其他信息
    private WyTalkCheckLabelDetailVO details;
    //细分类信息，可能包含多个，可能为空
    private List<WyTalkCheckSubLabelVO> subLabels;
}
