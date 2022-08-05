package com.socialuni.center.web.insystem;

import com.socialuni.center.web.feignAPI.SocialuniDevAccountAPI;
import com.socialuni.social.entity.model.DO.dev.DevAccountDO;
import com.socialuni.center.web.model.QO.dev.DevAccountQueryQO;
import com.socialuni.center.web.repository.dev.DevAccountRepository;
import com.socialuni.social.api.model.ResultRO;
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
