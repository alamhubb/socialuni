package com.socialuni.social.sdk.im.feign;

import com.socialuni.social.sdk.im.innerApi.SocialuniOpenImUserInterface;
import com.socialuni.social.sdk.im.model.SocialuniImUserModel;
import com.socialuni.social.sdk.im.service.ImAuthService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SocialuniOpenImUserFeign implements SocialuniOpenImUserInterface {
    @Resource
    private ImAuthService imAuthService;

    @Override
    public String userLogin(SocialuniImUserModel imUserModel) {
        String token = imAuthService.userRegister(imUserModel);
        return token;
//        throw new SocialParamsException("测试报错功能");
//        return null;
    }

    @Override
    public String getAndRefreshToken(Long userId) {
        return imAuthService.getToken(String.valueOf(userId));
    }
}
