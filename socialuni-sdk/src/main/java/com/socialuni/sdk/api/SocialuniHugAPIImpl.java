package com.socialuni.sdk.api;

import com.socialuni.sdk.feignAPI.SocialuniHugAPI;
import com.socialuni.sdk.model.QO.CenterHugAddQO;
import com.socialuni.sdk.serive.CenterHugService;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialuniHugAPIImpl implements SocialuniHugAPI {
    @Resource
    CenterHugService centerHugService;

    @Override
    public ResultRO<Void> addHug(CenterHugAddQO socialHugAddQO) {
        return centerHugService.addHug(socialHugAddQO);
    }
}
