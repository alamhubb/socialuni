package com.socialuni.center.web.insystem.admin;

import com.socialuni.center.sdk.feignAPI.SocialuniAdminAPI;
import com.socialuni.center.sdk.mode.SyncProdDevAccountQO;
import com.socialuni.center.sdk.utils.DevAccountUtils;
import com.socialuni.cloud.config.SocialAppEnv;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.exception.SocialParamsException;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 11:09
 * 前端初始化内容
 */
@RestController
public class SocialuniAdminController implements SocialuniAdminAPI {
    //绑定手机号，绑定微信手机号，发送验证码，手机号登录都是清池专属
    @Resource
    private SocialuniAdminService socialuniAdminService;

    @Override
    public ResultRO<Void> syncProdDevAccount(SyncProdDevAccountQO syncProdDevAccountQO) {
        if (!DevAccountUtils.getDevId().equals(1)) {
            throw new SocialParamsException("内部接口，不对外部提供服务");
        }
        //生产环境不支持
        if (!SocialAppEnv.getIsDevProdEnv()) {
            throw new SocialParamsException("此接口仅支持演示环境");
        }
        //同步生产环境开发者账号信息
        socialuniAdminService.syncProdDevAccountToDev(syncProdDevAccountQO);
        return new ResultRO<>();
    }
}
