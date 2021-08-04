package com.socialuni.demo.controller;

import com.socialuni.sdk.service.SocialCosService;
import com.socialuni.social.model.model.RO.ResultRO;
import com.socialuni.social.model.model.RO.app.SocialCosAuthRO;
import com.socialuni.sdk.url.SocialuniCosUrl;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SocialCosController implements SocialuniCosUrl {
    @Resource
    SocialCosService socialCosService;

    @Override
    public ResultRO<SocialCosAuthRO> getCosAuth() {
        return socialCosService.getCosAuthorization();
    }
}
