package com.socialuni.social.sdk.utils;

import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import org.apache.commons.lang3.StringUtils;

/**
 * @author qinkaiyuan
 * @date 2019-02-17 13:48
 */
public class PhoneNumUtil {
    public static void checkPhoneNum(String phoneNum) {
        if (StringUtils.isEmpty(phoneNum) || IntegerUtils.strHasNoNumber(phoneNum) || phoneNum.length() != 11) {
            throw new SocialBusinessException("请输入正确的手机号");
        }
    }
}
