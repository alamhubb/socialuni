package com.socialuni.social.admin.utils;

import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;

public class CheckIsAdminUtil {
    public static void checkAdmin() {
        if (!DevAccountFacade.isCenter()){
            throw new SocialParamsException("没有访问权限，请联系客服");
        }
    }
}
