package com.socialuni.social.user.sdk.api;

import com.socialuni.social.user.sdk.model.SocialUserPhoneModel;

public interface SocialUserPhoneApi {
    SocialUserPhoneModel findByPhoneNumAndStatus(String phoneNum, String status);

    SocialUserPhoneModel findByPhoneNumAndDevId(String phoneNum, Integer devId);

    SocialUserPhoneModel findByUserId(Integer userId);

    SocialUserPhoneModel findByUserIdAndStatus(Integer userId, String status);

    //关注后用户缓存修改，一人+粉丝，一人+关注
    SocialUserPhoneModel savePut(SocialUserPhoneModel phoneDO);
}