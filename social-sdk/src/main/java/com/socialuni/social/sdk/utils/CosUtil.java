package com.socialuni.social.sdk.utils;

import com.socialuni.social.utils.UUIDUtil;

/**
 * @author qinkaiyuan
 * @date 2019-10-24 17:28
 */
public class CosUtil {
    public static String getUserImgUrl(String userId) {
        return "user/" + userId + "/*";
    }

    public static String getUserImgPath() {
//        ThirdUserDO thirdUserDO = CenterUserUtil.getMineThirdUser();
        //初步查看没问题，是unionId生成的对应dev和user
//        String thirdUserId = thirdUserDO.getThirdUserId();
        String uuid = UUIDUtil.getUUID();
//        添加应用名称
//        String shortName = Pinyin.toPinyin(devAccountDO.getAppName(), "").toLowerCase();
//        if (ObjectUtil.hasEmpty(devNum, shortName, uuid)) {
//            throw new SocialParamsException("包含空内容");
//        }
        return "" + "/" + "showtname" + "/" + uuid + "/";
    }
}
