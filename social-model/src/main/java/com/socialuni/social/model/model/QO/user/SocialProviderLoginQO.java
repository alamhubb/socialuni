package com.socialuni.social.model.model.QO.user;

import lombok.Data;

/**
 * @author qinkaiyuan
 * @date 2019-02-14 22:03
 */
@Data
public class SocialProviderLoginQO {
    //qq、wx、phone,前台叫provider
    private String loginType;
    //todo，provider plateform好像还是有点乱，需要确认，涉及到数据库用户信息和常量类型
    private String provider;
    //h5、mp、app//先判断平台，然后判断provider
    private String platform;

    //为什么这里存用户名什么的，因为app时，微信情况下，直接有unionId，就不再去请求了
    //app平台兑换openid unionid使用,目测 accessToken无用了
    private String accessToken;
    //小程序平台兑换openid unionid使用
    private String code;

    private String openId;
    private String unionId;

    //微信mp获取unionId使用，
    //百度小程序使用
    private String encryptedData;
    private String iv;

    private String nickName;
    private String avatarUrl;
    private Integer gender;
    //出生日期
    private String birthday;

    private String country;
    private String province;
    private String city;

    //手机号登录使用
    private String phoneNum;
    private String authCode;

    //暂未使用
    private String inviteCode;

    //无后台，借用联盟后台实现渠道登录时使用,逻辑太复杂，暂不支持，需要维持多个开发者账号的关系
//    private String appId;
//    private String secret;
//    private Integer devId;
}
