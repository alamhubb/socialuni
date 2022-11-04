package com.socialuni.social.sdk.utils;


import com.socialuni.social.common.api.constant.SocialSystemConst;
import com.socialuni.social.sdk.constant.ErrorMsg;
import com.socialuni.social.tance.sdk.facade.ConfigFacade;
import com.socialuni.social.user.sdk.entity.SocialuniUserDo;

import java.text.MessageFormat;
import java.util.Date;

public class ErrorMsgUtil {
    public static String getErrorCode605ContactServiceValue(SocialuniUserDo userDO) {
//        UserDetailDO userDetailDO = SocialuniUserUtil.getUserDetail(userDO);
//        Date violationEndTime = userDetailDO.getViolationEndTime();
//        return ErrorMsgUtil.getErrorCode605ContactServiceValue(violationEndTime);
        return null;
    }

    public static String getErrorCode605ContactServiceValue(Date date) {
        Integer manyDay = DateUtils.HowManyDaysFromNow(date);
        return MessageFormat.format(ErrorMsg.errorCode605ContactServiceValue, manyDay) + ConfigFacade.getFormat(ErrorMsg.CONTACT_SERVICE, SocialSystemConst.CONFIGS_KEY_QQ_ACCOUNT);
    }
}
