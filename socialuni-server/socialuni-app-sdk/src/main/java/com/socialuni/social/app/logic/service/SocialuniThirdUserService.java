package com.socialuni.social.app.logic.service;

import com.socialuni.social.app.factory.SocialuniMineUserDetailROFactory;
import com.socialuni.social.app.model.SocialuniMineUserDetailRO;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.user.SocialuniUserRO;
import com.socialuni.social.user.sdk.model.QO.SocialProviderLoginQO;
import com.socialuni.social.user.sdk.model.RO.login.SocialLoginRO;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.tance.dev.facade.DevAccountFacade;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@Slf4j
public class SocialuniThirdUserService {
    @Resource
    SocialuniThirdUserRegistryDomain socialuniUserRegistryDomain;

    @Transactional
    public ResultRO<SocialLoginRO<SocialuniUserRO>> registryUser(SocialProviderLoginQO loginQO) {
        //注册只向三方开发，所以不能为自己
        Integer dataDevId = DevAccountFacade.getDevIdNullElseCenterDevId();
//        if (dataDevId == AdminAppConfigConst.testDevId) {
//            throw new SocialParamsException("开发者信息错误");
//        }
//        SocialLoginRO<SocialuniUserRO> socialLoginRO = socialuniUserRegistryDomain.registryUser(loginQO);

//        return new ResultRO<>(socialLoginRO);
        SocialLoginRO<SocialuniUserRO> socialLoginRO = socialuniUserRegistryDomain.registryUser(loginQO);
        return ResultRO.success(socialLoginRO);
    }


    public ResultRO<SocialuniUserRO> queryThirdUser() {
        SocialuniUserDo mineUserDO = SocialuniUserUtil.getMineUserAllowNull();
        if (mineUserDO == null) {
            return ResultRO.success();
        }
        SocialuniMineUserDetailRO mineUser = SocialuniMineUserDetailROFactory.getMineUserDetail(mineUserDO);
        return new ResultRO<>(mineUser);
    }

}
