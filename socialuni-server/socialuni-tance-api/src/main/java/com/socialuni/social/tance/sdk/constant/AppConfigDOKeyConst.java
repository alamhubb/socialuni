package com.socialuni.social.tance.sdk.constant;


import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class AppConfigDOKeyConst {
    public static final String appGender = "appGender";
//    public static final String systemUserPhoneNum = "systemUserPhoneNum";

    public static final String followTabName = "followTabName";
    public static final String homeTabName = "homeTabName";
    public static final String cityTabName = "cityTabName";
    public static final String tabNames = "tabNames";
    public static final String defaultChatGroups = "defaultChatGroups";

    public static final String disableUnderageContent = "disableUnderageContent";
    public static final String disableContentHasQrCode = "disableContentHasQrCode";
    public static final String disableContentHasContactInfo = "disableContentHasContactInfo";
    public static final String mustSetSchoolCanPost = "mustSetSchoolCanPost";

    public static final String serviceWeChat = "serviceWeChat";
    public static final String vipPrice = "vipPrice";
    public static final String swiperHeight = "swiperHeight";
    public static final String homeUrl = "homeUrl";
    public static final String suggestUrl = "suggestUrl";
    public static final String contactUsUrl = "contactUsUrl";
    public static final String userAgreementUrl = "userAgreementUrl";
    public static final String userPrivacyUrl = "userPrivacyUrl";
    public static final String childProtectUrl = "childProtectUrl";
    public static final String rewardedAdLimit = "rewardedAdLimit";
    public static final String contactExpenseShell = "contactExpenseShell";
    public static final String contactUserReceiveShell = "contactUserReceiveShell";
    //系统抽成比例
    public static final String sysServiceReceiveRatio = "sysServiceReceiveRatio";
    public static final String errorMsgContactService = "errorMsgContactService";
    public static final String errorMsg601UnLogin = "errorMsg601UnLogin";
    public static final String errorMsg604SystemError = "errorMsg604SystemError";
    public static final String authCodeInterval = "authCodeInterval";
    public static final String reportCountHide = "reportCountHide";
    public static final String showSwipers = "showSwipers";
    public static final String talkShowAdInterval = "talkShowAdInterval";
    public static final String talkShowAdCount = "talkShowAdCount";
    public static final String authCodeCount = "authCodeCount";
    public static final String authCodeIpCount = "authCodeIpCount";
    public static final String talkShowAdIndexList = "talkShowAdIndexList";
    public static final String authCodePhoneCount = "authCodePhoneCount";
    public static final String authCodeValidMinute = "authCodeValidMinute";
    public static final String highLimitReportCount = "highLimitReportCount";

    public static final String qq_account = "qq_account";
    public static final String wx_mp_id = "wx_mp_id";
    public static final String wx_app_id = "wx_app_id";
    public static final String wx_mp_secret = "wx_mp_secret";
    public static final String wx_merchant_id = "wx_merchant_id";
    public static final String wx_merchant_key = "wx_merchant_key";
    public static final String devPublishDataApiUrl = "devPublishDataApiUrl";
    public static final String mp_wx_auditing = "mp_wx_auditing";
    public static final String mp_qq_auditing = "mp_qq_auditing";

    public static final List<String> configKeys = Arrays.asList(
            appGender,
//            systemUserPhoneNum,
            followTabName,
            homeTabName,
            cityTabName,
            tabNames,
            defaultChatGroups,
            disableUnderageContent,
            disableContentHasQrCode,
            disableContentHasContactInfo,
            mustSetSchoolCanPost,
            serviceWeChat,
            vipPrice,
            swiperHeight,
            homeUrl,
            suggestUrl,
            contactUsUrl,
            userAgreementUrl,
            userPrivacyUrl,
            childProtectUrl,
            rewardedAdLimit,
            contactExpenseShell,
            contactUserReceiveShell,
            sysServiceReceiveRatio,
            errorMsgContactService,
            errorMsg601UnLogin,
            errorMsg604SystemError,
            authCodeInterval,
            reportCountHide,
            showSwipers,
            talkShowAdInterval,
            talkShowAdCount,
            authCodeCount,
            authCodeIpCount,
            talkShowAdIndexList,
            authCodePhoneCount,
            authCodeValidMinute,
            highLimitReportCount,
            qq_account,
            wx_mp_id,
            wx_app_id,
            wx_mp_secret,
            wx_merchant_id,
            wx_merchant_key,
            mp_wx_auditing,
            mp_qq_auditing,
            devPublishDataApiUrl
    );
}
