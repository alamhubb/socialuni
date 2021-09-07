package com.socialuni.social.web.controller;

import com.socialuni.social.sdk.service.SocialCosService;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.RO.app.SocialCosAuthRO;
import com.socialuni.social.sdk.url.SocialuniCosUrl;
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
