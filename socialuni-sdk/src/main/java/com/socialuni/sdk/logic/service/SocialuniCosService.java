package com.socialuni.sdk.logic.service;

import com.socialuni.sdk.dao.DO.dev.DevAccountDO;
import com.socialuni.sdk.logic.platform.tencent.TencentCloudAPI;
import com.socialuni.sdk.model.RO.app.SocialCosAuthRO;
import com.socialuni.sdk.utils.DevAccountUtils;
import com.socialuni.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.web.sdk.model.ResultRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
