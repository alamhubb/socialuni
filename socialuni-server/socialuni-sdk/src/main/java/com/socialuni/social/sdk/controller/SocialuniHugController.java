package com.socialuni.social.sdk.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.sdk.feignAPI.community.SocialuniHugAPI;
import com.socialuni.social.sdk.logic.service.SocialuniHugService;
import com.socialuni.social.community.sdk.model.QO.SocialuniHugAddQO;
import com.socialuni.social.common.api.constant.SocialuniSystemConst;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("socialuni/hug")
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
