package com.socialuni.social.tance.sdk.config;

import cn.hutool.extra.spring.SpringUtil;
import com.socialuni.social.tance.sdk.api.ConfigInterface;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
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
        ConfigInterface configApi = SpringUtil.getBean(ConfigInterface.class);
//        String devId = SocialuniSystemConst.getAppSocialuniId();
        Integer devId = DevAccountFacade.getDevIdNotNull();
        SocialuniAppConfigBO appConfig = new SocialuniAppConfigBO();
        appConfig.setAppGender(configApi.getString( "appGender"));
        //
        appConfig.setDisableUnderageContent(configApi.getBoolean( "disableUnderageContent"));
        appConfig.setDisableContentHasQrCode(configApi.getBoolean( "disableContentHasQrCode"));
        appConfig.setDisableContentHasContactInfo(configApi.getBoolean( "disableContentHasContactInfo"));
        appConfig.setMustSetSchoolCanPost(configApi.getBoolean( "mustSetSchoolCanPost"));
        //
        appConfig.setFollowTabName(configApi.getString( "followTabName"));
        appConfig.setHomeTabName(configApi.getString( "homeTabName"));
        appConfig.setCityTabName(configApi.getString( "cityTabName"));
        //
        appConfig.setTabNames(configApi.getListString( "tabNames"));

        Integer devUserId = DevAccountFacade.getDevUserId();

        String userUid = SocialuniUnionIdFacede.getUuidByUnionIdNotNull(devUserId);

        appConfig.setSystemUserId(userUid);
        return appConfig;
    }

    /**
     * @return
     */
    public static SocialuniAppMoreConfigBO getAppMoreConfig() {
        ConfigInterface configApi = SpringUtil.getBean(ConfigInterface.class);

        SocialuniAppMoreConfigBO socialuniAppMoreConfigBO = new SocialuniAppMoreConfigBO();
        socialuniAppMoreConfigBO.setServiceWeChat(configApi.getString( "serviceWeChat"));
        socialuniAppMoreConfigBO.setVipPrice(configApi.getInteger( "vipPrice"));
        socialuniAppMoreConfigBO.setSwiperHeight(configApi.getInteger( "swiperHeight"));
        socialuniAppMoreConfigBO.setHomeUrl(configApi.getString( "homeUrl"));
        socialuniAppMoreConfigBO.setSuggestUrl(configApi.getString( "suggestUrl"));
        socialuniAppMoreConfigBO.setContactUsUrl(configApi.getString( "contactUsUrl"));
        socialuniAppMoreConfigBO.setUserAgreementUrl(configApi.getString( "userAgreementUrl"));
        socialuniAppMoreConfigBO.setUserPrivacyUrl(configApi.getString( "userPrivacyUrl"));
        socialuniAppMoreConfigBO.setChildProtectUrl(configApi.getString( "childProtectUrl"));
        socialuniAppMoreConfigBO.setRewardedAdLimit(configApi.getInteger( "rewardedAdLimit"));
        socialuniAppMoreConfigBO.setContactExpenseShell(configApi.getInteger( "contactExpenseShell"));
        socialuniAppMoreConfigBO.setContactUserReceiveShell(configApi.getInteger( "contactUserReceiveShell"));
        socialuniAppMoreConfigBO.setErrorMsgContactService(configApi.getString( "errorMsgContactService"));
        socialuniAppMoreConfigBO.setErrorMsg601UnLogin(configApi.getString( "errorMsg601UnLogin"));
        socialuniAppMoreConfigBO.setErrorMsg604SystemError(configApi.getString( "errorMsg604SystemError"));
        socialuniAppMoreConfigBO.setAuthCodeInterval(configApi.getInteger( "authCodeInterval"));
        socialuniAppMoreConfigBO.setReportCountHide(configApi.getInteger( "reportCountHide"));
        socialuniAppMoreConfigBO.setShowSwipers(configApi.getBoolean( "showSwipers"));
        socialuniAppMoreConfigBO.setTalkShowAdInterval(configApi.getInteger( "talkShowAdInterval"));
        socialuniAppMoreConfigBO.setTalkShowAdCount(configApi.getInteger( "talkShowAdCount"));
        socialuniAppMoreConfigBO.setAuthCodeCount(configApi.getInteger( "authCodeCount"));
        socialuniAppMoreConfigBO.setAuthCodeIpCount(configApi.getInteger( "authCodeIpCount"));
        socialuniAppMoreConfigBO.setTalkShowAdIndexList(configApi.getListInteger( "talkShowAdIndexList"));
        socialuniAppMoreConfigBO.setAuthCodePhoneCount(configApi.getInteger( "authCodePhoneCount"));
        socialuniAppMoreConfigBO.setAuthCodeValidMinute(configApi.getInteger( "authCodeValidMinute"));
        socialuniAppMoreConfigBO.setHighLimitReportCount(configApi.getInteger( "highLimitReportCount"));
        return socialuniAppMoreConfigBO;
    }
}
