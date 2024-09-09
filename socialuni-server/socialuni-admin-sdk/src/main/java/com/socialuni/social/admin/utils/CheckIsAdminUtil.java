package com.socialuni.social.admin.utils;

import com.socialuni.social.admin.facede.AdminDevAccountFacade;
import com.qingchi.qing.common.exception.base.exception.SocialParamsException;

public class CheckIsAdminUtil {
    public static void checkAdmin() {
        if (!AdminDevAccountFacade.isCenter()){
            throw new SocialParamsException("没有访问权限，请联系客服");
        }
    }
}
