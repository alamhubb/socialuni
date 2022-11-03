package com.socialuni.social.sdk.logic.service.login;

import com.socialuni.social.common.sdk.exception.exception.SocialParamsException;
import com.socialuni.social.common.sdk.model.ResultRO;
import com.socialuni.social.sdk.constant.platform.UniappProviderType;
import com.socialuni.social.sdk.logic.domain.login.SocialPhoneLoginDomain;
import com.socialuni.social.sdk.logic.domain.login.SocialProviderLoginDomain;
import com.socialuni.social.sdk.logic.domain.thirdUser.AuthThirdUserDomain;
import com.socialuni.social.sdk.logic.entity.user.SocialPhoneLoginEntity;
import com.socialuni.social.sdk.logic.entity.user.SocialProviderLoginEntity;
import com.socialuni.social.sdk.logic.factory.RO.user.SocialuniMineUserDetailROFactory;
import com.socialuni.social.sdk.logic.manage.ThirdUserTokenManage;
import com.socialuni.social.sdk.logic.manage.TokenManage;
import com.socialuni.social.sdk.model.QO.user.SocialPhoneNumQO;
import com.socialuni.social.sdk.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.sdk.model.RO.user.SocialuniMineUserDetailRO;
import com.socialuni.social.sdk.model.RO.user.login.SocialLoginRO;
import com.socialuni.social.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.tance.sdk.api.SocialuniUnionIdInterface;
import com.socialuni.social.user.sdk.entity.SocialuniUserDo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@Slf4j
public class SocialuniLoginService {
    @Resource
    private AuthThirdUserDomain authThirdUserDomain;
    @Resource
    private SocialProviderLoginDomain socialProviderLoginDomain;
    @Resource
    private SocialProviderLoginEntity socialProviderLoginEntity;
    @Resource
    private SocialPhoneLoginEntity socialPhoneLoginEntity;
    @Resource
    TokenManage tokenManage;
    @Resource
    ThirdUserTokenManage thirdUserTokenManage;
    @Resource
    SocialuniUnionIdInterface uniContentUnionIdRepository;
    @Resource
    SocialLoginService socialLoginService;
    @Resource
    SocialPhoneLoginDomain socialPhoneLoginDomain;


    //提供给借用社交联盟实现微信qq渠道登录的开发者， 不需要支持社交联盟登录，社交联盟登录是前台跳转登录返回信息，不走后台
    @Transactional
    public ResultRO<SocialLoginRO<SocialuniMineUserDetailRO>> providerLogin(SocialProviderLoginQO loginQO) {
        // 只有清池支持渠道登录
        // 其他的只支持社交联盟登陆
        if (!UniappProviderType.values.contains(loginQO.getProvider())) {
            throw new SocialParamsException(UniappProviderType.notSupportTypeErrorMsg);
        }
        SocialLoginRO<SocialuniMineUserDetailRO> socialLoginRO = socialProviderLoginDomain.providerLogin(loginQO);

        return ResultRO.success(socialLoginRO);
    }

    public ResultRO<SocialLoginRO<SocialuniMineUserDetailRO>> socialuniPhoneLogin(SocialProviderLoginQO loginData) {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull(loginData.getCode());

        SocialuniMineUserDetailRO centerMineUserDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(mineUser);

        SocialLoginRO<SocialuniMineUserDetailRO> socialLoginRO = new SocialLoginRO<>(loginData.getCode(), centerMineUserDetailRO);
        return ResultRO.success(socialLoginRO);
    }

    @Transactional
    public ResultRO<SocialLoginRO<SocialuniMineUserDetailRO>> phoneLogin(SocialPhoneNumQO socialPhoneNumQO) {
        SocialLoginRO<SocialuniMineUserDetailRO> socialLoginRO = socialPhoneLoginDomain.phoneLogin(socialPhoneNumQO);
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
