package com.socialuni.social.admin.service;


import com.socialuni.social.admin.controller.DevAccountRO;
import com.socialuni.social.admin.manage.DevAuthCodeManage;
import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.utils.SocialTokenFacade;
import com.socialuni.social.tance.entity.DevAccountEntity;
import com.socialuni.social.tance.sdk.api.DevAccountInterface;
import com.socialuni.social.tance.sdk.api.DevAccountRedisInterface;
import com.socialuni.social.tance.sdk.api.DevTokenInterface;
import com.socialuni.social.tance.sdk.model.DevAccountModel;
import com.socialuni.social.tance.sdk.model.DevTokenModler;
import com.socialuni.social.user.sdk.logic.entity.SocialUserPhoneEntity;
import com.socialuni.social.user.sdk.model.QO.SocialPhoneNumQO;
import com.socialuni.social.user.sdk.model.RO.login.SocialLoginRO;
import com.socialuni.social.user.sdk.logic.redis.SocialUserPhoneRedis;
import com.socialuni.social.user.sdk.utils.PhoneNumUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
public class AdminLoginService {
    @Resource
    DevAuthCodeManage devAuthCodeManage;
    @Resource
    DevAccountInterface devAccountApi;
    @Resource
    DevAccountEntity devAccountEntity;
    @Resource
    DevTokenInterface devTokenApi;
    @Resource
    SocialUserPhoneEntity socialUserPhoneEntity;
    @Resource
    private DevAccountRedisInterface devAccountRedis;
    @Resource
    private SocialUserPhoneRedis socialUserPhoneRedis;

    //秘钥登录
    @Transactional
    public ResultRO<SocialLoginRO<DevAccountRO>> secretKeyLogin(DevAccountInterface.DevAccountQueryQO devAccountQueryQO) {
        DevAccountModel devAccountModel = devAccountApi.findOneBySecretKey(devAccountQueryQO.getSecretKey());
        if (devAccountModel == null) {
            throw new SocialBusinessException("秘钥错误");
        }
        //有用户返回，没有创建
//        String platform = loginVO.getPlatform();
        return getSocialLoginROResultRO(devAccountModel, false);
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
        userToken = devTokenApi.savePut(new DevTokenModler(userToken, devAccountModel.getId())).getTokenCode();

        DevAccountRO devAccountRO = new DevAccountRO(devAccountModel);
        if (isNewAccount) {
            devAccountRO.setSecretKey(devAccountModel.getSecretKey());
        }
//        devAccountRO.setSecretKey(devAccountDO.getSecretKey());

        SocialLoginRO<DevAccountRO> socialLoginRO = new SocialLoginRO<>(userToken, devAccountRO);

        return ResultRO.success(socialLoginRO);
    }
}
