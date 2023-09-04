package com.socialuni.social.admin.utils;

import com.socialuni.social.admin.facede.AdminDevAccountFacade;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;

public class CheckIsAdminUtil {
    public static void checkAdmin() {
        if (!AdminDevAccountFacade.isCenter()){
            throw new SocialParamsException("没有访问权限，请联系客服");
        }
    }
}
