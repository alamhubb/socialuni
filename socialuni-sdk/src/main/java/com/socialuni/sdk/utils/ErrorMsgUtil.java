package com.socialuni.sdk.utils;


import com.socialuni.sdk.constant.ErrorMsg;
import com.socialuni.sdk.model.DO.user.SocialUserDO;

import java.text.MessageFormat;
import java.util.Date;

public class ErrorMsgUtil {
    public static String getErrorCode605ContactServiceValue(SocialUserDO userDO) {
//        UserDetailDO userDetailDO = CenterUserUtil.getUserDetail(userDO);
//        Date violationEndTime = userDetailDO.getViolationEndTime();
//        return ErrorMsgUtil.getErrorCode605ContactServiceValue(violationEndTime);
        return null;
    }

    public static String getErrorCode605ContactServiceValue(Date date) {
        Integer manyDay = DateUtils.HowManyDaysFromNow(date);
        return MessageFormat.format(ErrorMsg.errorCode605ContactServiceValue, manyDay);
    }
}