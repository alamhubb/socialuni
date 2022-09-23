package com.socialuni.social.sdk.utils;


import com.socialuni.social.sdk.constant.ErrorMsg;
import com.socialuni.social.sdk.dao.DO.user.SocialuniUserDO;

import java.text.MessageFormat;
import java.util.Date;

public class ErrorMsgUtil {
    public static String getErrorCode605ContactServiceValue(SocialuniUserDO userDO) {
//        UserDetailDO userDetailDO = SocialuniUserUtil.getUserDetail(userDO);
//        Date violationEndTime = userDetailDO.getViolationEndTime();
//        return ErrorMsgUtil.getErrorCode605ContactServiceValue(violationEndTime);
        return null;
    }

    public static String getErrorCode605ContactServiceValue(Date date) {
        Integer manyDay = DateUtils.HowManyDaysFromNow(date);
        return MessageFormat.format(ErrorMsg.errorCode605ContactServiceValue, manyDay);
    }
}
