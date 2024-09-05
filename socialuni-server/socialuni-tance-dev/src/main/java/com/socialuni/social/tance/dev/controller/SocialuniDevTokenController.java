package com.socialuni.social.tance.dev.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.utils.UUIDUtil;
import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.tance.dev.dao.DO.SocialuniDevTokenDO;
import com.socialuni.social.tance.dev.api.SocialuniDevTokenAPI;
import com.socialuni.social.tance.dev.facade.DevAccountFacade;
import com.socialuni.social.tance.dev.model.DevAccountModel;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public class SocialuniDevTokenController implements SocialuniDevTokenAPI {

    public ResultRO<String> getAuthToken(String devSecretKey) {
        DevAccountModel devAccountModel = DevAccountFacade.getDevAccountBySecretKeyNotNull(devSecretKey);

        String token = UUIDUtil.getUUID();

        SocialuniDevTokenDO socialuniDevTokenDO = new SocialuniDevTokenDO(token);

        //保存
        socialuniDevTokenDO = SocialuniRepositoryFacade.save(socialuniDevTokenDO);

        return ResultRO.success(socialuniDevTokenDO.getToken());
    }
}
