package com.socialuni.admin.web.constant;

import com.socialuni.social.sdk.constant.ViolateType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 10:06
 */
public class AdminAuditResultType extends ViolateType {

    //前台不展示，后台管理不展示，仅代码使用
    public final static String otherIllegalLinkage = "其他违规关联违规";
    public final static String noViolation = "没有违规";
    public final static String slightViolation = "轻微违规";
    public final static String adult = "成年";
    public final static String underage = "未成年";

    public final static List<String> underageShowReportTypes = Arrays.asList(underage, adult);
    public final static List<String> adminFrontHasUnderageShowReportTypes = Arrays.asList(underage, adult, pornInfo, sexy, slightViolation, unfriendly, badAd, lawbreaking, other, noViolation);

    //轻微违规
    public final static List<String> slightViolationList = Collections.singletonList(slightViolation);
    //一般违规
    public final static List<String> generalViolationList = Arrays.asList(underage, sexy, unfriendly, other);
    //严重
    public final static List<String> severelyViolationList = Arrays.asList(pornInfo, badAd, userInfo, lawbreaking, swindle);

}
