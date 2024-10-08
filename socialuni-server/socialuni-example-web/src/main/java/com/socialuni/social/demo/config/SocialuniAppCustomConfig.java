package com.socialuni.social.demo.config;

import com.socialuni.social.tance.dev.config.SocialuniAppConfig;
import com.socialuni.social.common.api.model.SocialuniAppConfigBO;
import com.socialuni.social.common.api.model.SocialuniAppMoreConfigBO;
import org.springframework.stereotype.Component;

@Component
public class SocialuniAppCustomConfig extends SocialuniAppConfig {

    //首页的tab栏目
    public static SocialuniAppConfigBO appConfig;
    public static SocialuniAppMoreConfigBO appMoreConfig = new SocialuniAppMoreConfigBO();
    //系统提供一些默认配置，然后再将用户的自定义配置和系统的配置合并

    //可以通过这里给appConfig自定义，可参考SocialuniAppType类的方式
    static {
        SocialuniAppConfigBO socialuniAppConfigBO = new SocialuniAppConfigBO();
        //禁止发布未成年内容
        socialuniAppConfigBO.setDisableUnderageContent(true);
        //禁止发布包含二维码的内容
        socialuniAppConfigBO.setDisableContentHasQrCode(false);
        //禁止发布包含联系方式的内容
        socialuniAppConfigBO.setDisableContentHasContactInfo(false);

        SocialuniAppCustomConfig.appConfig = socialuniAppConfigBO;
    }
}
