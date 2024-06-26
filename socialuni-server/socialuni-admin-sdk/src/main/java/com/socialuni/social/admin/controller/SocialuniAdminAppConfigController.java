package com.socialuni.social.admin.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.utils.MapUtil;
import com.socialuni.social.tance.entity.SocialuniUnionIdDo;
import com.socialuni.social.tance.model.DO.AppConfigDO;
import com.socialuni.social.tance.model.DO.AppConfigPk;
import com.socialuni.social.tance.repository.AppConfigRepository;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.tance.sdk.model.SocialuniUnionIdModler;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.springframework.stereotype.Controller;
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
@Controller
@RequestMapping("config")
public class SocialuniAdminAppConfigController {
    @Resource
    private AppConfigRepository appConfigRepository;

    @PostMapping("save")
    @ResponseBody
    public AppConfigDO save(@RequestBody AppConfigDO appConfigDO) {
        Integer mineUserId = SocialuniUserUtil.getMineUserIdNotNull();

        Integer devId = DevAccountFacade.getAdminDevIdNotNull(mineUserId);
        appConfigDO.setDevId(devId);
//        if(AppConfigRepository.DEFAULT_DEV_KEY.equals(appConfigDO.getDevId()))
//            throw new SocialParamsException("联盟默认数据不可修改");


        return appConfigRepository.save(appConfigDO);
    }

    @PostMapping("deleteById")
    @ResponseBody
    public void deleteById(@RequestBody AppConfigPk appConfigPk) {
        Integer mineUserId = SocialuniUserUtil.getMineUserIdNotNull();

        Integer devId = DevAccountFacade.getAdminDevIdNotNull(mineUserId);
        appConfigPk.setDevId(devId);
//        if(AppConfigRepository.DEFAULT_DEV_KEY.equals(appConfigPk.getDevId()))
//            throw new SocialParamsException("联盟默认数据不可删除");

        appConfigRepository.deleteById(appConfigPk);
    }

    /**
     * 获得所有列表
     */
    @GetMapping("getAllConfigs")
    @ResponseBody
    public ResultRO<List<AppConfigDO>> getAllConfigs() {
        Integer mineUserId = SocialuniUserUtil.getMineUserIdNotNull();

        Integer devId = DevAccountFacade.getAdminDevIdNotNull(mineUserId);

        int status = 1;
        // 默认的内容。
        List<AppConfigDO> defaultList = appConfigRepository.findAllByDevIdAndStatusOrderByCreateTimeDesc(AppConfigRepository.DEFAULT_DEV_KEY, status);

        // 自己定义的
        List<AppConfigDO> devList = appConfigRepository.findAllByDevIdAndStatusOrderByCreateTimeDesc(devId, status);
        //
        CollectionUtil.addAll(defaultList, devList);
        // 去重
        List<AppConfigDO> distinct = CollectionUtil.distinct(defaultList, AppConfigDO::getConfigKey, true);

        return ResultRO.success(distinct);
    }

    /**
     * 获得所有列表属于map形式
     */
    @GetMapping("getAllConfigsOfMap")
    @ResponseBody
    public ResultRO<Map<String, Object>> getAllConfigsOfMap() {
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
        return ResultRO.success(MapUtil.pivot(distinct, "configKey", "value"));
    }
}
