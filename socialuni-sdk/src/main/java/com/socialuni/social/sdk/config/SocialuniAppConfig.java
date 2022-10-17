package com.socialuni.social.sdk.config;

import cn.hutool.extra.spring.SpringUtil;
import com.socialuni.social.tance.sdk.api.ConfigApi;
import com.socialuni.social.tance.sdk.enumeration.SocialuniSystemConst;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import org.springframework.stereotype.Component;

@Component
public class SocialuniAppConfig {
//    首页的tab栏目
//    @Deprecated
//    static SocialuniAppConfigBO appConfig = SocialuniAppType.getDefaultTypeAppConfig();
//    @Deprecated
//    static SocialuniAppMoreConfigBO appMoreConfig = SocialuniAppType.getDefaultAppMoreConfig();
    /**
     * 通过默认的开发者key获得配置
     *
     * @return
     */
    public static SocialuniAppConfigBO getAppConfig() {
        ConfigApi configApi = SpringUtil.getBean(ConfigApi.class);
//        String devId = SocialuniSystemConst.getAppSocialuniId();
        Integer devId = DevAccountFacade.getDevIdNotNull();
        SocialuniAppConfigBO appConfig = new SocialuniAppConfigBO();
        appConfig.setAppGender(configApi.getString(devId, "appGender"));
        //
        appConfig.setDisableUnderageContent(configApi.getBoolean(devId, "disableUnderageContent"));
        appConfig.setDisableContentHasQrCode(configApi.getBoolean(devId, "disableContentHasQrCode"));
        appConfig.setDisableContentHasContactInfo(configApi.getBoolean(devId, "disableContentHasContactInfo"));
        appConfig.setMustSetSchoolCanPost(configApi.getBoolean(devId, "mustSetSchoolCanPost"));
        //
        appConfig.setFollowTabName(configApi.getString(devId, "followTabName"));
        appConfig.setHomeTabName(configApi.getString(devId, "homeTabName"));
        appConfig.setCityTabName(configApi.getString(devId, "cityTabName"));
        //
        appConfig.setTabNames(configApi.getListString(devId, "tabNames"));
        return appConfig;
    }

    /**
     * @return
     */
    public static SocialuniAppMoreConfigBO getAppMoreConfig() {
        ConfigApi configApi = SpringUtil.getBean(ConfigApi.class);
        Integer devId = DevAccountFacade.getDevIdNotNull();
        SocialuniAppMoreConfigBO socialuniAppMoreConfigBO = new SocialuniAppMoreConfigBO();
        socialuniAppMoreConfigBO.setServiceWeChat(configApi.getString(devId, "serviceWeChat"));
        socialuniAppMoreConfigBO.setVipPrice(configApi.getInteger(devId, "vipPrice"));
        socialuniAppMoreConfigBO.setSwiperHeight(configApi.getInteger(devId, "swiperHeight"));
        socialuniAppMoreConfigBO.setHomeUrl(configApi.getString(devId, "homeUrl"));
        socialuniAppMoreConfigBO.setSuggestUrl(configApi.getString(devId, "suggestUrl"));
        socialuniAppMoreConfigBO.setContactUsUrl(configApi.getString(devId, "contactUsUrl"));
        socialuniAppMoreConfigBO.setUserAgreementUrl(configApi.getString(devId, "userAgreementUrl"));
        socialuniAppMoreConfigBO.setUserPrivacyUrl(configApi.getString(devId, "userPrivacyUrl"));
        socialuniAppMoreConfigBO.setChildProtectUrl(configApi.getString(devId, "childProtectUrl"));
        socialuniAppMoreConfigBO.setRewardedAdLimit(configApi.getInteger(devId, "rewardedAdLimit"));
        socialuniAppMoreConfigBO.setContactExpenseShell(configApi.getInteger(devId, "contactExpenseShell"));
        socialuniAppMoreConfigBO.setContactUserReceiveShell(configApi.getInteger(devId, "contactUserReceiveShell"));
        socialuniAppMoreConfigBO.setErrorMsgContactService(configApi.getString(devId, "errorMsgContactService"));
        socialuniAppMoreConfigBO.setErrorMsg601UnLogin(configApi.getString(devId, "errorMsg601UnLogin"));
        socialuniAppMoreConfigBO.setErrorMsg604SystemError(configApi.getString(devId, "errorMsg604SystemError"));
        socialuniAppMoreConfigBO.setAuthCodeInterval(configApi.getInteger(devId, "authCodeInterval"));
        socialuniAppMoreConfigBO.setReportCountHide(configApi.getInteger(devId, "reportCountHide"));
        socialuniAppMoreConfigBO.setShowSwipers(configApi.getBoolean(devId, "showSwipers"));
        socialuniAppMoreConfigBO.setTalkShowAdInterval(configApi.getInteger(devId, "talkShowAdInterval"));
        socialuniAppMoreConfigBO.setTalkShowAdCount(configApi.getInteger(devId, "talkShowAdCount"));
        socialuniAppMoreConfigBO.setAuthCodeCount(configApi.getInteger(devId, "authCodeCount"));
        socialuniAppMoreConfigBO.setAuthCodeIpCount(configApi.getInteger(devId, "authCodeIpCount"));
        socialuniAppMoreConfigBO.setAuthCodePhoneCount(configApi.getInteger(devId, "authCodePhoneCount"));
        socialuniAppMoreConfigBO.setAuthCodeValidMinute(configApi.getInteger(devId, "authCodeValidMinute"));
        socialuniAppMoreConfigBO.setHighLimitReportCount(configApi.getInteger(devId, "highLimitReportCount"));
        return socialuniAppMoreConfigBO;
    }
}
