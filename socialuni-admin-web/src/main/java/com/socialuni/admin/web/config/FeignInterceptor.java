package com.socialuni.admin.web.config;

import com.socialuni.api.config.SocialFeignHeaderName;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;
import com.socialuni.social.sdk.web.utils.RequestUtil;

@Slf4j
@Component
@ConditionalOnClass(RequestInterceptor.class)
public class FeignInterceptor implements RequestInterceptor {
    @Value("${socialuni.secret-key}")
    private String socialDevSecretKey;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        //对服务调用进行签名，当前签名方法比较简单，以后可以支持更加复杂的签名计算（比如读取参数内容，组合后再进行签名计算）
//        requestTemplate.header("appId", authProperties.getAppId());
        String socialSecret = RequestUtil.getHeader(SocialFeignHeaderName.socialSecretKeyHeaderName);
        requestTemplate.header(SocialFeignHeaderName.socialSecretKeyHeaderName, socialDevSecretKey);
//        requestTemplate.header("sign", md5);
        //一些接口的调用需要实现幂等，比如消息发送，如果使用requestId就可以方便服务方实现幂等
//        requestTemplate.header("requestId", UUID.randomUUID().toString().replaceAll("-", ""));
    }
}