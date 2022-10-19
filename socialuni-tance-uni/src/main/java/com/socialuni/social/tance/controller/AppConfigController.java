package com.socialuni.social.tance.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.socialuni.social.common.utils.MapUtil;
import com.socialuni.social.tance.entity.AppConfigDO;
import com.socialuni.social.tance.entity.AppConfigPk;
import com.socialuni.social.tance.repository.AppConfigRepository;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
    public AppConfigDO save(@RequestBody AppConfigDO appConfigDO) {
        Integer devId = DevAccountFacade.getDevIdNotNull();
        appConfigDO.setDevId(devId);
//        if(AppConfigRepository.DEFAULT_DEV_KEY.equals(appConfigDO.getDevId()))
//            throw new SocialParamsException("联盟默认数据不可修改");


        return appConfigRepository.save(appConfigDO);
    }

    @PostMapping("deleteById")
    public void deleteById(@RequestBody AppConfigPk appConfigPk) {
        Integer devId = DevAccountFacade.getDevIdNotNull();
        appConfigPk.setDevId(devId);
//        if(AppConfigRepository.DEFAULT_DEV_KEY.equals(appConfigPk.getDevId()))
//            throw new SocialParamsException("联盟默认数据不可删除");

        appConfigRepository.deleteById(appConfigPk);
    }

    /**
     * 获得所有列表
     */
    @GetMapping("getAllConfigs")
    public List<AppConfigDO> getAllConfigs() {
        int status = 1;
        // 默认的内容。
        List<AppConfigDO> defaultList = appConfigRepository.findAllByDevIdAndStatusOrderByCreateTimeDesc(AppConfigRepository.DEFAULT_DEV_KEY, status);
        // 自己定义的
        List<AppConfigDO> devList = appConfigRepository.findAllByDevIdAndStatusOrderByCreateTimeDesc(DevAccountFacade.getDevIdNotNull(), status);
        //
        CollectionUtil.addAll(defaultList, devList);
        // 去重
        List<AppConfigDO> distinct = CollectionUtil.distinct(defaultList, AppConfigDO::getConfigKey, true);

        return distinct;
    }
    /**
     * 获得所有列表属于map形式
     */
    @GetMapping("getAllConfigsOfMap")
    public Map<String,Object> getAllConfigsOfMap() {
        int status = 1;
        // 默认的内容。
        List<AppConfigDO> defaultList = appConfigRepository.findAllByDevIdAndStatusOrderByCreateTimeDesc(AppConfigRepository.DEFAULT_DEV_KEY, status);
        // 自己定义的
        List<AppConfigDO> devList = appConfigRepository.findAllByDevIdAndStatusOrderByCreateTimeDesc(DevAccountFacade.getDevIdNotNull(), status);
        //
        CollectionUtil.addAll(defaultList, devList);
        // 去重
        List<AppConfigDO> distinct = CollectionUtil.distinct(defaultList, AppConfigDO::getConfigKey, true);
        //
        return  MapUtil.pivot(distinct,"configKey","value");
    }
}
