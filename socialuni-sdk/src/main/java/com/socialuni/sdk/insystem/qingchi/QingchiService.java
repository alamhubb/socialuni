package com.socialuni.sdk.insystem.qingchi;

import com.socialuni.sdk.model.RO.devAccount.DevAccountRO;
import com.socialuni.sdk.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.sdk.model.DO.dev.DevAccountDO;
import com.socialuni.sdk.model.DO.dev.DevAccountProviderDO;
import com.socialuni.sdk.repository.dev.DevAccountProviderRepository;
import com.socialuni.sdk.utils.DevAccountUtils;
import com.socialuni.sdk.domain.thirdUser.AuthThirdUserDomain;
import com.socialuni.sdk.entity.AuthThirdUserEntity;
import com.socialuni.sdk.manage.DevAccountManage;
import com.socialuni.sdk.utils.CenterUserUtil;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.model.QO.user.OAuthUserInfoQO;
import com.socialuni.sdk.model.QO.user.SocialPhoneNumQO;
import com.socialuni.sdk.model.QO.user.SocialProviderLoginQO;
import com.socialuni.sdk.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.exception.SocialParamsException;
import com.socialuni.sdk.model.QO.SocialBindWxPhoneNumQO;
import com.socialuni.sdk.model.RO.user.login.SocialLoginRO;
import com.socialuni.sdk.model.RO.user.phone.SocialSendAuthCodeQO;
import com.socialuni.sdk.constant.AuthType;
import com.socialuni.sdk.domain.login.SocialProviderLoginDomain;
import com.socialuni.sdk.domain.phone.SocailSendAuthCodeDomain;
import com.socialuni.sdk.domain.phone.SocialBindPhoneNumDomain;
import com.socialuni.sdk.domain.phone.SocialBindWxPhoneNumDomain;
import com.socialuni.sdk.entity.user.SocialPhoneLoginEntity;
import com.socialuni.sdk.entity.user.SocialProviderLoginEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import javax.validation.Valid;

@Slf4j
@Service
public class QingchiService {
    @Resource
    private AuthThirdUserEntity authThirdUserEntity;
    @Resource
    private SocialBindWxPhoneNumDomain socialBindWxPhoneNumDomain;
    @Resource
    private SocialBindPhoneNumDomain socialBindPhoneNumDomain;
    @Resource
    private SocailSendAuthCodeDomain socailSendAuthCodeDomain;
    @Resource
    private SocialPhoneLoginEntity socialPhoneLoginEntity;
    @Resource
    private AuthThirdUserDomain authThirdUserDomain;
    @Resource
    private SocialProviderLoginDomain socialProviderLoginDomain;
    @Resource
    private DevAccountManage devAccountManage;
    @Resource
    private DevAccountProviderRepository devAccountProviderRepository;
    @Resource
    SocialProviderLoginEntity socialProviderLoginEntity;

    private DevAccountDO checkIsQingchiApp() {
        DevAccountDO devAccountDO = DevAccountUtils.getDevAccountNotNull();
        // 只有清池支持渠道登录
        if (!devAccountDO.getId().equals(1)) {
            // 其他的只支持社交联盟登陆
            throw new SocialParamsException("未授权");
        }
        return devAccountDO;
    }

    //因旧版本原因合在了一起，新版本要拆开
    @Transactional
    public ResultRO<SocialLoginRO<CenterMineUserDetailRO>> providerLogin(SocialProviderLoginQO loginQO) {
        DevAccountDO devAccountDO = this.checkIsQingchiApp();
//        loginQO.setDevId(devAccountDO.getId());

        //创建或返回
        SocialUserDO mineUser = socialProviderLoginEntity.providerLogin(loginQO);

        //中心授权
        SocialLoginRO<CenterMineUserDetailRO> centerLoginRO = authThirdUserDomain.thirdUserAuthLogin(mineUser, AuthType.user, devAccountDO);
        return ResultRO.success(centerLoginRO);
    }

    @Transactional
    public ResultRO<SocialLoginRO<CenterMineUserDetailRO>>  phoneLogin(SocialPhoneNumQO socialPhoneNumQO) {
        DevAccountDO devAccountDO = this.checkIsQingchiApp();
        //todo 这接口有问题，应该拆开，手机号登陆不应该和三方登陆在一起
        //根据user获取返回结果
        SocialUserDO mineUser = socialPhoneLoginEntity.phoneLogin(socialPhoneNumQO);
        //中心授权
        SocialLoginRO<CenterMineUserDetailRO> centerLoginRO = authThirdUserDomain.thirdUserAuthLogin(mineUser, AuthType.phone, devAccountDO);
        return ResultRO.success(centerLoginRO);
    }

    public ResultRO<CenterMineUserDetailRO> bindPhoneNum(SocialPhoneNumQO socialPhoneNumQO) {
        DevAccountDO devAccountDO = this.checkIsQingchiApp();
        SocialUserDO mineUser = CenterUserUtil.getMineUserNotNull();
        SocialMineUserDetailRO socialMineUserDetailRO = socialBindPhoneNumDomain.bindPhoneNum(socialPhoneNumQO, mineUser);
        //只是记录一个授权记录
        //生成一条对三方的手机号授权，并且返回手机号信息
        CenterMineUserDetailRO centerMineUserDetailRO = authThirdUserEntity.authThirdUser(mineUser, AuthType.phone, devAccountDO, socialMineUserDetailRO);
        return new ResultRO<>(centerMineUserDetailRO);
    }

    //微信绑定手机号方法
    public ResultRO<CenterMineUserDetailRO> bindWxPhoneNum(@Valid SocialBindWxPhoneNumQO bindPhoneQO) {
        // 只有清池支持渠道登录
        DevAccountDO devAccountDO = this.checkIsQingchiApp();
        SocialUserDO mineUser = CenterUserUtil.getMineUserNotNull();
        SocialMineUserDetailRO socialMineUserDetailRO = socialBindWxPhoneNumDomain.bindWxPhoneNum(bindPhoneQO, mineUser);

        //生成一条对三方的手机号授权，并且返回手机号信息
        CenterMineUserDetailRO centerMineUserDetailRO = authThirdUserEntity.authThirdUser(mineUser, AuthType.phone, devAccountDO, socialMineUserDetailRO);
        //授权成功
        //token有效期三个月
        //生成userToken
        //只是记录一个授权记录
        //授权成功
        //token有效期三个月
        //生成userToken
        return ResultRO.success(centerMineUserDetailRO);
    }

    public ResultRO<Void> sendAuthCode(SocialSendAuthCodeQO authCodeQO) {
        this.checkIsQingchiApp();
        SocialUserDO mineUser = CenterUserUtil.getMineUserAllowNull();
        //校验逻辑应该拿到 domain里，因为限制了只有清池可以访问，所以不再限制ip
        return socailSendAuthCodeDomain.sendAuthCode(authCodeQO, mineUser);
    }


    public ResultRO<DevAccountRO> queryDevAccount(OAuthUserInfoQO devAccountQueryQO) {
        this.checkIsQingchiApp();
        DevAccountProviderDO devAccountProviderDO = devAccountProviderRepository.findOneByAppIdAndMpType(devAccountQueryQO.getAppId(), devAccountQueryQO.getMpType());
        DevAccountRO devAccountRO = new DevAccountRO(devAccountProviderDO.getAppName());
        return ResultRO.success(devAccountRO);
    }
}