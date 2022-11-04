package com.socialuni.social.sdk.logic.service;

import com.socialuni.social.sdk.feignAPI.openData.SocialuniCosAPI;
import com.socialuni.social.tance.sdk.enumeration.SocialuniSystemConst;
import com.socialuni.social.tance.sdk.model.DevAccountModel;
import com.socialuni.social.sdk.logic.platform.tencent.TencentCloudAPI;
import com.socialuni.social.sdk.model.RO.app.SocialCosAuthRO;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import com.socialuni.social.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.common.api.model.ResultRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialuniCosService {
    @Resource
    private SocialuniCosAPI socialuniCosAPI;

    public ResultRO<SocialCosAuthRO> getCosAuth() {
        //如果应用，则调用中心
        if (SocialuniSystemConst.serverIsChild()) {
            return socialuniCosAPI.getCosAuth();
        }
        String userId = SocialuniUserUtil.getMineUserUuidIdNotNull();
        DevAccountModel devAccountModel = DevAccountFacade.getDevAccountNotNull();
//        String devAppName = Pinyin.toPinyin(devAccountDO.getAppName(), "").toLowerCase();
//        String uploadImgPath = devAppName + "/user/" + userId + "/";
        SocialCosAuthRO socialCosAuthRO = TencentCloudAPI.getCosAuthorization(userId);
        return new ResultRO<>(socialCosAuthRO);
    }
}
