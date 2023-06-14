package com.socialuni.social.user.sdk.utils;


import com.socialuni.social.common.api.exception.exception.SocialNullUserException;
import com.socialuni.social.common.sdk.utils.DateUtils;
import com.socialuni.social.common.sdk.constant.ErrorMsg;
import com.socialuni.social.tance.sdk.config.SocialuniAppConfig;
import com.socialuni.social.tance.sdk.facade.ConfigFacade;
import com.socialuni.social.common.sdk.dao.facede.SocialuniUserRepositoryFacede;
import com.socialuni.social.user.sdk.model.DO.SocialUserViolationDo;

import java.text.MessageFormat;
import java.util.Date;

public class ErrorMsgUtil {
    public static String getErrorCode605ContactServiceValue() {
        Integer mineUserId = SocialuniUserUtil.getMineUserIdAllowNull();
        if (mineUserId == null) {
            throw new SocialNullUserException();
        }
        SocialUserViolationDo socialUserViolationDo = SocialuniUserRepositoryFacede.findByUserId(mineUserId, SocialUserViolationDo.class);
        Date violationEndTime = socialUserViolationDo.getViolationEndTime();
        return ErrorMsgUtil.getErrorCode605ContactServiceValue(violationEndTime);
    }

    public static String getErrorCode605ContactServiceValue(Date date) {
        Integer manyDay = DateUtils.HowManyDaysFromNow(date);
        return MessageFormat.format(ErrorMsg.errorCode605ContactServiceValue, manyDay) + ConfigFacade.getFormat(ErrorMsg.CONTACT_SERVICE, SocialuniAppConfig.getAppMoreConfig().getQq_account());
    }
}
