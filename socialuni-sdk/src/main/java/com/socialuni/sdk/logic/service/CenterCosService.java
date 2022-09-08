package com.socialuni.sdk.logic.service;

import com.socialuni.sdk.logic.domain.SocialCosGetAuthDomain;
import com.socialuni.sdk.model.DO.dev.DevAccountDO;
import com.socialuni.sdk.model.RO.app.SocialCosAuthRO;
import com.socialuni.sdk.utils.DevAccountUtils;
import com.socialuni.sdk.utils.SocialUserUtil;
import com.socialuni.social.web.sdk.model.ResultRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CenterCosService {
    @Resource
    private SocialCosGetAuthDomain socialCosGetAuthDomain;

    public ResultRO<SocialCosAuthRO> getCosAuth() {
        String userId = SocialUserUtil.getMineUserUuidIdNotNull();
        DevAccountDO devAccountDO = DevAccountUtils.getDevAccountNotNull();
//        String devAppName = Pinyin.toPinyin(devAccountDO.getAppName(), "").toLowerCase();
//        String uploadImgPath = devAppName + "/user/" + userId + "/";
        String uploadImgPath = "user/" + userId + "/";
        SocialCosAuthRO socialCosAuthRO = socialCosGetAuthDomain.getCosAuthorization(uploadImgPath);
        return new ResultRO<>(socialCosAuthRO);
    }
}
