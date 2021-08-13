package com.socialuni.social.sdk.utils;

import com.socialuni.social.exception.SocialBusinessException;
import com.socialuni.social.utils.IntegerUtils;
import org.apache.commons.lang.StringUtils;

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
