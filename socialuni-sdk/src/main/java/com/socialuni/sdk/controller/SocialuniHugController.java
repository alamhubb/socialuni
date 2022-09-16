package com.socialuni.sdk.controller;

import com.socialuni.sdk.config.SocialuniSystemConst;
import com.socialuni.sdk.feignAPI.SocialuniHugAPI;
import com.socialuni.sdk.logic.service.SocialuniHugService;
import com.socialuni.sdk.model.QO.SocialuniHugAddQO;
import com.socialuni.social.web.sdk.model.ResultRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SocialuniHugController implements SocialuniHugAPI {
    @Resource
    SocialuniHugService centerHugAPIImpl;
    @Resource
    SocialuniHugAPI socialuniHugAPI;

    @Override
    public ResultRO<Void> addHug(SocialuniHugAddQO socialHugAddQO) {
        centerHugAPIImpl.addHug(socialHugAddQO);
        //如果应用，则调用中心
        if (SocialuniSystemConst.serverIsChild()) {
            return socialuniHugAPI.addHug(socialHugAddQO);
        }
        return new ResultRO<>();
    }
}