package com.socialuni.social.user.sdk.exception;

import com.qingchi.qing.common.exception.base.ErrorCode;
import com.qingchi.qing.common.exception.base.SocialInfoException;
import com.socialuni.social.user.sdk.utils.ErrorMsgUtil;

public class SocialUserBannedException extends SocialInfoException {
    //用户被封禁
    public SocialUserBannedException() {
        super(ErrorMsgUtil.getErrorCode605ContactServiceValue(), ErrorCode.BANNED_ERROR, "被封禁用户访问");
    }
}
