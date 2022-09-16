package com.socialuni.admin.web.utils;

import com.socialuni.sdk.utils.DevAccountUtils;
import com.socialuni.social.web.sdk.exception.SocialBusinessException;
import com.socialuni.social.web.sdk.exception.SocialParamsException;

public class CheckIsAdminUtil {
    public static void checkAdmin() {
        if (!DevAccountUtils.isAdmin()){
            throw new SocialParamsException("没有访问权限，请联系客服");
        }
    }
}