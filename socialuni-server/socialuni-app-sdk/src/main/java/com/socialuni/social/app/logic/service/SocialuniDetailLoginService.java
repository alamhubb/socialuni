package com.socialuni.social.app.logic.service;

import com.socialuni.social.app.factory.SocialuniMineUserDetailROFactory;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.app.model.SocialuniMineUserDetailRO;
import com.socialuni.social.common.api.model.user.SocialuniUserRO;
import com.socialuni.social.common.api.utils.RequestUtil;
import com.socialuni.social.sdk.im.logic.entity.SocialuniChatEntity;
import com.socialuni.social.tance.dev.config.SocialuniDevConfig;
import com.socialuni.social.tance.dev.facade.DevAccountFacade;
import com.socialuni.social.tance.dev.facade.SocialuniUnionIdFacede;
import com.socialuni.social.tance.dev.util.SocialuniThirdTokenUtil;
import com.socialuni.social.user.sdk.api.user.SocialuniLoginAPI;
import com.socialuni.social.user.sdk.dao.DO.SocialuniTokenDO;
import com.socialuni.social.user.sdk.dao.utils.SocialuniUserExtendFriendLogDOUtil;
import com.socialuni.social.user.sdk.logic.manage.SocialuniTokenManage;
import com.socialuni.social.user.sdk.logic.service.SocialuniLoginService;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.model.QO.*;
import com.socialuni.social.user.sdk.model.RO.login.SocialLoginRO;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.web.sdk.config.SocialuniWebConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@Slf4j
public class SocialuniDetailLoginService {

    @Resource
    SocialuniTokenManage tokenManage;

    @Resource
    SocialuniLoginService socialuniLoginService;

    @Resource
    SocialuniChatEntity socialuniChatEntity;

    @Resource
    SocialuniLoginAPI socialuniLoginAPI;

    //提供给借用社交联盟实现微信qq渠道登录的开发者， 不需要支持社交联盟登录，社交联盟登录是前台跳转登录返回信息，不走后台
    @Transactional
    public ResultRO<SocialLoginRO<SocialuniUserRO>> providerLogin(SocialProviderLoginQO loginQO) {

        ResultRO<SocialLoginRO<SocialuniUserRO>> socialLoginRO1 = socialuniLoginService.providerLogin(loginQO);

        Long mineUserId = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(socialLoginRO1.getData().getUser().getId());

        SocialuniUserDo mineUser = SocialuniUserUtil.getUserNotNull(mineUserId);

        SocialuniMineUserDetailRO socialuniMineUserDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(mineUser);

        SocialLoginRO<SocialuniUserRO> socialLoginRO = new SocialLoginRO(socialLoginRO1.getData().getToken(), socialuniMineUserDetailRO);

        RequestUtil.setAttribute(SocialuniWebConfig.getTokenName(), socialLoginRO1.getData().getToken());
        //用户加入
        socialuniChatEntity.createUserChats(mineUser);

        //生成用户扩列记录
        SocialuniUserExtendFriendLogDOUtil.createUserExtendFriendLog();

        return ResultRO.success(socialLoginRO);
    }

    public ResultRO<SocialLoginRO<SocialuniMineUserDetailRO>> socialuniPhoneLogin(SocialProviderLoginQO loginData) {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull(loginData.getCode());

        SocialuniMineUserDetailRO centerMineUserDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(mineUser);

        SocialLoginRO<SocialuniMineUserDetailRO> socialLoginRO = new SocialLoginRO<>(loginData.getCode(), centerMineUserDetailRO);
        return ResultRO.success(socialLoginRO);
    }

    @Transactional
    public ResultRO<SocialLoginRO<SocialuniUserRO>> phoneLogin(SocialPhoneNumAuthCodeQO socialPhoneNumQO) {
        ResultRO<SocialLoginRO<SocialuniUserRO>> socialLoginRO1 = socialuniLoginService.phoneLogin(socialPhoneNumQO);

        Long mineUserId = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(socialLoginRO1.getData().getUser().getId());

        SocialuniUserDo socialuniUserDo = SocialuniUserUtil.getUserNotNull(mineUserId);

        SocialuniMineUserDetailRO socialuniMineUserDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(socialuniUserDo);

        SocialLoginRO<SocialuniUserRO> socialLoginRO = new SocialLoginRO(socialLoginRO1.getData().getToken(), socialuniMineUserDetailRO);

        RequestUtil.setAttribute(SocialuniWebConfig.getTokenName(), socialLoginRO1.getData().getToken());
        //用户加入
        socialuniChatEntity.createUserChats(socialuniUserDo);

        //生成用户扩列记录
        SocialuniUserExtendFriendLogDOUtil.createUserExtendFriendLog();
        return ResultRO.success(socialLoginRO);
    }

    @Transactional
    public ResultRO<SocialLoginRO<SocialuniUserRO>> phonePasswordLogin(SocialPhoneAuthCodePasswordQO socialPhoneNumQO) {
        ResultRO<SocialLoginRO<SocialuniUserRO>> socialLoginRO1 = socialuniLoginService.phonePasswordLogin(socialPhoneNumQO);

        Long mineUserId = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(socialLoginRO1.getData().getUser().getId());

        SocialuniUserDo socialuniUserDo = SocialuniUserUtil.getUserNotNull(mineUserId);

        SocialuniMineUserDetailRO socialuniMineUserDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(socialuniUserDo);

        SocialLoginRO<SocialuniUserRO> socialLoginRO = new SocialLoginRO(socialLoginRO1.getData().getToken(), socialuniMineUserDetailRO);

        RequestUtil.setAttribute(SocialuniWebConfig.getTokenName(), socialLoginRO1.getData().getToken());
        //用户加入
        socialuniChatEntity.createUserChats(socialuniUserDo);

        //生成用户扩列记录
        SocialuniUserExtendFriendLogDOUtil.createUserExtendFriendLog();
        return ResultRO.success(socialLoginRO);
    }

    public ResultRO<SocialLoginRO<SocialuniUserRO>> passwordLogin(SocialPhoneNumPasswordQO socialPhoneNumQO) {
        ResultRO<SocialLoginRO<SocialuniUserRO>> socialLoginRO1 = socialuniLoginService.passwordLogin(socialPhoneNumQO);

        Long mineUserId = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(socialLoginRO1.getData().getUser().getId());

        SocialuniUserDo socialuniUserDo = SocialuniUserUtil.getUserNotNull(mineUserId);

        SocialuniMineUserDetailRO socialuniMineUserDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(socialuniUserDo);

        SocialLoginRO<SocialuniUserRO> socialLoginRO = new SocialLoginRO(socialLoginRO1.getData().getToken(), socialuniMineUserDetailRO);

        RequestUtil.setAttribute(SocialuniWebConfig.getTokenName(), socialLoginRO1.getData().getToken());
        //用户加入
        socialuniChatEntity.createUserChats(socialuniUserDo);

        //生成用户扩列记录
        SocialuniUserExtendFriendLogDOUtil.createUserExtendFriendLog();
        return ResultRO.success(socialLoginRO);
    }


    public ResultRO<SocialLoginRO<SocialuniUserRO>> deviceUidLogin(SocialuniDeviceUidLoginQO socialuniDeviceUidLoginQO) {
        ResultRO<SocialLoginRO<SocialuniUserRO>> socialLoginRO1 = socialuniLoginService.deviceUidLogin(socialuniDeviceUidLoginQO, SocialuniUnionIdFacede.createUserUnionId());

        Long mineUserId = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(socialLoginRO1.getData().getUser().getId());

        SocialuniUserDo socialuniUserDo = SocialuniUserUtil.getUserNotNull(mineUserId);

        SocialuniMineUserDetailRO socialuniMineUserDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(socialuniUserDo);

        SocialLoginRO<SocialuniUserRO> socialLoginRO = new SocialLoginRO(socialLoginRO1.getData().getToken(), socialuniMineUserDetailRO);

        RequestUtil.setAttribute(SocialuniWebConfig.getTokenName(), socialLoginRO1.getData().getToken());

        //用户加入
        socialuniChatEntity.createUserChats(socialuniUserDo);

        //生成用户扩列记录
        SocialuniUserExtendFriendLogDOUtil.createUserExtendFriendLog();

        return ResultRO.success(socialLoginRO);
    }

    public ResultRO<SocialLoginRO<SocialuniUserRO>> refreshToken() {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();
        Long mineUserId = mineUser.getUserId();
        SocialuniTokenDO socialUserTokenDO = tokenManage.create(mineUser.getUnionId());
        if (SocialuniDevConfig.hasCenterServer()) {
            ResultRO<SocialLoginRO<SocialuniUserRO>> resultRO = socialuniLoginAPI.refreshToken();
            SocialuniUserRO socialuniUserRO = resultRO.getData().getUser();
            String token = resultRO.getData().getToken();
            Integer serverDevId = DevAccountFacade.getCenterDevIdNotNull();
            SocialuniThirdTokenUtil.updateUserToken(mineUserId, token, serverDevId, socialuniUserRO.getId());
        }

        SocialuniUserDo socialuniUserDo = SocialuniUserUtil.getUserNotNull(mineUserId);

        SocialuniMineUserDetailRO socialuniMineUserDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(socialuniUserDo);

        SocialLoginRO<SocialuniUserRO> socialLoginRO = new SocialLoginRO(socialUserTokenDO.getToken(), socialuniMineUserDetailRO);

        return ResultRO.success(socialLoginRO);
    }
}
