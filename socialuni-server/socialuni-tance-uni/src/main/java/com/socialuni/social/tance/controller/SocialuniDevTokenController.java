package com.socialuni.social.tance.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.utils.SocialTokenFacade;
import com.socialuni.social.common.api.utils.UUIDUtil;
import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.tance.model.DO.SocialuniDevTokenDO;
import com.socialuni.social.tance.sdk.api.SocialuniDevTokenAPI;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import com.socialuni.social.tance.sdk.model.DevAccountModel;
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
