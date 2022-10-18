package com.socialuni.social.tance.controller;

import com.socialuni.social.tance.repository.AppConfigRepository;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import com.socialuni.social.tance.entity.AppConfigDO;
import com.socialuni.social.tance.entity.AppConfigPk;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wulinghui
 * @version 1.0
 * @module Socialuni
 * @date 2022/10/16 19:55
 * @since 1.0
 */
@RestController
@RequestMapping("config")
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

    /**
     * 获得所有列表
     */
    @GetMapping("getAllConfigs")
    public List<AppConfigDO> getAllConfigs() {
        return appConfigRepository.findAllByDevIdAndStatusOrderByCreateTimeDesc(DevAccountFacade.getDevIdNotNull(), 1);
    }
}
