package com.socialuni.sdk.service;

import com.socialuni.sdk.domain.SocialCosGetAuthDomain;
import com.socialuni.sdk.utils.SocialUserUtil;
import com.socialuni.social.model.model.RO.ResultRO;
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
