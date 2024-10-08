package com.socialuni.social.user.sdk.logic.domain;

import com.socialuni.social.common.api.constant.SocialuniLoginType;
import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.api.exception.exception.SocialSystemException;
import com.socialuni.social.common.api.model.user.SocialuniUserShowRO;
import com.socialuni.social.common.sdk.dao.DO.SocialuniDeviceDO;
import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.common.sdk.dao.facede.SocialuniUserRepositoryFacede;
import com.socialuni.social.common.sdk.event.ddd.EventPublisherFacade;
import com.socialuni.social.user.sdk.dao.DO.SocialUserPasswordDO;
import com.socialuni.social.user.sdk.dao.DO.SocialUserPhoneDo;
import com.socialuni.social.user.sdk.logic.entity.SocialPhoneLoginEntity;
import com.socialuni.social.user.sdk.logic.entity.SocialProviderLoginEntity;
import com.socialuni.social.user.sdk.logic.entity.SocialuniCreateLoginHisotryEntity;
import com.socialuni.social.user.sdk.logic.manage.SocialUserManage;
import com.socialuni.social.user.sdk.logic.manage.SocialUserPasswordManage;
import com.socialuni.social.user.sdk.logic.manage.SocialUserPhoneManage;
import com.socialuni.social.user.sdk.logic.manage.SocialuniTokenManage;
import com.socialuni.social.user.sdk.dao.DO.SocialuniTokenDO;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.model.QO.*;
import com.socialuni.social.user.sdk.model.RO.login.SocialLoginRO;
import com.socialuni.social.user.sdk.model.factory.SocialuniMineUserROFactory;
import com.socialuni.social.user.sdk.dao.repository.SocialuniUserPasswordRepository;
import com.socialuni.social.user.sdk.utils.PasswordUtil;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;

@Service
public class SocialuniLoginDomain {
    @Resource
    SocialuniTokenManage tokenManage;
    @Resource
    SocialPhoneLoginEntity socialPhoneLoginEntity;

    @Resource
    SocialProviderLoginEntity socialProviderLoginEntity;
    @Resource
    SocialUserManage socialUserManage;


    @Transactional
    public SocialLoginRO<SocialuniUserShowRO> providerLogin(SocialProviderLoginQO loginQO) {
        //创建或返回
        SocialuniUserDo mineUser = socialProviderLoginEntity.providerLogin(loginQO);

        return getSocialLoginROByMineUser(mineUser, SocialuniLoginType.provider);
    }


    public SocialLoginRO<SocialuniUserShowRO> deviceUidLogin(SocialuniDeviceUidLoginQO socialuniDeviceUidLoginQO, Long unionId) {
        String deviceUid = socialuniDeviceUidLoginQO.getDeviceUid();

        if (StringUtils.isEmpty(deviceUid)) {
            throw new SocialParamsException("临时用户登录错误");
        }
        SocialuniDeviceDO socialuniDeviceDO = SocialuniRepositoryFacade.findByCustomField("uuid", deviceUid, SocialuniDeviceDO.class);

        if (socialuniDeviceDO == null) {
            throw new SocialParamsException("临时用户登录错误103256");
        }

        //创建或返回
        SocialuniUserDo mineUser = socialUserManage.createUser(unionId);

        return getSocialLoginROByMineUser(mineUser, SocialuniLoginType.device);
    }

    @Transactional
    public SocialLoginRO<SocialuniUserShowRO> phoneLogin(SocialPhoneNumAuthCodeQO socialPhoneNumQO) {
        SocialuniUserDo mineUser = socialPhoneLoginEntity.phoneLogin(socialPhoneNumQO);

        return getSocialLoginROByMineUser(mineUser, SocialuniLoginType.phone);
    }

    @Resource
    SocialUserPhoneManage socialUserPhoneManage;
    @Resource
    SocialuniUserPasswordRepository socialuniUserPasswordRepository;

    public SocialLoginRO<SocialuniUserShowRO> passwordLogin(SocialPhoneNumPasswordQO socialPhoneNumQO) {
        String phoneNum = socialPhoneNumQO.getPhoneNum();

        SocialUserPhoneDo socialUserPhoneDo = socialUserPhoneManage.checkLoginPhoneNumAndGetUser(phoneNum);

        if (socialUserPhoneDo == null) {
            throw new SocialSystemException("手机号未注册，却执行了密码登录");
        }

        Long userId = socialUserPhoneDo.getUserId();

        SocialUserPasswordDO socialUserPasswordDO = SocialuniUserRepositoryFacede.findFirstByUserIdOrderByIdDesc(userId, SocialUserPasswordDO.class);

        if (socialUserPasswordDO == null) {
            throw new SocialSystemException("未设置密码，却使用了密码登录");
        }

        Integer errorNum = socialUserPasswordDO.getErrorNum();

        Date lastErrorTime = socialUserPasswordDO.getLastErrorTime();

        long time = lastErrorTime.getTime();

        long now = System.currentTimeMillis();

        long threeHours = 3 * 60 * 60 * 1000;

        boolean diffThreeHours = now - time < threeHours;

        if (errorNum >= 3) {
            if (diffThreeHours) {
                throw new SocialBusinessException("密码错误次数过多，请三小时后再试");
            }
        }

        String cryptoPassword = socialPhoneNumQO.getPassword();

        //检查密码
        String password = PasswordUtil.check(cryptoPassword);

        String sha512Password = PasswordUtil.sha512Encode(password);

        String dbPassword = socialUserPasswordDO.getPassword();

        socialUserPasswordDO.setLastErrorTime(new Date());

        if (!sha512Password.equals(dbPassword)) {
            if (diffThreeHours) {
                socialUserPasswordDO.setErrorNum(errorNum + 1);
            } else {
                socialUserPasswordDO.setErrorNum(1);
            }
            socialUserPasswordDO = SocialuniUserRepositoryFacede.save(socialUserPasswordDO);
            throw new SocialBusinessException("密码错误");
        }
        //一致，则登录成功，将错误次数设为0
        socialUserPasswordDO.setErrorNum(0);
        socialUserPasswordDO = SocialuniUserRepositoryFacede.save(socialUserPasswordDO);

        SocialuniUserDo mineUser = SocialuniUserUtil.getUserNotNull(userId);

        return getSocialLoginROByMineUser(mineUser, SocialuniLoginType.password);
    }

    @Resource
    SocialUserPasswordManage socialUserPasswordManage;

    @Transactional
    public SocialLoginRO<SocialuniUserShowRO> phonePasswordLogin(SocialPhoneAuthCodePasswordQO socialPhoneNumQO) {
        String cryptoPassword = socialPhoneNumQO.getPassword();

        //检查密码
        String password = PasswordUtil.check(cryptoPassword);

        //校验用户没有则创建
        SocialuniUserDo mineUser = socialPhoneLoginEntity.phoneLogin(socialPhoneNumQO);

        Long userId = mineUser.getUserId();

        String sha512Password = PasswordUtil.sha512Encode(password);

        SocialUserPasswordDO socialUserPasswordDO = socialUserPasswordManage.getOrCreateUserPasswordDO(userId, socialPhoneNumQO.getPhoneNum(), sha512Password);

        socialUserPasswordDO = socialUserPasswordManage.updatePassword(sha512Password, socialUserPasswordDO);

        return getSocialLoginROByMineUser(mineUser, SocialuniLoginType.phonePassword);
    }


    public SocialLoginRO<SocialuniUserShowRO> getSocialLoginROByMineUser(SocialuniUserDo mineUser, String loginType) {
        SocialuniUserShowRO userDetailRO = SocialuniMineUserROFactory.getMineUser(mineUser);

        SocialuniTokenDO socialUserTokenDO = tokenManage.create(mineUser.getUnionId());


        SocialuniCreateLoginHisotryEntity.createUserLoginHistory(mineUser.getUserId(), loginType);


        EventPublisherFacade.publishEvent("userLogin", mineUser);

        return new SocialLoginRO<>(socialUserTokenDO.getToken(), userDetailRO);
    }
}
