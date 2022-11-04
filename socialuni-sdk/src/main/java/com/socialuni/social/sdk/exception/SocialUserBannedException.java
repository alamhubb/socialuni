package com.socialuni.social.sdk.exception;

import com.socialuni.social.common.api.constant.ErrorCode;
import com.socialuni.social.common.api.exception.base.SocialInfoException;
import com.socialuni.social.sdk.utils.ErrorMsgUtil;
import com.socialuni.social.user.sdk.entity.SocialuniUserDo;

public class SocialUserBannedException extends SocialInfoException {
    //用户被封禁
    public SocialUserBannedException(SocialuniUserDo userDO) {
        super(ErrorMsgUtil.getErrorCode605ContactServiceValue(userDO), ErrorCode.BANNED_ERROR, "被封禁用户访问");
    }
}
