package com.socialuni.center.web.controller;

import com.socialuni.api.feignAPI.SocialuniHugAPI;
import com.socialuni.api.model.QO.CenterHugAddQO;
import com.socialuni.center.web.serive.CenterHugService;
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
