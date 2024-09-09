package com.socialuni.social.admin.service;


import com.socialuni.social.admin.controller.DevAccountRO;
import com.socialuni.social.app.logic.service.SocialuniDetailLoginService;
import com.socialuni.social.common.api.constant.SocialuniLoginType;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.user.SocialuniUserShowRO;
import com.socialuni.social.common.api.utils.SocialTokenFacade;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.tance.dev.dao.DO.DevAccountDo;
import com.socialuni.social.tance.config.DevAccountEntity;
import com.socialuni.social.tance.dev.api.DevAccountInterface;
import com.socialuni.social.user.sdk.logic.domain.SocialuniLoginDomain;
import com.socialuni.social.user.sdk.model.QO.SocialPhoneNumAuthCodeQO;
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
    public ResultRO<SocialLoginRO<SocialuniUserShowRO>> secretKeyLogin(DevAccountInterface.DevAccountQueryQO devAccountQueryQO) {
        DevAccountDo devAccountDo = devAccountApi.findOneBySecretKey(devAccountQueryQO.getSecretKey());

        Long userId = devAccountDo.getUserId();

        SocialuniUserDo socialuniUserDo = SocialuniUserUtil.getUserNotNull(userId);

        SocialLoginRO<SocialuniUserShowRO> socialLoginRO = socialuniLoginDomain.getSocialLoginROByMineUser(socialuniUserDo, SocialuniLoginType.secretKey);

        return ResultRO.success(socialLoginRO);
    }


    @Transactional
    public ResultRO<SocialLoginRO<DevAccountRO>> phoneLogin(SocialPhoneNumAuthCodeQO socialPhoneNumQO) {
        String phoneNum = socialPhoneNumQO.getPhoneNum();

        ResultRO<SocialLoginRO<SocialuniUserShowRO>> resultRO = centerLoginService.phoneLogin(socialPhoneNumQO);


        //如果手机号已经存在账户，则直接使用，正序获取第一个用户
        DevAccountDo devAccountDo = devAccountApi.findOneByPhoneNumOrderByIdAsc(phoneNum);

        Boolean flag = false;
        //判断开发者账户是否拥有c端用户
        if (devAccountDo == null) {
            devAccountDo = devAccountEntity.createDevAccount(phoneNum);
            flag = true;
        }

        return getSocialLoginROResultRO(devAccountDo, flag);
    }

    private ResultRO<SocialLoginRO<DevAccountRO>> getSocialLoginROResultRO(DevAccountDo devAccountDo, boolean isNewAccount) {
        //有用户返回，没有创建
//        String platform = loginVO.getPlatform();
        String devSecretKey = devAccountDo.getSecretKey();
        //生成userToken
        String userToken = SocialTokenFacade.createTokenByUserKey(devSecretKey);
//        userToken = devTokenApi.savePut(new DevTokenModler(userToken, devAccountModel.getId())).getTokenCode();

        DevAccountRO devAccountRO = new DevAccountRO(devAccountDo);
        if (isNewAccount) {
            devAccountRO.setSecretKey(devAccountDo.getSecretKey());
        }
//        devAccountRO.setSecretKey(devAccountDO.getSecretKey());

        SocialLoginRO<DevAccountRO> socialLoginRO = new SocialLoginRO<>(userToken, devAccountRO);

        return ResultRO.success(socialLoginRO);
    }
}
