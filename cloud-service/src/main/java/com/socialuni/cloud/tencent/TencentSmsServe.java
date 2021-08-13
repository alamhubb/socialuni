package com.socialuni.cloud.tencent;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.socialuni.social.exception.SocialBusinessException;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
public class TencentSmsServe {
    @Value("${socialuni.cloud.tencent.sms.app-id}")
    private static int appId;
    @Value("${socialuni.cloud.tencent.sms.app-key}")
    private static String appKey;
    @Value("${socialuni.cloud.tencent.sms.template-id}")
    private static int templateId;
    @Value("${socialuni.cloud.tencent.sms.sms-sign}")
    private static String smsSign;

    public static void sendAuthCode(String phoneNum, String authCode) {
        SmsSingleSender ssender = new SmsSingleSender(appId, appKey);
        //多少分钟内有效
        String[] params = {authCode, "30"};
        // 签名
        // 签名参数未提供或者为空时，会使用默认签名发送短信
        SmsSingleSenderResult result = null;
        try {
            log.info("发送验证码authCode:{}", authCode);
            result = ssender.sendWithParam("86", phoneNum, templateId, params, smsSign, "", "");
        } catch (Exception e) {
            throw new SocialBusinessException("验证码发送失败，请稍候重试，");
        }

        *if (result != null && result.result == 0) {
            authenticationDO.setStatus(StatusConst.success);
            authRepository.save(authenticationDO);
            return new ResultRO<>();
        } else {
            authenticationDO.setStatus(StatusConst.fail);
            authRepository.save(authenticationDO);
            throw new SocialBusinessException("验证码发送失败，请稍候重试，" + ErrorMsg.CONTACT_SERVICE);
        }
    }
}
