package com.socialuni.social.sdk.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.sdk.feignAPI.openData.SocialuniCosAPI;
import com.socialuni.social.sdk.logic.service.SocialuniCosService;
import com.socialuni.social.common.api.constant.SocialuniSystemConst;
import com.socialuni.social.common.sdk.platform.model.SocialCosAuthRO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("socialuni/cos")
@RestController
public class SocialuniCosController implements SocialuniCosAPI {
    @Resource
    SocialuniCosService centerCosService;

    public ResultRO<SocialCosAuthRO> getCosAuth() {
        return centerCosService.getCosAuth();
    }

    public ResultRO<String> getCosPath() {
        return ResultRO.success(SocialuniSystemConst.getStaticResourceUrl());
    }
}
