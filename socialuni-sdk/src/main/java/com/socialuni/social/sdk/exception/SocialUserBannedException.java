package com.socialuni.social.sdk.exception;

import com.socialuni.social.sdk.utils.ErrorMsgUtil;
import com.socialuni.social.user.sdk.model.SocialuniUserDO;
import com.socialuni.social.common.constant.ErrorCode;
import com.socialuni.social.common.exception.base.SocialInfoException;

public class SocialUserBannedException extends SocialInfoException {
    //用户被封禁
    public SocialUserBannedException(SocialuniUserDO userDO) {
        super(ErrorMsgUtil.getErrorCode605ContactServiceValue(userDO), ErrorCode.BANNED_ERROR, "被封禁用户访问");
    }
}
