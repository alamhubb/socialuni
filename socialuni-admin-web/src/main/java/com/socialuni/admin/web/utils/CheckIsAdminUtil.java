package com.socialuni.admin.web.utils;

import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import com.socialuni.social.common.exception.exception.SocialParamsException;

public class CheckIsAdminUtil {
    public static void checkAdmin() {
        if (!DevAccountFacade.isCenter()){
            throw new SocialParamsException("没有访问权限，请联系客服");
        }
    }
}
