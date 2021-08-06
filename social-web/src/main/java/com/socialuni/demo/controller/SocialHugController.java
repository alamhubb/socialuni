package com.socialuni.demo.controller;

import com.socialuni.sdk.service.SocialHugService;
import com.socialuni.social.model.model.QO.community.SocialHugAddQO;
import com.socialuni.social.model.model.RO.ResultRO;
import com.socialuni.sdk.url.community.SocialuniHugUrl;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SocialHugController implements SocialuniHugUrl {
    @Resource
    SocialHugService socialHugService;

    @Override
    public ResultRO<Void> addHug(SocialHugAddQO socialHugAddQO) {
        return socialHugService.addHug(socialHugAddQO);
    }
}
