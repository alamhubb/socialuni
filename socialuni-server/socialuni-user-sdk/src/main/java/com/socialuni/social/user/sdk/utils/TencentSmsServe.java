package com.socialuni.social.user.sdk.utils;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.socialuni.social.common.sdk.utils.StringUtil;
import com.socialuni.social.tance.sdk.enumeration.SocialuniSystemConst;
import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TencentSmsServe {
    private static int appId;
    private static String appKey;
    private static int templateId;
    private static String smsSign;

    @Value("${socialuni.cloud.tencent.sms.app-id}")
    public void setAppId(int appId) {
        TencentSmsServe.appId = appId;
    }

    @Value("${socialuni.cloud.tencent.sms.app-key}")
    public void setAppKey(String appKey) {
        TencentSmsServe.appKey = appKey;
    }

    @Value("${socialuni.cloud.tencent.sms.template-id}")
    public void setTemplateId(int templateId) {
        TencentSmsServe.templateId = templateId;
    }

    @Value("${socialuni.cloud.tencent.sms.sms-sign}")
    public void setSmsSign(String smsSign) {
        TencentSmsServe.smsSign = smsSign;
    }

    public static String sendAuthCode(String phoneNum) {
        String authCode = "1111";
        if (phoneNum.equals("11111111111") || phoneNum.equals("12222222222")) {
            log.info("非生产环境不发送验证码，开发环境验证码为：{}", authCode);
        } else if (SocialuniSystemConst.getIsProdEnv() && StringUtil.isNotEmpty(appKey)) {
            authCode = AuthCodeUtil.getAuthCode();
        } else {
            log.info("非生产环境不发送验证码，开发环境验证码为：{}", authCode);
        }
        return TencentSmsServe.sendAuthCode(phoneNum, authCode, "30");
    }

    public static String sendAuthCode(String phoneNum, String sendAuthCode) {
        String authCode = AuthCodeUtil.getAuthCode();
        return TencentSmsServe.sendAuthCode(phoneNum, authCode, "30");
    }

    public static String sendAuthCode(String phoneNum, String authCode, String authCodeValidMinute) {
        SmsSingleSender ssender = new SmsSingleSender(appId, appKey);
        //多少分钟内有效
        String[] params = {authCode, authCodeValidMinute};
        // 签名
        // 签名参数未提供或者为空时，会使用默认签名发送短信
        try {
            //生产环境才发送验证码
            if (SocialuniSystemConst.getIsProdEnv() && StringUtil.isNotEmpty(appKey)) {
                SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNum, templateId, params, smsSign, "", "");
                if (result == null || result.result != 0) {
                    throw new SocialBusinessException("验证码发送失败，请稍候重试");
                }
            }
        } catch (Exception e) {
            throw new SocialBusinessException("验证码发送失败，请稍候重试");
        }
        return authCode;
    }
}
