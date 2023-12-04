package com.socialuni.social.admin.service;


import com.socialuni.social.admin.controller.DevAccountRO;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.user.SocialuniMineUserDetailRO;
import com.socialuni.social.common.api.model.user.SocialuniUserRO;
import com.socialuni.social.common.api.utils.SocialTokenFacade;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.sdk.logic.service.login.SocialuniDetailLoginService;
import com.socialuni.social.tance.entity.DevAccountEntity;
import com.socialuni.social.tance.sdk.api.DevAccountInterface;
import com.socialuni.social.tance.sdk.model.DevAccountModel;
import com.socialuni.social.user.sdk.logic.domain.SocialuniLoginDomain;
import com.socialuni.social.user.sdk.model.QO.SocialPhoneNumQO;
import com.socialuni.social.user.sdk.model.RO.login.SocialLoginRO;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
public class AdminLoginService {
    @Resource
    DevAccountInterface devAccountApi;
    @Resource
    DevAccountEntity devAccountEntity;

    @Resource
    private SocialuniDetailLoginService centerLoginService;
    @Resource
    private SocialuniLoginDomain socialuniLoginDomain;

    //秘钥登录
    @Transactional
    public ResultRO<SocialLoginRO<SocialuniUserRO>> secretKeyLogin(DevAccountInterface.DevAccountQueryQO devAccountQueryQO) {
        DevAccountModel devAccountModel = devAccountApi.findOneBySecretKey(devAccountQueryQO.getSecretKey());

        Integer userId = devAccountModel.getUserId();

        SocialuniUserDo socialuniUserDo = SocialuniUserUtil.getAndCheckUserNotNull(userId);

        SocialLoginRO<SocialuniUserRO> socialLoginRO = socialuniLoginDomain.getSocialLoginROByMineUser(socialuniUserDo);

        return ResultRO.success(socialLoginRO);
    }


    @Transactional
    ResultRO<SocialLoginRO<DevAccountRO>> phoneLogin(SocialPhoneNumQO socialPhoneNumQO) {
        String phoneNum = socialPhoneNumQO.getPhoneNum();

        ResultRO<SocialLoginRO<SocialuniMineUserDetailRO>> resultRO = centerLoginService.phoneLogin(socialPhoneNumQO);


        //如果手机号已经存在账户，则直接使用，正序获取第一个用户
        DevAccountModel devAccountModel = devAccountApi.findOneByPhoneNumOrderByIdAsc(phoneNum);

        Boolean flag = false;
        //判断开发者账户是否拥有c端用户
        if (devAccountModel == null) {
            devAccountModel = devAccountEntity.createDevAccount(phoneNum);
            flag = true;
        }

        return getSocialLoginROResultRO(devAccountModel, flag);
    }

    private ResultRO<SocialLoginRO<DevAccountRO>> getSocialLoginROResultRO(DevAccountModel devAccountModel, boolean isNewAccount) {
        //有用户返回，没有创建
//        String platform = loginVO.getPlatform();
        String devSecretKey = devAccountModel.getSecretKey();
        //生成userToken
        String userToken = SocialTokenFacade.generateTokenByUserKey(devSecretKey);
//        userToken = devTokenApi.savePut(new DevTokenModler(userToken, devAccountModel.getId())).getTokenCode();

        DevAccountRO devAccountRO = new DevAccountRO(devAccountModel);
        if (isNewAccount) {
            devAccountRO.setSecretKey(devAccountModel.getSecretKey());
        }
//        devAccountRO.setSecretKey(devAccountDO.getSecretKey());

        SocialLoginRO<DevAccountRO> socialLoginRO = new SocialLoginRO<>(userToken, devAccountRO);

        return ResultRO.success(socialLoginRO);
    }
}
