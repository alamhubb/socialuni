package com.socialuni.social.sdk.controller;

import com.socialuni.social.sdk.config.SocialuniSystemConst;
import com.socialuni.social.sdk.feignAPI.community.SocialuniHugAPI;
import com.socialuni.social.sdk.logic.service.SocialuniHugService;
import com.socialuni.social.sdk.model.QO.SocialuniHugAddQO;
import com.socialuni.social.common.model.ResultRO;
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
