package com.socialuni.social.sdk.logic.service;

import com.socialuni.social.common.sdk.feignAPI.openData.SocialuniCosAPI;
import com.socialuni.social.tance.dev.config.SocialuniDevConfig;
import com.socialuni.social.tance.dev.dao.DO.DevAccountDo;
import com.socialuni.social.content.tencent.TencentCloudAPI;
import com.socialuni.social.common.sdk.platform.model.SocialCosAuthRO;
import com.socialuni.social.tance.dev.facade.DevAccountFacade;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.common.api.model.ResultRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialuniCosService {
    @Resource
    private SocialuniCosAPI socialuniCosAPI;

    public ResultRO<SocialCosAuthRO> getCosAuth() {
        //如果应用，则调用中心
        if (SocialuniDevConfig.hasCenterServer()) {
            return socialuniCosAPI.getCosAuth();
        }
        String userId = SocialuniUserUtil.getMineUserUuidIdNotNull();
        DevAccountDo devAccountDo = DevAccountFacade.getDevAccountNullElseCenterDev();
//        String devAppName = Pinyin.toPinyin(devAccountDO.getAppName(), "").toLowerCase();
//        String uploadImgPath = devAppName + "/user/" + userId + "/";
        SocialCosAuthRO socialCosAuthRO = TencentCloudAPI.getCosAuthorization(userId);
        return new ResultRO<>(socialCosAuthRO);
    }
}
