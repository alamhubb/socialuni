package com.socialuni.sdk.logic.service;

import com.socialuni.sdk.logic.domain.SocialCosGetAuthDomain;
import com.socialuni.sdk.utils.SocialuniUserUtil;
import com.socialuni.sdk.model.RO.app.SocialCosAuthRO;
import com.socialuni.social.web.sdk.model.ResultRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialCosService {
    @Resource
    private SocialCosGetAuthDomain socialCosGetAuthDomain;

    public ResultRO<SocialCosAuthRO> getCosAuthorization() {
        Integer userId = SocialuniUserUtil.getMineUserIdNotNull();
        String uploadImgPath = "socialApp/user/" + userId + "/";
        SocialCosAuthRO socialCosAuthRO = socialCosGetAuthDomain.getCosAuthorization(uploadImgPath);
        return new ResultRO<>(socialCosAuthRO);
    }
}
