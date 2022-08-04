package com.socialuni.center.web.serive;

import com.socialuni.api.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.center.web.domain.thirdUser.AuthThirdUserDomain;
import com.socialuni.center.web.factory.RO.user.CenterMineUserDetailROFactory;
import com.socialuni.center.web.manage.ThirdUserTokenManage;
import com.socialuni.center.web.model.DO.UniContentUnionIdDO;
import com.socialuni.center.web.repository.UniContentUnionIdRepository;
import com.socialuni.center.web.utils.CenterTokenUtil;
import com.socialuni.center.web.utils.CenterUserUtil;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.constant.ContentType;
import com.socialuni.social.entity.model.DO.dev.DevAccountDO;
import com.socialuni.social.entity.model.DO.dev.ThirdUserTokenDO;
import com.socialuni.social.entity.model.DO.user.TokenDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.QO.user.SocialPhoneNumQO;
import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.model.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.social.model.model.RO.user.login.SocialLoginRO;
import com.socialuni.social.sdk.constant.AuthType;
import com.socialuni.social.sdk.domain.login.SocialProviderLoginDomain;
import com.socialuni.social.sdk.entity.user.SocialPhoneLoginEntity;
import com.socialuni.social.sdk.entity.user.SocialProviderLoginEntity;
import com.socialuni.social.sdk.factory.user.base.SocialMineUserDetailROFactory;
import com.socialuni.social.sdk.manage.TokenManage;
import com.socialuni.social.sdk.utils.DevAccountUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@Slf4j
public class CenterLoginService {
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
    UniContentUnionIdRepository uniContentUnionIdRepository;

    //提供给借用社交联盟实现微信qq渠道登录的开发者， 不需要支持社交联盟登录，社交联盟登录是前台跳转登录返回信息，不走后台
    /*@Transactional
    public ResultRO<SocialLoginRO<CenterMineUserDetailRO>> providerLogin(SocialProviderLoginQO loginQO) {
        // 只有清池支持渠道登录
        // 其他的只支持社交联盟登陆
        if (!UniappProviderType.values.contains(loginQO.getProvider())) {
            throw new SocialParamsException(UniappProviderType.notSupportTypeErrorMsg);
        }

        DevAccountDO devAccountDO = DevAccountUtils.getDevAccountNotNull();
        loginQO.setDevId(devAccountDO.getId());
        SocialLoginRO<SocialMineUserDetailRO> socialLoginRO = socialProviderLoginDomain.providerLogin(loginQO);

        UserDO mineUser = SocialUserUtil.get(socialLoginRO.getUser().getId());

        //中心授权
        SocialLoginRO<CenterMineUserDetailRO> centerLoginRO = authThirdUserDomain.thirdUserAuthLogin(mineUser, AuthType.user, devAccountDO, socialLoginRO.getUser());
        return ResultRO.success(centerLoginRO);
    }*/

    public ResultRO<SocialLoginRO<CenterMineUserDetailRO>> socialuniPhoneLogin(SocialProviderLoginQO loginData) {
        UserDO mineUser = CenterUserUtil.getMineUser(loginData.getCode());

        CenterMineUserDetailRO centerMineUserDetailRO = CenterMineUserDetailROFactory.getMineUserDetail(mineUser);

        SocialLoginRO<CenterMineUserDetailRO> socialLoginRO = new SocialLoginRO<>(loginData.getCode(), centerMineUserDetailRO);
        return ResultRO.success(socialLoginRO);
    }

    @Transactional
    public ResultRO<SocialLoginRO<CenterMineUserDetailRO>> phoneLogin(SocialPhoneNumQO socialPhoneNumQO) {
//        DevAccountDO devAccountDO = DevAccountUtils.getDevAccountNotNull();
        //todo 这接口有问题，应该拆开，手机号登陆不应该和三方登陆在一起
        //根据user获取返回结果
        UserDO mineUser = socialPhoneLoginEntity.phoneLogin(socialPhoneNumQO);

        UniContentUnionIdDO uniContentUnionIdDO = uniContentUnionIdRepository.findByDataDevIdAndContentTypeAndContentId(1, ContentType.user, mineUser.getId());
        if (uniContentUnionIdDO == null) {
            uniContentUnionIdDO = new UniContentUnionIdDO(1, ContentType.user, mineUser.getId());
            uniContentUnionIdDO = uniContentUnionIdRepository.save(uniContentUnionIdDO);
        }

        //向三方应用授权，不存在登录接口向三方授权的情况了
//        CenterMineUserDetailRO mineUserDetailRO = CenterMineUserDetailROFactory.getMineUserDetail(mineUser);
//        TokenDO socialUserTokenDO = tokenManage.create(mineUser.getId());
//        SocialLoginRO<CenterMineUserDetailRO> centerLoginRO = new SocialLoginRO<>(socialUserTokenDO.getToken(), mineUserDetailRO);
        //中心授权
//        SocialLoginRO<CenterMineUserDetailRO> centerLoginRO = authThirdUserDomain.thirdUserAuthLogin(mineUser, AuthType.phone, devAccountDO);

        //获取开发者对应的账号
        SocialMineUserDetailRO socialMineUserDetailRO = SocialMineUserDetailROFactory.getMineUserDetail(mineUser);

        CenterMineUserDetailRO centerUserDetailRO = CenterMineUserDetailROFactory.getMineUserDetail(socialMineUserDetailRO, mineUser);

        TokenDO tokenDO = tokenManage.create(socialMineUserDetailRO.getId());

        //生成返回对象
        SocialLoginRO<CenterMineUserDetailRO> applySocialUniOAuthRO = new SocialLoginRO<>();

        applySocialUniOAuthRO.setToken(tokenDO.getToken());
        applySocialUniOAuthRO.setUser(centerUserDetailRO);
        return ResultRO.success(applySocialUniOAuthRO);
    }

    /*
    旧版的包含三方授权模块
    @Transactional
    public ResultRO<SocialLoginRO<CenterMineUserDetailRO>>  phoneLogin(SocialPhoneNumQO socialPhoneNumQO) {
        DevAccountDO devAccountDO = DevAccountUtils.getDevAccountNotNull();
        //todo 这接口有问题，应该拆开，手机号登陆不应该和三方登陆在一起
        //根据user获取返回结果
        UserDO mineUser = socialPhoneLoginEntity.phoneLogin(socialPhoneNumQO);
        //向三方应用授权，不存在登录接口向三方授权的情况了
//        CenterMineUserDetailRO mineUserDetailRO = CenterMineUserDetailROFactory.getMineUserDetail(mineUser);
//        TokenDO socialUserTokenDO = tokenManage.create(mineUser.getId());
//        SocialLoginRO<CenterMineUserDetailRO> centerLoginRO = new SocialLoginRO<>(socialUserTokenDO.getToken(), mineUserDetailRO);
        //中心授权
        SocialLoginRO<CenterMineUserDetailRO> centerLoginRO = authThirdUserDomain.thirdUserAuthLogin(mineUser, AuthType.phone, devAccountDO);

            //获取开发者对应的账号
        SocialMineUserDetailRO socialMineUserDetailRO = SocialMineUserDetailROFactory.getMineUserDetail(mineUser);

        CenterMineUserDetailRO centerUserDetailRO = CenterMineUserDetailROFactory.getMineUserDetail(socialMineUserDetailRO, mineUser);

        ThirdUserTokenDO tokenDO = tokenManage.create(centerUserDetailRO.getId().toString(), devAccountDO.getId(), mineUser.getId());

        //生成返回对象
        SocialLoginRO<CenterMineUserDetailRO> applySocialUniOAuthRO = new SocialLoginRO<>();

        applySocialUniOAuthRO.setToken(tokenDO.getToken());
        applySocialUniOAuthRO.setUser(centerUserDetailRO);
        return ResultRO.success(centerLoginRO);
    }*/
}
