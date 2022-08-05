package com.socialuni.center.web.exception;

import com.socialuni.center.web.utils.ErrorMsgUtil;
import com.socialuni.social.exception.constant.ErrorCode;
import com.socialuni.social.exception.base.SocialInfoException;
import com.socialuni.center.web.model.DO.user.SocialUserDO;

public class SocialUserBannedException extends SocialInfoException {
    //用户被封禁
    public SocialUserBannedException(SocialUserDO userDO) {
        super(ErrorMsgUtil.getErrorCode605ContactServiceValue(userDO), ErrorCode.BANNED_ERROR, "被封禁用户访问");
    }
}
