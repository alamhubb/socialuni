package com.socialuni.social.sdk.utils;

import com.socialuni.social.constant.GenderType;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.sdk.constant.GenderTypeQueryVO;
import com.socialuni.social.exception.SocialParamsException;
import com.socialuni.social.sdk.constant.GenderTypeVO;
import org.apache.commons.lang3.StringUtils;

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
