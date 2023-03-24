package com.socialuni.social.user.sdk.logic.service;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.user.SocialuniUserRO;
import com.socialuni.social.report.sdk.constant.SocialuniSupportProviderType;
import com.socialuni.social.user.sdk.logic.domain.SocialuniLoginDomain;
import com.socialuni.social.user.sdk.model.QO.SocialPhoneNumQO;
import com.socialuni.social.user.sdk.model.QO.SocialProviderLoginQO;
import com.socialuni.social.user.sdk.model.RO.login.SocialLoginRO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@Slf4j
public class SocialuniLoginService {
    @Resource
    SocialuniLoginDomain socialPhoneLoginDomain;

    //提供给借用社交联盟实现微信qq渠道登录的开发者， 不需要支持社交联盟登录，社交联盟登录是前台跳转登录返回信息，不走后台
    @Transactional
    public ResultRO<SocialLoginRO<SocialuniUserRO>> providerLogin(SocialProviderLoginQO loginQO) {
        // 只有清池支持渠道登录
        // 其他的只支持社交联盟登陆
        SocialuniSupportProviderType.checkSupportType(loginQO.getProvider());
        SocialLoginRO<SocialuniUserRO> socialLoginRO = socialPhoneLoginDomain.providerLogin(loginQO);
        return ResultRO.success(socialLoginRO);
    }

    @Transactional
    public ResultRO<SocialLoginRO<SocialuniUserRO>> phoneLogin(SocialPhoneNumQO socialPhoneNumQO) {
        SocialLoginRO<SocialuniUserRO> socialLoginRO = socialPhoneLoginDomain.phoneLogin(socialPhoneNumQO);
//        CompletableFuture.supplyAsync(() -> socialuniOpenImgUserFeign.userLogin(SocialuniMineUserDetailROFactory.toImUserModel(user)));
        return ResultRO.success(socialLoginRO);
    }

    /*
    旧版的包含三方授权模块
    @Transactional
    public ResultRO<SocialLoginRO<SocialuniMineUserDetailRO>>  phoneLogin(SocialPhoneNumQO socialPhoneNumQO) {
        DevAccountDO devAccountDO = DevAccountUtils.getDevAccountNotNull();
        //todo 这接口有问题，应该拆开，手机号登陆不应该和三方登陆在一起
        //根据user获取返回结果
        UserDO mineUser = socialPhoneLoginEntity.phoneLogin(socialPhoneNumQO);
        //向三方应用授权，不存在登录接口向三方授权的情况了
//        SocialuniMineUserDetailRO mineUserDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(mineUser);
//        TokenDO socialUserTokenDO = tokenManage.create(mineUser.getId());
//        SocialLoginRO<SocialuniMineUserDetailRO> centerLoginRO = new SocialLoginRO<>(socialUserTokenDO.getToken(), mineUserDetailRO);
        //中心授权
        SocialLoginRO<SocialuniMineUserDetailRO> centerLoginRO = authThirdUserDomain.thirdUserAuthLogin(mineUser, AuthType.phone, devAccountDO);

            //获取开发者对应的账号
        SocialMineUserDetailRO socialMineUserDetailRO = SocialMineUserDetailROFactory.getMineUserDetail(mineUser);

        SocialuniMineUserDetailRO centerUserDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(socialMineUserDetailRO, mineUser);

        ThirdUserTokenDO tokenDO = tokenManage.create(centerUserDetailRO.getId().toString(), devAccountDO.getId(), mineUser.getId());

        //生成返回对象
        SocialLoginRO<SocialuniMineUserDetailRO> applySocialUniOAuthRO = new SocialLoginRO<>();

        applySocialUniOAuthRO.setToken(tokenDO.getToken());
        applySocialUniOAuthRO.setUser(centerUserDetailRO);
        return ResultRO.success(centerLoginRO);
    }*/
}
