package com.socialuni.sdk.utils;

import com.socialuni.sdk.constant.socialuni.GenderType;
import com.socialuni.social.web.sdk.exception.SocialParamsException;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 10:06
 */
public class GenderUtil {
    //仅做对旧版本的兼容处理，修改用户的兼容
    public static String getUserEditGenderByOldGender(String gender) {
        String mapGender = GenderType.GenderTypeUserEditOldMap.get(gender);
        if (mapGender != null) {
            return mapGender;
        }
        return gender;
    }

    //校验query的GenderType是否正确
    public static void checkUserEditGenderType(String gender) {
        String mapGender = GenderType.GenderTypeUserEditMap.get(gender);
        if (mapGender == null) {
            throw new SocialParamsException("错误的性别类型");
        }
    }
}
