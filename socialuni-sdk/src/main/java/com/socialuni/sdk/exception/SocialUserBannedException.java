package com.socialuni.sdk.exception;

import com.socialuni.sdk.utils.ErrorMsgUtil;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.social.exception.constant.ErrorCode;
import com.socialuni.social.exception.base.SocialInfoException;

public class SocialUserBannedException extends SocialInfoException {
    //用户被封禁
    public SocialUserBannedException(SocialUserDO userDO) {
        super(ErrorMsgUtil.getErrorCode605ContactServiceValue(userDO), ErrorCode.BANNED_ERROR, "被封禁用户访问");
    }
}
