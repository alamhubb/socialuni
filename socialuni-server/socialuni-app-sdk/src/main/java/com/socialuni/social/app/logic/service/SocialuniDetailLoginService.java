package com.socialuni.social.app.logic.service;

import com.socialuni.social.app.factory.SocialuniMineUserDetailROFactory;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.user.SocialuniMineUserDetailRO;
import com.socialuni.social.common.api.model.user.SocialuniUserRO;
import com.socialuni.social.sdk.im.logic.entity.SocialuniChatEntity;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.user.sdk.logic.service.SocialuniLoginService;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.model.QO.SocialPhoneAuthCodePasswordQO;
import com.socialuni.social.user.sdk.model.QO.SocialPhoneNumPasswordQO;
import com.socialuni.social.user.sdk.model.QO.SocialPhoneNumAuthCodeQO;
import com.socialuni.social.user.sdk.model.QO.SocialProviderLoginQO;
import com.socialuni.social.user.sdk.model.RO.login.SocialLoginRO;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@Slf4j
public class SocialuniDetailLoginService {
    @Resource
    SocialuniLoginService socialuniLoginService;

    @Resource
    SocialuniChatEntity socialuniChatEntity;


    //提供给借用社交联盟实现微信qq渠道登录的开发者， 不需要支持社交联盟登录，社交联盟登录是前台跳转登录返回信息，不走后台
    @Transactional
    public ResultRO<SocialLoginRO<SocialuniMineUserDetailRO>> providerLogin(SocialProviderLoginQO loginQO) {

        ResultRO<SocialLoginRO<SocialuniUserRO>> socialLoginRO1 = socialuniLoginService.providerLogin(loginQO);

        Integer mineUserId = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(socialLoginRO1.getData().getUser().getId());

        SocialuniUserDo socialuniUserDo = SocialuniUserUtil.getAndCheckUserNotNull(mineUserId);

        SocialuniMineUserDetailRO socialuniMineUserDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(socialuniUserDo);

        SocialLoginRO<SocialuniMineUserDetailRO> socialLoginRO = new SocialLoginRO(socialLoginRO1.getData().getToken(), socialuniMineUserDetailRO);

        //用户加入
        socialuniChatEntity.createUserChats(socialuniUserDo);

        return ResultRO.success(socialLoginRO);
    }

    public ResultRO<SocialLoginRO<SocialuniMineUserDetailRO>> socialuniPhoneLogin(SocialProviderLoginQO loginData) {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull(loginData.getCode());

        SocialuniMineUserDetailRO centerMineUserDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(mineUser);

        SocialLoginRO<SocialuniMineUserDetailRO> socialLoginRO = new SocialLoginRO<>(loginData.getCode(), centerMineUserDetailRO);
        return ResultRO.success(socialLoginRO);
    }

    @Transactional
    public ResultRO<SocialLoginRO<SocialuniMineUserDetailRO>> phoneLogin(SocialPhoneNumAuthCodeQO socialPhoneNumQO) {
        ResultRO<SocialLoginRO<SocialuniUserRO>> socialLoginRO1 = socialuniLoginService.phoneLogin(socialPhoneNumQO);

        Integer mineUserId = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(socialLoginRO1.getData().getUser().getId());

        SocialuniUserDo socialuniUserDo = SocialuniUserUtil.getAndCheckUserNotNull(mineUserId);

        SocialuniMineUserDetailRO socialuniMineUserDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(socialuniUserDo);

        SocialLoginRO<SocialuniMineUserDetailRO> socialLoginRO = new SocialLoginRO(socialLoginRO1.getData().getToken(), socialuniMineUserDetailRO);

        //用户加入
        socialuniChatEntity.createUserChats(socialuniUserDo);
        return ResultRO.success(socialLoginRO);
    }

    @Transactional
    public ResultRO<SocialLoginRO<SocialuniMineUserDetailRO>> phonePasswordLogin(SocialPhoneAuthCodePasswordQO socialPhoneNumQO) {
        ResultRO<SocialLoginRO<SocialuniUserRO>> socialLoginRO1 = socialuniLoginService.phonePasswordLogin(socialPhoneNumQO);

        Integer mineUserId = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(socialLoginRO1.getData().getUser().getId());

        SocialuniUserDo socialuniUserDo = SocialuniUserUtil.getAndCheckUserNotNull(mineUserId);

        SocialuniMineUserDetailRO socialuniMineUserDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(socialuniUserDo);

        SocialLoginRO<SocialuniMineUserDetailRO> socialLoginRO = new SocialLoginRO(socialLoginRO1.getData().getToken(), socialuniMineUserDetailRO);

        //用户加入
        socialuniChatEntity.createUserChats(socialuniUserDo);
        return ResultRO.success(socialLoginRO);
    }

    public ResultRO<SocialLoginRO<SocialuniMineUserDetailRO>> passwordLogin(SocialPhoneNumPasswordQO socialPhoneNumQO) {
        ResultRO<SocialLoginRO<SocialuniUserRO>> socialLoginRO1 = socialuniLoginService.passwordLogin(socialPhoneNumQO);

        Integer mineUserId = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(socialLoginRO1.getData().getUser().getId());

        SocialuniUserDo socialuniUserDo = SocialuniUserUtil.getAndCheckUserNotNull(mineUserId);

        SocialuniMineUserDetailRO socialuniMineUserDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(socialuniUserDo);

        SocialLoginRO<SocialuniMineUserDetailRO> socialLoginRO = new SocialLoginRO(socialLoginRO1.getData().getToken(), socialuniMineUserDetailRO);

        //用户加入
        socialuniChatEntity.createUserChats(socialuniUserDo);
        return ResultRO.success(socialLoginRO);
    }
}
