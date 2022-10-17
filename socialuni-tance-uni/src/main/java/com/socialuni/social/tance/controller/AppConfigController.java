package com.socialuni.social.tance.controller;

import com.socialuni.social.tance.sdk.model.AppConfigDO;
import com.socialuni.social.tance.sdk.model.AppConfigPk;
import com.socialuni.social.tance.repository.AppConfigRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wulinghui
 * @version 1.0
 * @module Socialuni
 * @date 2022/10/16 19:55
 * @since 1.0
 */
@RestController
@RequestMapping("appConfig")
public class AppConfigController {
    @Resource
    private AppConfigRepository appConfigRepository;

    @PostMapping("save")
    public AppConfigDO save(AppConfigDO appConfigDO) {
        return appConfigRepository.save(appConfigDO);
    }

    @PostMapping("deleteById")
    public void deleteById(AppConfigPk appConfigPk) {
        appConfigRepository.deleteById(appConfigPk);
    }
}
