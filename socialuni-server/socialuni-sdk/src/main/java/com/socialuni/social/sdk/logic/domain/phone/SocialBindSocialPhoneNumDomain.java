package com.socialuni.social.sdk.logic.domain.phone;

import com.socialuni.social.user.sdk.logic.manage.SocialUserPhoneManage;
import com.socialuni.social.user.sdk.logic.redis.SocialUserPhoneRedis;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class SocialBindSocialPhoneNumDomain {

    @Resource
    SocialUserPhoneRedis socialUserPhoneRedis;
    @Resource
    SocialUserPhoneManage socialUserPhoneManage;

//    @Resource
//    SocialUserDetailRepository socialUserDetailRepository;

    @Resource
    SocialBindPhoneNumDomain socialBindPhoneNumDomain;

    /*public SocialUserDetailRO bindSocialPhoneNum() {
        UserDO mineUser = SocialUserUtil.getMineUser();

        ResultRO<OAuthUserPhoneNumRO> resultRO = oAuthApi.getOAuthUserPhoneNum();
        if (resultRO.hasError()) {
            throw new SocialParamsException(resultRO.getErrorMsg());
        }
        String phoneNum = resultRO.getData().getPhoneNum();

        socialBindPhoneNumDomain.checkPhoneNumAndUserBindPhoneNum(mineUser, "86", phoneNum);

        SocialUserDetailRO userDetailVO = SocialUserDetailROFactory.getMineUserDetail(mineUser);
        return userDetailVO;
    }*/
}