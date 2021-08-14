package com.socialuni.center.web.insystem.admin;

import com.socialuni.api.feignAPI.SocialuniAdminAPI;
import com.socialuni.center.web.utils.DevAccountUtils;
import com.socialuni.entity.model.DevAccountDO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.exception.SocialParamsException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import socialuni.social.sdk.web.constant.DevEnvType;

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
    @Value("${spring.profiles.active}")
    private String envType;

    @Override
    public ResultRO<Void> syncProdDevAccount(DevAccountDO devAccount) {
        if (!DevAccountUtils.getDevId().equals(1)) {
            throw new SocialParamsException("内部接口，不对外部提供服务");
        }
        if (!envType.contains(DevEnvType.prod)) {
            throw new SocialParamsException("此接口仅开发环境可用");
        }
        socialuniAdminService.syncProdDevAccountToDev(devAccount);
        return ResultRO.success();
    }
}
