package com.socialuni.center.web.utils;

import com.socialuni.api.model.RO.SocialuniUidRO;
import com.socialuni.center.web.model.DO.SocialuniUidDO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.sdk.config.SocialAppConfig;

import java.util.function.Function;


public class SocialuniUidDOUtils {
    //list转换，TO类List转为RO类List
    public static <QO, RO extends SocialuniUidRO> void createSocialuniDO(SocialuniUidRO appRO, Function<QO, ResultRO<RO>> function, QO postQO) {
        //包含中心服务器才使用
        if (SocialAppConfig.hasCenterServer()) {
            ResultRO<RO> resultRO = function.apply(postQO);
            SocialuniUidDO socialuniUidDO = new SocialuniUidDO(appRO, resultRO.getData());
        }
    }
}
