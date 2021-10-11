package com.socialuni.cloud.tencent;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.socialuni.cloud.config.SocialAppEnv;
import com.socialuni.social.exception.SocialBusinessException;
import com.socialuni.social.utils.AuthCodeUtil;
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
            log.info("发送验证码authCode:{}", authCode);
            //生产环境才发送验证码
            if (SocialAppEnv.getIsProdEnv()){
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
