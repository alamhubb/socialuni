package com.socialuni.social.sdk.logic.service;

import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.sdk.logic.domain.user.SocialAddUserImgDomain;
import com.socialuni.social.sdk.logic.domain.user.SocialDeleteUserImgDomain;
import com.socialuni.social.user.sdk.logic.domain.SocialEditUserDomain;
import com.socialuni.social.sdk.logic.entity.UniUserRegistryDomain;
import com.socialuni.social.sdk.logic.factory.RO.user.SocialuniMineUserDetailROFactory;
import com.socialuni.social.user.sdk.manage.SocialuniTokenManage;
import com.socialuni.social.user.sdk.model.QO.SocialProviderLoginQO;
import com.socialuni.social.common.api.model.user.SocialuniMineUserDetailRO;
import com.socialuni.social.user.sdk.model.RO.login.SocialLoginRO;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.tance.sdk.api.SocialuniUnionIdInterface;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import com.socialuni.social.common.sdk.dao.DO.keywords.SocialuniUserDo;
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
    SocialuniUnionIdInterface uniContentUnionIdRepository;
    @Resource
    SocialuniTokenManage tokenManage;
    @Transactional
    public ResultRO<SocialLoginRO<SocialuniMineUserDetailRO>> registryUser(SocialProviderLoginQO loginQO) {
        //注册只向三方开发，所以不能为自己
        Integer dataDevId = DevAccountFacade.getDevIdNotNull();
        if (dataDevId == 1) {
            throw new SocialParamsException("开发者信息错误");
        }
        SocialLoginRO<SocialuniMineUserDetailRO> socialLoginRO = socialuniUserRegistryDomain.registryUser(loginQO);

        return new ResultRO<>(socialLoginRO);
    }


    public ResultRO<SocialuniMineUserDetailRO> queryThirdUser() {
        SocialuniUserDo mineUserDO = SocialuniUserUtil.getMineUserAllowNull();
        if (mineUserDO == null) {
            return ResultRO.success();
        }
        SocialuniMineUserDetailRO mineUser = SocialuniMineUserDetailROFactory.getMineUserDetail(mineUserDO);
        return new ResultRO<>(mineUser);
    }

}
