package com.socialuni.center.web.controller;

import com.socialuni.center.web.feignAPI.SocialuniCosAPI;
import com.socialuni.center.web.serive.CenterCosService;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.center.web.model.RO.app.SocialCosAuthRO;
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
