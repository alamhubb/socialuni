package com.socialuni.social.tance.sdk.config;

import com.socialuni.social.common.api.model.SocialuniAppConfigBO;
import com.socialuni.social.common.api.model.SocialuniAppMoreConfigBO;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import com.socialuni.social.tance.sdk.service.SocialuniAppConfigInfoInterface;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Component
public class SocialuniAppConfig {
    private static final String followTabName = "关注";
    private static final String homeTabName = "首页";
    private static final String cityTabName = "同城";
    private static final String selfSchoolTabName = "本校";
    //    private String systemUserId = "关注";
    public static final List<String> innerDefaultTabNames = Arrays.asList(
            SocialuniAppConfig.followTabName,
            SocialuniAppConfig.homeTabName,
            SocialuniAppConfig.cityTabName,
            SocialuniAppConfig.selfSchoolTabName
    );

    public static SocialuniAppConfigInfoInterface socialuniAppConfigInfoInterface;

    @Resource
    public void setSocialuniAppConfigInfoInterface(SocialuniAppConfigInfoInterface socialuniAppConfigInfoInterface) {
        SocialuniAppConfig.socialuniAppConfigInfoInterface = socialuniAppConfigInfoInterface;
    }

    /**
     * 通过默认的开发者key获得配置
     *
     * @return
     */
    public static SocialuniAppConfigBO getAppConfig() {
        Integer devId = DevAccountFacade.getDevIdNotNull();
        SocialuniAppConfigBO appConfig = socialuniAppConfigInfoInterface.getAppConfig(devId);
        return appConfig;
    }

    /**
     * @return
     */
    public static SocialuniAppMoreConfigBO getAppMoreConfig() {
        Integer devId = DevAccountFacade.getDevIdNotNull();
        SocialuniAppMoreConfigBO appConfig = socialuniAppConfigInfoInterface.getAppMoreConfig(devId);
        return appConfig;
//        ConfigInterface configApi = SpringUtil.getBean(ConfigInterface.class);
//
//        SocialuniAppMoreConfigBO socialuniAppMoreConfigBO = new SocialuniAppMoreConfigBO();
//        socialuniAppMoreConfigBO.setServiceWeChat(configApi.getString("serviceWeChat"));
//        socialuniAppMoreConfigBO.setVipPrice(configApi.getInteger("vipPrice"));
//        socialuniAppMoreConfigBO.setSwiperHeight(configApi.getInteger("swiperHeight"));
//        socialuniAppMoreConfigBO.setHomeUrl(configApi.getString("homeUrl"));
//        socialuniAppMoreConfigBO.setSuggestUrl(configApi.getString("suggestUrl"));
//        socialuniAppMoreConfigBO.setContactUsUrl(configApi.getString("contactUsUrl"));
//        socialuniAppMoreConfigBO.setUserAgreementUrl(configApi.getString("userAgreementUrl"));
//        socialuniAppMoreConfigBO.setUserPrivacyUrl(configApi.getString("userPrivacyUrl"));
//        socialuniAppMoreConfigBO.setChildProtectUrl(configApi.getString("childProtectUrl"));
//        socialuniAppMoreConfigBO.setRewardedAdLimit(configApi.getInteger("rewardedAdLimit"));
//        socialuniAppMoreConfigBO.setContactExpenseShell(configApi.getInteger("contactExpenseShell"));
//        socialuniAppMoreConfigBO.setContactUserReceiveShell(configApi.getInteger("contactUserReceiveShell"));
//        socialuniAppMoreConfigBO.setErrorMsgContactService(configApi.getString("errorMsgContactService"));
//        socialuniAppMoreConfigBO.setErrorMsg601UnLogin(configApi.getString("errorMsg601UnLogin"));
//        socialuniAppMoreConfigBO.setErrorMsg604SystemError(configApi.getString("errorMsg604SystemError"));
//        socialuniAppMoreConfigBO.setAuthCodeInterval(configApi.getInteger("authCodeInterval"));
//        socialuniAppMoreConfigBO.setReportCountHide(configApi.getInteger("reportCountHide"));
//        socialuniAppMoreConfigBO.setShowSwipers(configApi.getBoolean("showSwipers"));
//        socialuniAppMoreConfigBO.setTalkShowAdInterval(configApi.getInteger("talkShowAdInterval"));
//        socialuniAppMoreConfigBO.setTalkShowAdCount(configApi.getInteger("talkShowAdCount"));
//        socialuniAppMoreConfigBO.setAuthCodeCount(configApi.getInteger("authCodeCount"));
//        socialuniAppMoreConfigBO.setAuthCodeIpCount(configApi.getInteger("authCodeIpCount"));
//        socialuniAppMoreConfigBO.setTalkShowAdIndexList(configApi.getListInteger("talkShowAdIndexList"));
//        socialuniAppMoreConfigBO.setAuthCodePhoneCount(configApi.getInteger("authCodePhoneCount"));
//        socialuniAppMoreConfigBO.setAuthCodeValidMinute(configApi.getInteger("authCodeValidMinute"));
//        socialuniAppMoreConfigBO.setHighLimitReportCount(configApi.getInteger("highLimitReportCount"));
//        socialuniAppMoreConfigBO.setMp_wx_auditing(configApi.getBoolean(AppConfigDOKeyConst.mp_wx_auditing));
//        socialuniAppMoreConfigBO.setMp_qq_auditing(configApi.getBoolean(AppConfigDOKeyConst.mp_qq_auditing));
//        return socialuniAppMoreConfigBO;
    }
}
