package com.socialuni.social.sdk.config;

import cn.hutool.extra.spring.SpringUtil;
import com.socialuni.social.tance.sdk.api.ConfigApi;
import com.socialuni.social.tance.sdk.enumeration.GenderType;
import com.socialuni.social.tance.sdk.enumeration.SocialuniSystemConst;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SocialuniAppConfig {
//    首页的tab栏目
//    @Deprecated
//    static SocialuniAppConfigBO appConfig = SocialuniAppType.getDefaultTypeAppConfig();
//    @Deprecated
//    static SocialuniAppMoreConfigBO appMoreConfig = SocialuniAppType.getDefaultAppMoreConfig();

    /**
     * 通过默认的开发者key获得配置
     * @return
     */
    public static SocialuniAppConfigBO getAppConfig() {
        ConfigApi configApi = SpringUtil.getBean(ConfigApi.class);
        String appSocialuniId = SocialuniSystemConst.getAppSocialuniId();
        SocialuniAppConfigBO appConfig = new SocialuniAppConfigBO();
        appConfig.setAppGender(configApi.getString(appSocialuniId,"appGender"));
        //
        appConfig.setDisableUnderageContent(configApi.getBoolean(appSocialuniId,"disableUnderageContent"));
        appConfig.setDisableContentHasQrCode(configApi.getBoolean(appSocialuniId,"disableContentHasQrCode"));
        appConfig.setDisableContentHasContactInfo(configApi.getBoolean(appSocialuniId,"disableContentHasContactInfo"));
        appConfig.setMustSetSchoolCanPost(configApi.getBoolean(appSocialuniId,"mustSetSchoolCanPost"));
        //
        appConfig.setFollowTabName(configApi.getString(appSocialuniId,"followTabName"));
        appConfig.setHomeTabName(configApi.getString(appSocialuniId,"homeTabName"));
        appConfig.setCityTabName(configApi.getString(appSocialuniId,"cityTabName"));
        //
        appConfig.setTabNames(configApi.getListString(appSocialuniId,"tabNames"));
        return appConfig;
    }
    /**
     *
     * @return
     */
    public static SocialuniAppMoreConfigBO getAppMoreConfig() {
        ConfigApi configApi = SpringUtil.getBean(ConfigApi.class);
        String appSocialuniId = SocialuniSystemConst.getAppSocialuniId();
        SocialuniAppMoreConfigBO socialuniAppMoreConfigBO = new SocialuniAppMoreConfigBO();
        socialuniAppMoreConfigBO.setServiceWeChat(configApi.getString(appSocialuniId,"serviceWeChat"));
        socialuniAppMoreConfigBO.setVipPrice(configApi.getInteger(appSocialuniId,"vipPrice"));
        socialuniAppMoreConfigBO.setSwiperHeight(configApi.getInteger(appSocialuniId,"swiperHeight"));
        socialuniAppMoreConfigBO.setHomeUrl(configApi.getString(appSocialuniId,"homeUrl"));
        socialuniAppMoreConfigBO.setSuggestUrl(configApi.getString(appSocialuniId,"suggestUrl"));
        socialuniAppMoreConfigBO.setContactUsUrl(configApi.getString(appSocialuniId,"contactUsUrl"));
        socialuniAppMoreConfigBO.setUserAgreementUrl(configApi.getString(appSocialuniId,"userAgreementUrl"));
        socialuniAppMoreConfigBO.setUserPrivacyUrl(configApi.getString(appSocialuniId,"userPrivacyUrl"));
        socialuniAppMoreConfigBO.setChildProtectUrl(configApi.getString(appSocialuniId,"childProtectUrl"));
        socialuniAppMoreConfigBO.setRewardedAdLimit(configApi.getInteger(appSocialuniId,"rewardedAdLimit"));
        socialuniAppMoreConfigBO.setContactExpenseShell(configApi.getInteger(appSocialuniId,"contactExpenseShell"));
        socialuniAppMoreConfigBO.setContactUserReceiveShell(configApi.getInteger(appSocialuniId,"contactUserReceiveShell"));
        socialuniAppMoreConfigBO.setErrorMsgContactService(configApi.getString(appSocialuniId,"errorMsgContactService"));
        socialuniAppMoreConfigBO.setErrorMsg601UnLogin(configApi.getString(appSocialuniId,"errorMsg601UnLogin"));
        socialuniAppMoreConfigBO.setErrorMsg604SystemError(configApi.getString(appSocialuniId,"errorMsg604SystemError"));
        socialuniAppMoreConfigBO.setAuthCodeInterval(configApi.getInteger(appSocialuniId,"authCodeInterval"));
        socialuniAppMoreConfigBO.setReportCountHide(configApi.getInteger(appSocialuniId,"reportCountHide"));
        socialuniAppMoreConfigBO.setShowSwipers(configApi.getBoolean(appSocialuniId,"showSwipers"));
        socialuniAppMoreConfigBO.setTalkShowAdInterval(configApi.getInteger(appSocialuniId,"talkShowAdInterval"));
        socialuniAppMoreConfigBO.setTalkShowAdCount(configApi.getInteger(appSocialuniId,"talkShowAdCount"));
        socialuniAppMoreConfigBO.setTalkShowAdIndexList(configApi.getListInteger(appSocialuniId,"talkShowAdIndexList"));
        socialuniAppMoreConfigBO.setAuthCodeCount(configApi.getInteger(appSocialuniId,"authCodeCount"));
        socialuniAppMoreConfigBO.setAuthCodeIpCount(configApi.getInteger(appSocialuniId,"authCodeIpCount"));
        socialuniAppMoreConfigBO.setAuthCodePhoneCount(configApi.getInteger(appSocialuniId,"authCodePhoneCount"));
        socialuniAppMoreConfigBO.setAuthCodeValidMinute(configApi.getInteger(appSocialuniId,"authCodeValidMinute"));
        socialuniAppMoreConfigBO.setHighLimitReportCount(configApi.getInteger(appSocialuniId,"highLimitReportCount"));
        return  socialuniAppMoreConfigBO;
    }
}
