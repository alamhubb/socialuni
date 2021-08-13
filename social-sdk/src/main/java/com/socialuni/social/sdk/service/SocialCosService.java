package com.socialuni.social.sdk.service;

import com.socialuni.social.sdk.domain.SocialCosGetAuthDomain;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.RO.app.SocialCosAuthRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialCosService {
    @Resource
    private SocialCosGetAuthDomain socialCosGetAuthDomain;

    public ResultRO<SocialCosAuthRO> getCosAuthorization() {
        String userId = SocialUserUtil.getMineUserStringId();
        String uploadImgPath = "socialApp/user/" + userId + "/";
        SocialCosAuthRO socialCosAuthRO = socialCosGetAuthDomain.getCosAuthorization(uploadImgPath);
        return new ResultRO<>(socialCosAuthRO);
    }
}
