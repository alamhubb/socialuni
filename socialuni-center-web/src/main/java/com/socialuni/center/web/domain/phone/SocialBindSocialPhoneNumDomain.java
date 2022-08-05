package com.socialuni.center.web.domain.phone;

import com.socialuni.center.web.manage.phone.SocialUserPhoneManage;
import com.socialuni.center.web.redis.SocialUserPhoneRedis;
import com.socialuni.center.web.repository.user.SocialUserPhoneRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class SocialBindSocialPhoneNumDomain {

    @Resource
    SocialUserPhoneRedis socialUserPhoneRedis;
    @Resource
    SocialUserPhoneRepository socialUserPhoneRepository;
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