package com.socialuni.center.web.insystem;

import com.socialuni.center.sdk.feignAPI.SocialuniDevAccountAPI;
import com.socialuni.center.sdk.model.DevAccountDO;
import com.socialuni.center.sdk.model.QO.DevAccountQueryQO;
import com.socialuni.center.sdk.model.SyncProdDevAccountQO;
import com.socialuni.center.sdk.repository.DevAccountRepository;
import com.socialuni.center.sdk.utils.DevAccountUtils;
import com.socialuni.center.web.insystem.admin.SocialuniAdminService;
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
public class SocialuniDevAccountController implements SocialuniDevAccountAPI {
    @Resource
    DevAccountRepository devAccountRepository;

    @Override
    public ResultRO<DevAccountDO> queryDevAccount(DevAccountQueryQO devAccountQueryQO) {
        DevAccountDO devAccountDO = devAccountRepository.findOneBySecretKey(devAccountQueryQO.getSecretKey());
        return new ResultRO<>(devAccountDO);
    }

    @Override
    public ResultRO<DevAccountDO> queryDevAccountProvider(DevAccountQueryQO devAccountQueryQO) {
        return null;
    }
}
