package com.socialuni.social.sdk.controller;

import com.socialuni.social.sdk.feignAPI.openData.SocialuniCosAPI;
import com.socialuni.social.sdk.logic.service.SocialuniCosService;
import com.socialuni.social.sdk.model.RO.app.SocialCosAuthRO;
import com.socialuni.social.common.sdk.model.ResultRO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("socialuni/cos")
@RestController
public class SocialuniCosController implements SocialuniCosAPI {
    @Resource
    SocialuniCosService centerCosService;

    public ResultRO<SocialCosAuthRO> getCosAuth() {
        return centerCosService.getCosAuth();
    }
}
