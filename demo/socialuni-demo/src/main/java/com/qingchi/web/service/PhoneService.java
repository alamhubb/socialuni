package com.qingchi.web.service;

import com.qingchi.web.factory.MineUserDetailROFactory;
import com.qingchi.web.model.RO.user.MineUserDetailRO;
import com.socialuni.api.feignAPI.insystem.SocialuniQingchiAPI;
import com.socialuni.api.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.constant.StatusConst;
import com.socialuni.social.entity.model.DO.AuthenticationDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.exception.SocialBusinessException;
import com.socialuni.social.model.model.QO.SocialBindWxPhoneNumQO;
import com.socialuni.social.model.model.QO.user.SocialPhoneNumQO;
import com.socialuni.social.model.model.RO.user.phone.SocialSendAuthCodeQO;
import com.socialuni.social.sdk.constant.AppConfigConst;
import com.socialuni.social.sdk.constant.ErrorMsg;
import com.socialuni.social.sdk.entity.user.SocialUserPhoneEntity;
import com.socialuni.social.sdk.repository.AuthenticationRepository;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import com.socialuni.social.web.sdk.utils.IpUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PhoneService {
    @Resource
    SocialuniQingchiAPI socialuniQingchiAPI;
    @Resource
    private SocialUserPhoneEntity socialUserPhoneEntity;
    @Resource
    private AuthenticationRepository authRepository;

    public ResultRO<Void> sendAuthCode(SocialSendAuthCodeQO socialSendAuthCodeQO) {
        Integer ipLimitCount = (Integer) AppConfigConst.appConfigMap.get(AppConfigConst.authCodeIpCountKey);
        if (ipLimitCount == null || ipLimitCount == 0) {
            ipLimitCount = 50;
        }
        String userIp = IpUtil.getIpAddr();
        Integer ipCount = authRepository.countByIp(userIp);
        if (ipCount >= ipLimitCount) {
            throw new SocialBusinessException("获取验证码次数已达到上线，" + ErrorMsg.CONTACT_SERVICE);
        }
        AuthenticationDO authenticationDO = new AuthenticationDO(SocialUserUtil.getMineUserIdAllowNull(), socialSendAuthCodeQO.getPhoneNum(), null, userIp);
        authenticationDO.setStatus(StatusConst.success);
        authRepository.save(authenticationDO);

        return socialuniQingchiAPI.sendAuthCode(socialSendAuthCodeQO);
    }

    public ResultRO<MineUserDetailRO> bindWxPhoneNum(SocialBindWxPhoneNumQO bindWxPhoneNumQO) {
        UserDO mineUser = SocialUserUtil.getMineUserNotNull();
        //微信绑定手机号方法
        ResultRO<CenterMineUserDetailRO> resultRO = socialuniQingchiAPI.bindWxPhoneNum(bindWxPhoneNumQO);
        return getMineUserDetailROResultRO(mineUser, resultRO);
    }

    public ResultRO<MineUserDetailRO> bindPhoneNum(SocialPhoneNumQO bindWxPhoneNumQO) {
        UserDO mineUser = SocialUserUtil.getMineUserNotNull();
        //微信绑定手机号方法
        ResultRO<CenterMineUserDetailRO> resultRO = socialuniQingchiAPI.bindPhoneNum(bindWxPhoneNumQO);
        return getMineUserDetailROResultRO(mineUser, resultRO);
    }


    private ResultRO<MineUserDetailRO> getMineUserDetailROResultRO(UserDO mineUser, ResultRO<CenterMineUserDetailRO> resultRO) {
        CenterMineUserDetailRO centerMineUserDetailRO = resultRO.getData();

        mineUser = socialUserPhoneEntity.checkPhoneNumAndCreateBind(mineUser, "86", centerMineUserDetailRO.getPhoneNum());

        MineUserDetailRO mineUserDetailRO = MineUserDetailROFactory.getMineUser(mineUser, centerMineUserDetailRO);
        mineUserDetailRO.setPhoneNum(centerMineUserDetailRO.getPhoneNum());
        return new ResultRO<>(mineUserDetailRO);
    }
}
