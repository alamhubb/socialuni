package com.socialuni.sdk.logic.service;

import com.socialuni.sdk.logic.domain.user.SocialAddUserImgDomain;
import com.socialuni.sdk.logic.domain.user.SocialDeleteUserImgDomain;
import com.socialuni.sdk.logic.domain.user.SocialEditUserDomain;
import com.socialuni.sdk.logic.entity.UniUserRegistryDomain;
import com.socialuni.sdk.factory.RO.user.CenterMineUserDetailROFactory;
import com.socialuni.sdk.logic.manage.TokenManage;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.model.QO.user.SocialProviderLoginQO;
import com.socialuni.sdk.model.RO.user.SocialuniMineUserDetailRO;
import com.socialuni.sdk.model.RO.user.login.SocialLoginRO;
import com.socialuni.sdk.dao.repository.UniContentUnionIdRepository;
import com.socialuni.sdk.utils.DevAccountUtils;
import com.socialuni.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.web.sdk.model.ResultRO;
import com.socialuni.social.web.sdk.exception.SocialParamsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@Slf4j
public class CenterThirdUserService {
    @Resource
    SocialEditUserDomain socialEditUserDomain;
    @Resource
    SocialAddUserImgDomain socialAddUserImgDomain;
    @Resource
    SocialDeleteUserImgDomain socialDeleteUserImgDomain;
    @Resource
    UniUserRegistryDomain socialuniUserRegistryDomain;
    @Resource
    UniContentUnionIdRepository uniContentUnionIdRepository;
    @Resource
    TokenManage tokenManage;
    @Transactional
    public ResultRO<SocialLoginRO<SocialuniMineUserDetailRO>> registryUser(SocialProviderLoginQO loginQO) {
        //注册只向三方开发，所以不能为自己
        Integer dataDevId = DevAccountUtils.getDevIdNotNull();
        if (dataDevId == 1) {
            throw new SocialParamsException("开发者信息错误");
        }
        SocialLoginRO<SocialuniMineUserDetailRO> socialLoginRO = socialuniUserRegistryDomain.registryUser(loginQO);

        return new ResultRO<>(socialLoginRO);
    }


    public ResultRO<SocialuniMineUserDetailRO> queryThirdUser() {
        SocialuniUserDO mineUserDO = SocialuniUserUtil.getMineUserAllowNull();
        if (mineUserDO == null) {
            return ResultRO.success();
        }
        SocialuniMineUserDetailRO mineUser = CenterMineUserDetailROFactory.getMineUserDetail(mineUserDO);
        return new ResultRO<>(mineUser);
    }

}
