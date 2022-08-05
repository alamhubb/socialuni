package com.socialuni.center.web.service;

import com.socialuni.center.web.domain.SocialCosGetAuthDomain;
import com.socialuni.center.web.utils.SocialUserUtil;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.center.web.model.RO.app.SocialCosAuthRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialCosService {
    @Resource
    private SocialCosGetAuthDomain socialCosGetAuthDomain;

    public ResultRO<SocialCosAuthRO> getCosAuthorization() {
        Integer userId = SocialUserUtil.getMineUserIdNotNull();
        String uploadImgPath = "socialApp/user/" + userId + "/";
        SocialCosAuthRO socialCosAuthRO = socialCosGetAuthDomain.getCosAuthorization(uploadImgPath);
        return new ResultRO<>(socialCosAuthRO);
    }
}
