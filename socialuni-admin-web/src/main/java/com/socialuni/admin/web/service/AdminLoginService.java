package com.socialuni.admin.web.service;


import com.socialuni.admin.web.controller.DevAccountRO;
import com.socialuni.admin.web.manage.DevAccountEntity;
import com.socialuni.admin.web.manage.DevAuthCodeManage;
import com.socialuni.center.sdk.feignAPI.SocialuniDevAccountAPI;
import com.socialuni.center.sdk.model.DevAccountDO;
import com.socialuni.center.sdk.model.DevTokenDO;
import com.socialuni.center.sdk.model.QO.DevAccountQueryQO;
import com.socialuni.center.sdk.repository.DevAccountRepository;
import com.socialuni.center.sdk.repository.DevTokenRepository;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.exception.SocialBusinessException;
import com.socialuni.social.model.model.QO.user.SocialPhoneNumQO;
import com.socialuni.social.model.model.RO.user.login.SocialLoginRO;
import com.socialuni.social.utils.PhoneNumUtil;
import com.socialuni.social.web.sdk.utils.SocialTokenUtil;
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
    @Resource
    SocialuniDevAccountAPI socialuniDevAccountAPI;

    //秘钥登录
    @Transactional
    public ResultRO<SocialLoginRO<DevAccountRO>> secretKeyLogin(DevAccountQueryQO devAccountQueryQO) {
        ResultRO<DevAccountDO> resultRO = socialuniDevAccountAPI.queryDevAccount(devAccountQueryQO);
        DevAccountDO devAccountDO = resultRO.getData();
        if (devAccountDO == null) {
            throw new SocialBusinessException("秘钥错误");
        }
        //有用户返回，没有创建
//        String platform = loginVO.getPlatform();
        Integer userId = devAccountDO.getId();
        //生成userToken
        String userToken = SocialTokenUtil.generateTokenByUserId(userId);
        userToken = devTokenRepository.save(new DevTokenDO(userToken, userId)).getTokenCode();

        DevAccountRO devAccountRO = new DevAccountRO(devAccountDO);
//        devAccountRO.setSecretKey(devAccountDO.getSecretKey());

        SocialLoginRO<DevAccountRO> socialLoginRO = new SocialLoginRO<>(userToken, devAccountRO);

        return ResultRO.success(socialLoginRO);
    }


    @Transactional
    public ResultRO<SocialLoginRO<DevAccountRO>> phoneLogin(SocialPhoneNumQO socialPhoneNumQO) {
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
        }

        //有用户返回，没有创建
//        String platform = loginVO.getPlatform();
        Integer userId = devAccountDO.getId();
        //生成userToken
        String userToken = SocialTokenUtil.generateTokenByUserId(userId);
        userToken = devTokenRepository.save(new DevTokenDO(userToken, userId)).getTokenCode();

        DevAccountRO devAccountRO = new DevAccountRO(devAccountDO);
//        devAccountRO.setSecretKey(devAccountDO.getSecretKey());

        SocialLoginRO<DevAccountRO> socialLoginRO = new SocialLoginRO<>(userToken, devAccountRO);

        return ResultRO.success(socialLoginRO);
    }
}
