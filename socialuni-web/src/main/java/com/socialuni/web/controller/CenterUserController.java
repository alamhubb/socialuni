package com.socialuni.web.controller;

import com.socialuni.sdk.feignAPI.SocialuniUserAPI;
import com.socialuni.sdk.model.QO.user.*;
import com.socialuni.sdk.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.sdk.model.RO.user.CenterUserDetailRO;
import com.socialuni.sdk.serive.CenterUserService;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.net.URI;
import java.util.Map;

@RestController
public class CenterUserController implements SocialuniUserAPI {
    @PostConstruct
    public void someMethod(){
        System.out.println("大撒旦法撒旦老接口11111111111");
    }
    @Resource
    private CenterUserService centerUserService;

    @Override
    public ResultRO<CenterMineUserDetailRO> registryUser(URI baseUrl, Map<String, Object> headerMap, SocialProviderLoginQO loginQO) {
        return centerUserService.registryUser(loginQO);
    }

    @Override
    public ResultRO<CenterMineUserDetailRO> getMineUser() {
        return centerUserService.getMineUser();
    }

    @Override
    public ResultRO<CenterMineUserDetailRO> queryThirdUser() {
        return centerUserService.queryThirdUser();
    }

    @Override
    public ResultRO<CenterUserDetailRO> queryUserDetail(CenterUserIdQO centerUserIdQO) {
        return centerUserService.queryUserDetail(centerUserIdQO);
    }

    @Override
    public ResultRO<CenterMineUserDetailRO> editUser(SocialUserEditQO socialUserEditQO) {
        return centerUserService.editUser(socialUserEditQO);
    }

    @Override
    public ResultRO<CenterMineUserDetailRO> addUserImg(SocialUserImgAddQO socialUserImgAddQO) {
        return centerUserService.addUserImg(socialUserImgAddQO);
    }

    @Override
    public ResultRO<CenterMineUserDetailRO> deleteUserImg(CenterUserImgDeleteQO centerUserImgDeleteQO) {
        return centerUserService.deleteUserImg(centerUserImgDeleteQO);
    }
}
