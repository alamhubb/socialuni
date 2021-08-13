package com.socialuni.center.web.qingchi;

import com.socialuni.api.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.center.web.domain.thirdUser.AuthThirdUserDomain;
import com.socialuni.center.web.entity.AuthThirdUserEntity;
import com.socialuni.center.web.utils.CenterUserUtil;
import com.socialuni.center.web.utils.DevAccountUtils;
import com.socialuni.social.sdk.constant.AuthType;
import com.socialuni.social.sdk.domain.login.SocialPhoneLoginDomain;
import com.socialuni.social.sdk.domain.phone.SocailSendAuthCodeDomain;
import com.socialuni.social.sdk.domain.phone.SocialBindPhoneNumDomain;
import com.socialuni.social.sdk.domain.phone.SocialBindWxPhoneNumDomain;
import com.socialuni.social.sdk.exception.SocialParamsException;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import com.socialuni.social.model.model.QO.SocialBindWxPhoneNumQO;
import com.socialuni.social.model.model.QO.user.SocialPhoneNumQO;
import com.socialuni.social.api.model.model.ResultRO;
import com.socialuni.social.model.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.social.model.model.RO.user.login.SocialLoginRO;
import com.socialuni.social.model.model.RO.user.phone.SocialSendAuthCodeQO;
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
    SocialPhoneLoginDomain socialPhoneLoginDomain;
    @Resource
    private AuthThirdUserDomain authThirdUserDomain;

    private void checkIsQingchiApp(){
        if (!DevAccountUtils.getDevId().equals(1)) {
            throw new SocialParamsException("仅支持社交联盟绑定手机号");
        }
    }

    //微信绑定手机号方法
    public ResultRO<CenterMineUserDetailRO> bindWxPhoneNum(@Valid SocialBindWxPhoneNumQO bindPhoneQO) {
        // 只有清池支持渠道登录
        this.checkIsQingchiApp();
        UserDO mineUser = CenterUserUtil.getMineUser();
        SocialMineUserDetailRO socialMineUserDetailRO = socialBindWxPhoneNumDomain.bindWxPhoneNum(bindPhoneQO, mineUser);

        //生成一条对三方的手机号授权，并且返回手机号信息
        CenterMineUserDetailRO centerMineUserDetailRO = authThirdUserEntity.authThirdUser(mineUser, AuthType.phone);
        return ResultRO.success(centerMineUserDetailRO);
    }

    public ResultRO<Void> sendAuthCode(SocialSendAuthCodeQO authCodeQO) {
        this.checkIsQingchiApp();
        UserDO mineUser = CenterUserUtil.getMineUser();
        return socailSendAuthCodeDomain.sendAuthCode(authCodeQO, mineUser);
    }

    public ResultRO<CenterMineUserDetailRO> bindPhoneNum(SocialPhoneNumQO socialPhoneNumQO) {
        this.checkIsQingchiApp();
        UserDO mineUser = CenterUserUtil.getMineUser();
        mineUser = socialBindPhoneNumDomain.bindPhoneNum(socialPhoneNumQO, mineUser);

        //生成一条对三方的手机号授权，并且返回手机号信息
        CenterMineUserDetailRO centerMineUserDetailRO = authThirdUserEntity.authThirdUser(mineUser, AuthType.phone);
        return new ResultRO<>(centerMineUserDetailRO);
    }

    @Transactional
    public ResultRO<SocialLoginRO<CenterMineUserDetailRO>> phoneLogin(SocialPhoneNumQO socialPhoneNumQO) {
        this.checkIsQingchiApp();
        //todo 这接口有问题，应该拆开，手机号登陆不应该和三方登陆在一起
        //根据user获取返回结果
        SocialLoginRO<SocialMineUserDetailRO> socialLoginRO = socialPhoneLoginDomain.phoneLogin(socialPhoneNumQO);
        UserDO mineUser = SocialUserUtil.get(socialLoginRO.getUser().getId());
        //中心授权
        SocialLoginRO<CenterMineUserDetailRO> centerLoginRO = authThirdUserDomain.authThirdUser(mineUser, AuthType.phone);
        return ResultRO.success(centerLoginRO);
    }
}
