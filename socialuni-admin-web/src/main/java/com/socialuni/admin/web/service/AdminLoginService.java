package com.socialuni.admin.web.service;


import com.socialuni.admin.web.controller.DevAccountRO;
import com.socialuni.sdk.config.SocialTokenUtil;
import com.socialuni.sdk.entity.DevAccountEntity;
import com.socialuni.admin.web.manage.DevAuthCodeManage;
import com.socialuni.sdk.model.DO.dev.DevAccountDO;
import com.socialuni.sdk.model.DO.dev.DevTokenDO;
import com.socialuni.sdk.model.QO.dev.DevAccountQueryQO;
import com.socialuni.sdk.repository.dev.DevAccountRepository;
import com.socialuni.sdk.repository.dev.DevTokenRepository;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.exception.SocialBusinessException;
import com.socialuni.sdk.model.QO.user.SocialPhoneNumQO;
import com.socialuni.sdk.model.RO.user.login.SocialLoginRO;
import com.socialuni.sdk.utils.PhoneNumUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
public class AdminLoginService {
    @Resource
    DevAuthCodeManage devAuthCodeManage;
    @Resource
    DevAccountRepository devAccountRepository;
    @Resource
    DevAccountEntity devAccountEntity;
    @Resource
    DevTokenRepository devTokenRepository;

    //秘钥登录
    @Transactional
    public ResultRO<SocialLoginRO<DevAccountRO>> secretKeyLogin(DevAccountQueryQO devAccountQueryQO) {
        DevAccountDO devAccountDO = devAccountRepository.findOneBySecretKey(devAccountQueryQO.getSecretKey());
        if (devAccountDO == null) {
            throw new SocialBusinessException("秘钥错误");
        }
        //有用户返回，没有创建
//        String platform = loginVO.getPlatform();
        return getSocialLoginROResultRO(devAccountDO, false);
    }


    @Transactional
    public ResultRO<SocialLoginRO<DevAccountRO>> phoneLogin(SocialPhoneNumQO socialPhoneNumQO) {
        /*if (!SocialAppEnv.getContainsProdEnv()) {
            throw new SocialBusinessException("开发环境请使用秘钥登录");
        }*/
        //所有平台，手机号登陆方式代码一致
        //登录的时候如果没有手机号，则手机号注册成功，自动注册一个user，用户名待填，自动生成一个昵称，密码待填，头像待上传
        //如果已经登录过，则返回那个已经注册的user，根据手机号获取user，返回登录成功
        //记录用户错误日志
        String phoneNum = socialPhoneNumQO.getPhoneNum();
        String authCode = socialPhoneNumQO.getAuthCode();
        //校验手机号格式
        PhoneNumUtil.checkPhoneNum(phoneNum);
        //校验验证码，传null用户记录日志
        devAuthCodeManage.checkAuthCode(phoneNum, authCode);

        //如果手机号已经存在账户，则直接使用，正序获取第一个用户
        DevAccountDO devAccountDO = devAccountRepository.findOneByPhoneNumOrderByIdAsc(phoneNum);

        if (devAccountDO == null) {
            devAccountDO = devAccountEntity.createDevAccount(phoneNum);
            return getSocialLoginROResultRO(devAccountDO, true);
        }
        return getSocialLoginROResultRO(devAccountDO, false);

    }

    private ResultRO<SocialLoginRO<DevAccountRO>> getSocialLoginROResultRO(DevAccountDO devAccountDO, boolean isNewAccount) {
        //有用户返回，没有创建
//        String platform = loginVO.getPlatform();
        String devSecretKey = devAccountDO.getSecretKey();
        //生成userToken
        String userToken = SocialTokenUtil.generateTokenByUserKey(devSecretKey);
        userToken = devTokenRepository.save(new DevTokenDO(userToken, devAccountDO.getId())).getTokenCode();

        DevAccountRO devAccountRO = new DevAccountRO(devAccountDO);
        if (isNewAccount) {
            devAccountRO.setSecretKey(devAccountDO.getSecretKey());
        }
//        devAccountRO.setSecretKey(devAccountDO.getSecretKey());

        SocialLoginRO<DevAccountRO> socialLoginRO = new SocialLoginRO<>(userToken, devAccountRO);

        return ResultRO.success(socialLoginRO);
    }
}
