package com.socialuni.sdk.constant.socialuni;

import java.util.Arrays;
import java.util.List;

public class UserIdentityAuthStatus extends CommonStatus {
    //正常，预审核，审核中，失败
    //第一次为预审核，默认展示为认证成功状态
    public static final String preAudit = StatusConst.preAudit;
    public static final String audit =  StatusConst.audit;
    public static final String fail =  StatusConst.fail;

    public static final List<String> auditList = Arrays.asList(preAudit, audit);
    public static final List<String> authSuccessList = Arrays.asList(preAudit, enable);
}

