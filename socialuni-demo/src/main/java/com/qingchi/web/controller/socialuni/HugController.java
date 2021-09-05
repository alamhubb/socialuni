package com.qingchi.web.controller.socialuni;

import com.socialuni.api.feignAPI.SocialuniHugAPI;
import com.socialuni.api.model.QO.CenterHugAddQO;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class HugController implements SocialuniHugAPI {
    @Resource
    SocialuniHugAPI socialuniHugAPI;

    @Override
    public ResultRO<Void> addHug(CenterHugAddQO socialHugAddQO) {
        return socialuniHugAPI.addHug(socialHugAddQO);
    }
}
