package com.socialuni.sdk.constant.socialuni;

import com.socialuni.social.common.exception.exception.SocialParamsException;

import java.util.Arrays;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-02-27 21:34
 */
public class DevAccountType {
    //个人用户
    public static final String personal = "personal";
    //商业用户
    public static final String company = "company";

    public final static List<String> supportDevAccountTypes = Arrays.asList(personal, company);

    public static final String notSupportTypeErrorMsg = "社交联盟不支持的开发者类型";

    public static void checkSupportType(String type) {
        if (!supportDevAccountTypes.contains(type)) {
            throw new SocialParamsException(notSupportTypeErrorMsg);
        }
    }
}
