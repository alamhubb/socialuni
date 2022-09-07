package com.socialuni.web.controller;

import com.socialuni.sdk.serive.CenterCosService;
import com.socialuni.sdk.model.RO.app.SocialCosAuthRO;
import com.socialuni.social.web.sdk.model.ResultRO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("cos")

@RestController
public class CenterCosController {
    @Resource
    CenterCosService centerCosService;

    @PostMapping("getCosAuth")
    public ResultRO<SocialCosAuthRO> getCosAuth() {
        return centerCosService.getCosAuth();
    }
}
