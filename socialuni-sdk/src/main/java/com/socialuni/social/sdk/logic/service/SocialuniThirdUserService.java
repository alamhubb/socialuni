package com.socialuni.social.sdk.logic.service;

import com.socialuni.social.sdk.logic.factory.RO.user.SocialuniMineUserDetailROFactory;
import com.socialuni.social.sdk.logic.domain.user.SocialAddUserImgDomain;
import com.socialuni.social.sdk.logic.domain.user.SocialDeleteUserImgDomain;
import com.socialuni.social.sdk.logic.domain.user.SocialEditUserDomain;
import com.socialuni.social.sdk.logic.entity.UniUserRegistryDomain;
import com.socialuni.social.sdk.logic.manage.TokenManage;
import com.socialuni.social.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.social.sdk.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.sdk.model.RO.user.SocialuniMineUserDetailRO;
import com.socialuni.social.sdk.model.RO.user.login.SocialLoginRO;
import com.socialuni.social.sdk.dao.repository.SocialuniUnionIdRepository;
import com.socialuni.social.sdk.utils.DevAccountUtils;
import com.socialuni.social.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.common.model.ResultRO;
import com.socialuni.social.common.exception.exception.SocialParamsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@Slf4j
public class SocialuniThirdUserService {
    @Resource
    SocialEditUserDomain socialEditUserDomain;
    @Resource
    SocialAddUserImgDomain socialAddUserImgDomain;
    @Resource
    SocialDeleteUserImgDomain socialDeleteUserImgDomain;
    @Resource
    UniUserRegistryDomain socialuniUserRegistryDomain;
    @Resource
    SocialuniUnionIdRepository uniContentUnionIdRepository;
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
        SocialuniMineUserDetailRO mineUser = SocialuniMineUserDetailROFactory.getMineUserDetail(mineUserDO);
        return new ResultRO<>(mineUser);
    }

}
