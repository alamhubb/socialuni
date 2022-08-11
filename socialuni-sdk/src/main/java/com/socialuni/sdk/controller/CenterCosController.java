package com.socialuni.sdk.controller;

import com.socialuni.sdk.feignAPI.SocialuniCosAPI;
import com.socialuni.sdk.serive.CenterCosService;
import com.socialuni.sdk.model.RO.app.SocialCosAuthRO;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CenterCosController implements SocialuniCosAPI {
    @Resource
    CenterCosService centerCosService;

    @Override
    public ResultRO<SocialCosAuthRO> getCosAuth() {
        return centerCosService.getCosAuth();
    }
}
