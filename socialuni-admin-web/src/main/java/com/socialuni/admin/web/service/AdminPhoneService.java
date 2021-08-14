package com.socialuni.admin.web.service;


import com.socialuni.admin.web.domain.AdminSendAuthCodeDomain;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.RO.user.phone.SocialSendAuthCodeQO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminPhoneService {
    @Resource
    AdminSendAuthCodeDomain adminSendAuthCodeDomain;

    public ResultRO<Void> sendAuthCode(SocialSendAuthCodeQO authCodeQO) {
        adminSendAuthCodeDomain.sendAuthCode(authCodeQO.getPhoneNum());
        return ResultRO.success();
    }
}
