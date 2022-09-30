package com.socialuni.social.sdk.logic.service;

import com.socialuni.social.tance.sdk.model.DevAccountModel;
import com.socialuni.social.sdk.logic.platform.tencent.TencentCloudAPI;
import com.socialuni.social.sdk.model.RO.app.SocialCosAuthRO;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import com.socialuni.social.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.common.model.ResultRO;
import org.springframework.stereotype.Service;

@Service
public class SocialuniCosService {
    public ResultRO<SocialCosAuthRO> getCosAuth() {
        String userId = SocialuniUserUtil.getMineUserUuidIdNotNull();
        DevAccountModel devAccountModel = DevAccountFacade.getDevAccountNotNull();
//        String devAppName = Pinyin.toPinyin(devAccountDO.getAppName(), "").toLowerCase();
//        String uploadImgPath = devAppName + "/user/" + userId + "/";
        SocialCosAuthRO socialCosAuthRO = TencentCloudAPI.getCosAuthorization(userId);
        return new ResultRO<>(socialCosAuthRO);
    }
}
