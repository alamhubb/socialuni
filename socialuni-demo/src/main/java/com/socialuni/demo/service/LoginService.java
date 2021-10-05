package com.socialuni.demo.service;


import com.socialuni.api.feignAPI.SocialuniOAuthAPI;
import com.socialuni.demo.model.MineUserDetailRO;
import com.socialuni.demo.utils.UserUtil;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.entity.model.DO.user.SocialUserPhoneDO;
import com.socialuni.social.entity.model.DO.user.TokenDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.exception.SocialBusinessException;
import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.model.model.RO.OAuthGetUserPhoneNumRO;
import com.socialuni.social.model.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.social.model.model.RO.user.login.SocialLoginRO;
import com.socialuni.social.sdk.entity.user.SocialProviderLoginEntity;
import com.socialuni.social.sdk.entity.user.SocialUserPhoneEntity;
import com.socialuni.social.sdk.factory.user.base.SocialMineUserDetailROFactory;
import com.socialuni.social.sdk.manage.TokenManage;
import com.socialuni.social.sdk.manage.phone.SocialUserPhoneManage;
import com.socialuni.social.sdk.service.SocialLoginService;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import com.socialuni.social.web.sdk.utils.SocialTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 11:09
 * 前端初始化内容
 */
@Service
public class LoginService {
    @Resource
    private SocialLoginService socialLoginService;
    @Resource
    private SocialuniOAuthAPI socialuniOAuthAPI;
    @Resource
    private SocialUserPhoneEntity socialUserPhoneEntity;
    @Resource
    private SocialUserPhoneManage socialUserPhoneManage;
    @Resource
    private TokenManage tokenManage;
    @Resource
    SocialProviderLoginEntity socialProviderLoginEntity;

    public ResultRO<SocialLoginRO<MineUserDetailRO>> socialuniPhoneLogin(SocialProviderLoginQO loginData) {
        UserDO mineUser = socialProviderLoginEntity.providerLogin(loginData);
        Integer mineUserId = mineUser.getId();

        SocialUserPhoneDO socialUserPhoneDO = SocialUserUtil.getUserPhoneDO(mineUserId);
        //如果用户手机号为空
        if (socialUserPhoneDO == null) {

            //查询用户是否授权了手机号，授权的话直接绑定手机号
            ResultRO<OAuthGetUserPhoneNumRO> phoneNumROResultRO = socialuniOAuthAPI.oAuthGetUserPhoneNum();
            OAuthGetUserPhoneNumRO OAuthGetUserPhoneNumRO = phoneNumROResultRO.getData();
            String phoneNum = OAuthGetUserPhoneNumRO.getPhoneNum();
            //如果手机号不为空
            if (StringUtils.isNotEmpty(phoneNum)) {
                //校验手机号格式
                socialUserPhoneManage.checkBindPhoneNum(phoneNum, mineUserId);
                socialUserPhoneDO = socialUserPhoneManage.createUserPhoneNum(mineUserId, "86", phoneNum);
            }
        }
        //如果为社区联盟登陆则需要模拟设置token
        SocialTokenUtil.setSocialuniToken(loginData.getCode());

        //创建或返回
        SocialMineUserDetailRO userDetailRO = SocialMineUserDetailROFactory.getMineUserDetail(mineUser, socialUserPhoneDO);

        TokenDO socialUserTokenDO = tokenManage.create(mineUserId);

        MineUserDetailRO mineUserDetailRO = UserUtil.getMineUser(userDetailRO);

        SocialLoginRO<MineUserDetailRO> socialLoginRO = new SocialLoginRO<>(socialUserTokenDO.getToken(), mineUserDetailRO);
        return ResultRO.success(socialLoginRO);
    }

    public ResultRO<SocialLoginRO<MineUserDetailRO>> providerLogin(SocialProviderLoginQO loginData) {
        ResultRO<SocialLoginRO<SocialMineUserDetailRO>> resultRO = socialLoginService.providerLogin(loginData);
        SocialLoginRO<SocialMineUserDetailRO> loginRO = resultRO.getData();
        SocialMineUserDetailRO socialMineUserDetailRO = loginRO.getUser();

        MineUserDetailRO mineUserDetailRO = UserUtil.getMineUser(socialMineUserDetailRO);

        SocialLoginRO<MineUserDetailRO> socialLoginRO = new SocialLoginRO<>(loginRO.getToken(), mineUserDetailRO);
        return ResultRO.success(socialLoginRO);
    }
}
