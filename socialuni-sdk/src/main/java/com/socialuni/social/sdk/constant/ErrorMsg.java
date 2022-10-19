package com.socialuni.social.sdk.constant;

public class ErrorMsg {
    //内容违规错误信息
    public static final String User_not_null = "用户数据不能为空";
    public static final String CONTENT_VIOLATION = "内容违规";
    public static final String report_success_result = "举报成功";
    public static final String report_fail_result = "举报失败";
    public static final String report_success_remark = "奖励" + AppConfigConst.auditSuccessValue + "分正义值，" + ErrorMsg.service;
    public static final String report_fail_remark = "扣除" + AppConfigConst.reportErrorValue + "分正义值，" + ErrorMsg.service;

    public static final String CHECK_VIOLATION_ERR_MSG = "包含涉污或违规内容，禁止发布，请修改后重试";
    public static final String CHECK_IMG_VIOLATION_ERR_MSG = "图片违规，禁止发布，请更换后重试";

    public static final String USER_VIOLATION_MSG = "账号被封禁，原因：";
    //不可修改，涉及到微信通知只能20个字符
    public static final String serviceName = "清池app客服";
    public static final String service = "有疑问请联系客服";
    public static final String illegalWordMsg = "为营造干净的交友环境，禁止发布包含疑似违规的相关内容，您发布的内容中包含疑似违规的敏感词集合：<{0}>，请求改后重试，" + ErrorMsg.service;;
    public static final String service_qq_account = "客服qq：491369310";
    public static final String CONTACT_SERVICE = "有疑问请联系" + ErrorMsg.service_qq_account;

    public static final String errorCode605ContactServiceValue = "账号被封禁，{0}天内解封，" + CONTACT_SERVICE;
    public static final String errorCode604SystemKey = "604系统异常提示";
//    public static final String errorCode604SystemMsg = "系统出错，请联系客服微信或qq:491369310";
    public static final String successMsg = "请求成功";
    public static final String failedMsg = "请求失败";



    public static final String reportSubmit = "感谢您的举报，举报正确审核后将奖励" + AppConfigConst.auditSuccessValue + "点正义值，" +
            "举报错误将扣除" + AppConfigConst.reportErrorValue + "点正义值，让我们一起为打造绿色交友平台努力";

    public static final String reportSubmitHide = "内容进入审核，暂时隐藏，" + reportSubmit;

    public static final String userMaybeViolation = "用户疑似存在违规行为，暂时被限制相关操作，请耐性等待审核结束" + CONTACT_SERVICE;
    public static final String bindPhoneNumCan = "请绑定手机号后再进行操作";
}
