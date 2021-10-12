package com.socialuni.demo.controller.socialuni;

import com.socialuni.api.feignAPI.SocialuniCosAPI;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.RO.app.SocialCosAuthRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CosController implements SocialuniCosAPI {
    @Resource
    SocialuniCosAPI socialuniCosAPI;

    public ResultRO<SocialCosAuthRO> getCosAuth() {
        return socialuniCosAPI.getCosAuth();
    }
}
