package com.socialuni.social.common.api.exception.exception;

import com.socialuni.social.common.api.constant.ErrorCode;
import com.socialuni.social.common.api.exception.base.SocialWarnException;

//逻辑可能有问题，或者被突破，预见了但不应该
public class SocialIpLimitException extends SocialWarnException {
    //用户未登录访问了需要登录的接口
    public SocialIpLimitException(String errorMsg) {
        super("访问频繁，请联系客服QQ:491369310", ErrorCode.IP_LIMIT_ERROR, "访问频繁，请联系客服QQ:491369310");
    }
}
