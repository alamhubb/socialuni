package com.socialuni.center.service;

import com.socialuni.sdk.domain.phone.SocialBindPhoneNumDomain;
import com.socialuni.sdk.domain.phone.SocialBindWxPhoneNumDomain;
import com.socialuni.sdk.factory.RO.user.CenterMineUserDetailROFactory;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.model.QO.SocialBindWxPhoneNumQO;
import com.socialuni.sdk.model.QO.user.SocialPhoneNumQO;
import com.socialuni.sdk.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.sdk.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.sdk.utils.SocialUserUtil;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PhoneService {
    @Resource
    private SocialBindPhoneNumDomain socialBindPhoneNumDomain;
    @Resource
    private SocialBindWxPhoneNumDomain socialBindWxPhoneNumDomain;

    public ResultRO<CenterMineUserDetailRO> bindWxPhoneNum(SocialBindWxPhoneNumQO bindWxPhoneNumQO) {
        SocialUserDO mineUser = SocialUserUtil.getMineUserNotNull();
        //微信绑定手机号方法

        SocialMineUserDetailRO socialMineUserDetailRO = socialBindWxPhoneNumDomain.bindWxPhoneNum(bindWxPhoneNumQO, mineUser);
        //只是记录一个授权记录
        //生成一条对三方的手机号授权，并且返回手机号信息

        CenterMineUserDetailRO centerMineUserDetailRO = CenterMineUserDetailROFactory.getMineUserDetail(socialMineUserDetailRO, mineUser);

        return new ResultRO<>(centerMineUserDetailRO);
    }

    public ResultRO<CenterMineUserDetailRO> bindPhoneNum(SocialPhoneNumQO bindPhoneQO) {
        SocialUserDO mineUser = SocialUserUtil.getMineUserNotNull();
        //微信绑定手机号方法

        SocialMineUserDetailRO socialMineUserDetailRO = socialBindPhoneNumDomain.bindPhoneNum(bindPhoneQO, mineUser);
        //只是记录一个授权记录
        //生成一条对三方的手机号授权，并且返回手机号信息

        CenterMineUserDetailRO centerMineUserDetailRO = CenterMineUserDetailROFactory.getMineUserDetail(socialMineUserDetailRO, mineUser);

        return new ResultRO<>(centerMineUserDetailRO);
    }
}
