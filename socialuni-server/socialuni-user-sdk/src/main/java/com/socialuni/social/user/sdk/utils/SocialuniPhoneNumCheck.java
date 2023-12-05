package com.socialuni.social.user.sdk.utils;

import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.api.utils.NumberUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author qinkaiyuan
 * @date 2019-02-17 13:48
 */
public class SocialuniPhoneNumCheck {
    public static void checkPhoneNum(String phoneNum) {
        if (StringUtils.isEmpty(phoneNum) || NumberUtils.strHasNoNumber(phoneNum) || phoneNum.length() != 11) {
            throw new SocialBusinessException("请输入正确的手机号");
        }
    }
}
