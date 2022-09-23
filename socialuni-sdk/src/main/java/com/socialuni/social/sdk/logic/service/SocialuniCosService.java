package com.socialuni.social.sdk.logic.service;

import com.socialuni.social.sdk.dao.DO.dev.DevAccountDO;
import com.socialuni.social.sdk.logic.platform.tencent.TencentCloudAPI;
import com.socialuni.social.sdk.model.RO.app.SocialCosAuthRO;
import com.socialuni.social.sdk.utils.DevAccountUtils;
import com.socialuni.social.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.common.model.ResultRO;
import org.springframework.stereotype.Service;

@Service
public class SocialuniCosService {
    public ResultRO<SocialCosAuthRO> getCosAuth() {
        String userId = SocialuniUserUtil.getMineUserUuidIdNotNull();
        DevAccountDO devAccountDO = DevAccountUtils.getDevAccountNotNull();
//        String devAppName = Pinyin.toPinyin(devAccountDO.getAppName(), "").toLowerCase();
//        String uploadImgPath = devAppName + "/user/" + userId + "/";
        SocialCosAuthRO socialCosAuthRO = TencentCloudAPI.getCosAuthorization(userId);
        return new ResultRO<>(socialCosAuthRO);
    }
}
