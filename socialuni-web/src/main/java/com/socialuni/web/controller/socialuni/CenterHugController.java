package com.socialuni.web.controller.socialuni;

import com.socialuni.sdk.feignAPI.SocialuniHugAPI;
import com.socialuni.sdk.model.QO.CenterHugAddQO;
import com.socialuni.sdk.serive.CenterHugService;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CenterHugController implements SocialuniHugAPI {
    @Resource
    CenterHugService centerHugAPIImpl;

    @Override
    public ResultRO<Void> addHug(CenterHugAddQO socialHugAddQO) {
        return centerHugAPIImpl.addHug(socialHugAddQO);
    }
}
