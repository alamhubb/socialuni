package com.socialuni.admin.web.controller;


import com.socialuni.center.sdk.constant.SocialuniSupportProviderType;
import com.socialuni.center.sdk.redis.DevAccountRedis;
import com.socialuni.center.sdk.repository.DevAccountProviderRepository;
import com.socialuni.center.sdk.utils.DevAccountUtils;
import com.socialuni.entity.model.DevAccountDO;
import com.socialuni.entity.model.DevAccountProviderDO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.constant.DevAccountType;
import com.socialuni.social.exception.SocialParamsException;
import com.socialuni.social.sdk.constant.MpType;
import com.socialuni.social.sdk.constant.platform.PlatformType;
import com.socialuni.social.utils.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("user")
public class AdminAccountController {
    @Resource
    private DevAccountRedis devAccountRedis;
    @Resource
    private DevAccountProviderRepository devAccountProviderRepository;

    @PostMapping("getUser")
    public ResultRO<DevUserRO> getUser() {
        DevAccountDO user = DevAccountUtils.getAdminDevAccountNotNull();
        DevUserRO devUserRO = new DevUserRO(user);
        //则更新用户手机号
        return new ResultRO<>(devUserRO);
    }

    @PostMapping("resetSecretKey")
    public ResultRO<String> resetSecretKey() {
        DevAccountDO user = DevAccountUtils.getAdminDevAccountNotNull();
        String secretKey = UUIDUtil.getUUID();
        user.setSecretKey(secretKey);
        devAccountRedis.saveDevAccount(user);
        ResultRO<String> ResultRO = new ResultRO<>();
        ResultRO.setData(secretKey);
        //则更新用户手机号
        return ResultRO;
    }

    @PostMapping("updateUser")
    public ResultRO<DevUserRO> resetSecretKey(@RequestBody DevAccountUpdateQO devAccountQO) {
        DevAccountDO devAccountDO = DevAccountUtils.getAdminDevAccountNotNull();
        Integer devId = devAccountDO.getId();

        String devType = devAccountQO.getType();
        DevAccountType.checkSupportType(devType);

        String wxMpAppId = devAccountQO.getWxMpAppId();
        String wxMpAppName = devAccountQO.getWxMpAppName();
        String qqMpAppId = devAccountQO.getQqMpAppId();
        String qqMpAppName = devAccountQO.getQqMpAppName();

        //qq和微信都为空
        if (StringUtils.isEmpty(wxMpAppId) && StringUtils.isEmpty(qqMpAppId)) {
            throw new SocialParamsException("请至少填写一个小程序信息");
        }

        //微信渠道有一个不为空
        if (StringUtils.isNotEmpty(wxMpAppId) || StringUtils.isNotEmpty(wxMpAppName)) {
            if (StringUtils.isEmpty(wxMpAppId)) {
                throw new SocialParamsException("微信小程序Id不可为空");
            }
            if (StringUtils.isEmpty(wxMpAppName)) {
                throw new SocialParamsException("微信小程序名称不可为空");
            }
            //两个都不为空
            //根据绑定小程序查找，是否已被使用
            DevAccountProviderDO wxDevAccountProviderDO = DevAccountUtils.getDevAccountProviderDOByAppIdAndMpType(wxMpAppId, SocialuniSupportProviderType.wx);
            //已存在
            if (wxDevAccountProviderDO != null) {
                //是否与当前开发者id一致，不一致报错，已被使用
                if (!wxDevAccountProviderDO.getDevId().equals(devId)) {
                    throw new SocialParamsException("小程序已被其他开发者绑定，请联系客服处理");
                }
            }
            //查找开发者是否已绑定
            wxDevAccountProviderDO = DevAccountUtils.getDevAccountProviderDOByDevAndMpType(devId, SocialuniSupportProviderType.wx);
            //未绑定创建
            if (wxDevAccountProviderDO == null) {
                wxDevAccountProviderDO = new DevAccountProviderDO(devId, PlatformType.mp, MpType.wx);
            }
            //已绑定更新
            wxDevAccountProviderDO.setAppId(wxMpAppId);
            wxDevAccountProviderDO.setAppName(wxMpAppName);
            devAccountProviderRepository.save(wxDevAccountProviderDO);
        }


        //qq渠道有一个不为空
        if (StringUtils.isNotEmpty(qqMpAppId) || StringUtils.isNotEmpty(qqMpAppName)) {
            if (StringUtils.isEmpty(qqMpAppId)) {
                throw new SocialParamsException("qq小程序Id不可为空");
            }
            if (StringUtils.isEmpty(qqMpAppName)) {
                throw new SocialParamsException("qq小程序名称不可为空");
            }
            //两个都不为空
            //根据绑定小程序查找，是否已被使用
            DevAccountProviderDO qqDevAccountProviderDO = DevAccountUtils.getDevAccountProviderDOByAppIdAndMpType(qqMpAppId, SocialuniSupportProviderType.qq);
            //已存在
            if (qqDevAccountProviderDO != null) {
                //是否与当前开发者id一致，不一致报错，已被使用
                if (!qqDevAccountProviderDO.getDevId().equals(devId)) {
                    throw new SocialParamsException("小程序已被其他开发者绑定，请联系客服处理");
                }
            }
            //查找开发者是否已绑定
            qqDevAccountProviderDO = DevAccountUtils.getDevAccountProviderDOByDevAndMpType(devId, SocialuniSupportProviderType.qq);
            //未绑定创建
            if (qqDevAccountProviderDO == null) {
                qqDevAccountProviderDO = new DevAccountProviderDO(devId, PlatformType.mp, MpType.qq);
            }
            //已绑定更新
            qqDevAccountProviderDO.setAppId(qqMpAppId);
            qqDevAccountProviderDO.setAppName(qqMpAppName);
            devAccountProviderRepository.save(qqDevAccountProviderDO);
        }

        devAccountDO.setType(devType);
        devAccountDO.setRealName(devAccountQO.getRealName());

        devAccountDO = devAccountRedis.saveDevAccount(devAccountDO);

        DevUserRO devUserRO = new DevUserRO(devAccountDO);
        //则更新用户手机号
        return new ResultRO<>(devUserRO);
    }
}
