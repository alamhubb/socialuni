package com.socialuni.center.web.utils;


import com.socialuni.social.sdk.constant.ErrorMsg;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.sdk.utils.common.DateUtils;

import java.text.MessageFormat;
import java.util.Date;

public class ErrorMsgUtil {
    public static String getErrorCode605ContactServiceValue(UserDO userDO) {
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
