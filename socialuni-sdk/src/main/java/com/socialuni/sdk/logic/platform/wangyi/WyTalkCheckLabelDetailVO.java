package com.socialuni.sdk.logic.platform.wangyi;

import lombok.Data;

import java.util.List;

@Data
public class WyTalkCheckLabelDetailVO {
    //线索信息，用于定位文本中有问题的部分，辅助人工审核
    private List<String> hint;
    //线索分类信息，返回10:表示命中用户自定义添加用户名单，返回11:表示命中用户自定义添加ip名单，
    // 返回30:表示命中用户自定义添加敏感词,返回140：标识命中反作弊（hitReason为反作弊命中原因，
    // 1：数据异常 2：行为模型 3：设备模型 4：业务类型 5：校验异常 6：模拟器 7：越狱或root 8：浏览器异常
    // 9：ip异常 10：易盾黑名单 11：自定义黑名单 12：自定义白名单）
    private List<Object>  hitInfos;
}
