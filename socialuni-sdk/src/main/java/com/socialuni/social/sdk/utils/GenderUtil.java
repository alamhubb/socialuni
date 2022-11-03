package com.socialuni.social.sdk.utils;

import com.socialuni.social.tance.sdk.enumeration.GenderType;
import com.socialuni.social.common.sdk.exception.exception.SocialParamsException;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 10:06
 */
public class GenderUtil {
    //校验query的GenderType是否正确
    public static void checkUserEditGenderType(String gender) {
        String mapGender = GenderType.GenderTypeUserEditMap.get(gender);
        if (mapGender == null) {
            throw new SocialParamsException("错误的性别类型");
        }
    }
}
