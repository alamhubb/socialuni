package com.socialuni.demo.config;

import com.socialuni.social.constant.SocialFeignHeaderName;
import com.socialuni.social.entity.model.DO.user.SocialUserAccountDO;
import com.socialuni.social.sdk.utils.model.SocialUserAccountUtil;
import com.socialuni.social.web.sdk.utils.IpUtil;
import com.socialuni.social.web.sdk.utils.SocialTokenUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

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
//        String socialSecret = RequestUtil.getHeader(SocialFeignHeaderName.socialSecretKeyHeaderName);
//        requestTemplate.header(SocialFeignHeaderName.socialSecretKeyHeaderName, socialSecret);
        requestTemplate.header(SocialFeignHeaderName.socialSecretKeyHeaderName, socialDevSecretKey);
        String userIp = IpUtil.getIpAddr();
        requestTemplate.header(SocialFeignHeaderName.requestIpHeaderName, userIp);

        //如果有token，或者社交登录时，不携带token，手动设置token
        if (SocialTokenUtil.hasToken()) {
            //走了这里一定是绑定了社交联盟账号，获取社交联盟账户
            SocialUserAccountDO socialUserAccountDO = SocialUserAccountUtil.getMineSocialAccount();
            if (socialUserAccountDO != null) {
                String uniToken = socialUserAccountDO.getSessionKey();
                requestTemplate.header(SocialFeignHeaderName.socialTokenHeaderName, uniToken);
            } else {
                String socialuniToken = SocialTokenUtil.getSocialuniToken();
                if (socialuniToken != null) {
                    requestTemplate.header(SocialFeignHeaderName.socialTokenHeaderName, socialuniToken);
                }
            }
        } else {
            String socialuniToken = SocialTokenUtil.getSocialuniToken();
            if (socialuniToken != null) {
                requestTemplate.header(SocialFeignHeaderName.socialTokenHeaderName, socialuniToken);
            }
        }


//        requestTemplate.header("sign", md5);
        //一些接口的调用需要实现幂等，比如消息发送，如果使用requestId就可以方便服务方实现幂等
//        requestTemplate.header("requestId", UUID.randomUUID().toString().replaceAll("-", ""));
    }
}