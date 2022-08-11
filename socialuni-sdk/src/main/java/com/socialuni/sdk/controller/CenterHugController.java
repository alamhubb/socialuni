package com.socialuni.sdk.controller;

import com.socialuni.sdk.feignAPI.SocialuniHugAPI;
import com.socialuni.sdk.model.QO.CenterHugAddQO;
import com.socialuni.sdk.serive.CenterHugService;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CenterHugController implements SocialuniHugAPI {
    @Resource
    CenterHugService centerHugService;

    @Override
    public ResultRO<Void> addHug(CenterHugAddQO socialHugAddQO) {
        return centerHugService.addHug(socialHugAddQO);
    }
}
