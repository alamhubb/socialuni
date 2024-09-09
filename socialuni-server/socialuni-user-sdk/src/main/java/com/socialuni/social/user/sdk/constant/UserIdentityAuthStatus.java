package com.socialuni.social.user.sdk.constant;

import com.qingchi.qing.constant.SocialuniCommonStatus;
import com.qingchi.qing.constant.ConstStatus;

import java.util.Arrays;
import java.util.List;

public class UserIdentityAuthStatus extends SocialuniCommonStatus {
    //正常，预审核，审核中，失败
    //第一次为预审核，默认展示为认证成功状态
    public static final String preAudit = ConstStatus.preAudit;
    public static final String audit =  ConstStatus.auditing;
    public static final String fail =  ConstStatus.fail;

    public static final List<String> auditList = Arrays.asList(preAudit, audit);
    public static final List<String> authSuccessList = Arrays.asList(preAudit, enable);
}

