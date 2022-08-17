package com.socialuni.center.controller.socialuni;

import com.socialuni.sdk.config.SocialAppConfig;
import com.socialuni.sdk.feignAPI.SocialuniHugAPI;
import com.socialuni.sdk.model.QO.CenterHugAddQO;
import com.socialuni.sdk.serive.CenterHugService;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("hug")
public class OldCenterHugController {
    @Resource
    CenterHugService centerHugAPIImpl;
    @Resource
    SocialuniHugAPI socialuniHugAPI;

    @PostMapping("addHug")
    public ResultRO<Void> addHug(@RequestBody @Valid CenterHugAddQO socialHugAddQO){
        centerHugAPIImpl.addHug(socialHugAddQO);
        //如果应用，则调用中心
        if (SocialAppConfig.serverIsChild()) {
            return socialuniHugAPI.addHug(socialHugAddQO);
        }
        return new ResultRO<>();
    }
}
