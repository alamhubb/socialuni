package com.qingchi.web.controller.oldVersion;

import com.qingchi.web.constant.QingchiProviderType;
import com.qingchi.web.model.RO.LoginRO;
import com.qingchi.web.service.login.LoginService;
import com.qingchi.web.service.login.UserLoginService;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.QO.user.SocialPhoneNumQO;
import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author qinkaiyuan
 * @date 2019-02-17 14:14
 */
@RestController
@RequestMapping("user")
@Slf4j
public class OldUserLoginController {
    @Resource
    private LoginService loginService;
    @Resource
    private UserLoginService userLoginService;

    @Deprecated
    //三方渠道登录，qq、wx、社交联盟，兼容各平台，h5、app、mp
    @PostMapping("providerLogin")
    public ResultRO<LoginRO> providerLogin(@RequestBody SocialProviderLoginQO loginVO) {
        if (QingchiProviderType.phone.equals(loginVO.getProvider())) {
            SocialPhoneNumQO socialPhoneNumQO = new SocialPhoneNumQO(loginVO.getPhoneNum(), loginVO.getAuthCode());
            ResultRO<LoginRO> resultRO = loginService.phoneLogin(socialPhoneNumQO);
            return resultRO;
        }
        ResultRO<LoginRO> resultRO = userLoginService.providerLogin(loginVO);
        return resultRO;
    }

    @Deprecated
    //可以携带id也可以不携带，不携带返回时生成
    //app端，小程序端，微信和qq登陆的接口，传入第三方返回的基础信息，昵称等，没有手机号，Provider = 'weixin' | 'qq'
    @PostMapping("platformLogin")
    public ResultRO<LoginRO> platformLogin(@RequestBody @Valid SocialProviderLoginQO loginVO) {
        //Provider = 'weixin' | 'qq'
        ResultRO<LoginRO> resultRO = userLoginService.providerLogin(loginVO);
        return resultRO;
    }

    @Deprecated
    @PostMapping("phoneLogin")
    public ResultRO<LoginRO> phoneLogin(@RequestBody @Valid SocialPhoneNumQO socialPhoneNumQO) {
        //Provider = 'weixin' | 'qq'
        ResultRO<LoginRO> resultRO = loginService.phoneLogin(socialPhoneNumQO);
        return resultRO;
    }


    /**
     * 微信小程序界面点击绑定手机号触发
     *
     * @param bindWxPhoneNumQO
     * @return
     */
    /*


     *//**
     * 登录接口，登录后返回用户的token
     * toDO 还没有做百度和头条平台的适配
     *
     * @param loginVO
     * @return
     *//*
    @Deprecated
    //下版本作废 2021-7-17，统一使用 login/providerLogin
    @PostMapping("miniAppLogin")
    public ResultRO<SocialLoginRO<MineUserDetailRO>> miniAppLogin(@RequestBody SocialProviderLoginQO loginVO) {
        //需要手动赋值
        loginVO.setPlatform(PlatformType.mp);
        SocialLoginRO<MineUserDetailRO> userDetailROSocialLoginRO = centerLoginService.providerAndPhoneLogin(loginVO, null);
        return new ResultRO<>(userDetailROSocialLoginRO);
    }

    *//**
     * 不在使用
     * 这个是手机号直接绑定的
     *
     * @param socialPhoneNumQO
     * @return
     *//*
    @Deprecated
    @PostMapping("bindPhoneNum2")
    @ResponseBody
    public ResultRO<CenterMineUserDetailRO> bindPhoneNum2(@RequestBody SocialPhoneNumQO socialPhoneNumQO) {
        CenterMineUserDetailRO centerMineUserDetailRO = centerPhoneNumService.userBindPhoneNum(socialPhoneNumQO);
        return new ResultRO<>(centerMineUserDetailRO);
    }*/
}
