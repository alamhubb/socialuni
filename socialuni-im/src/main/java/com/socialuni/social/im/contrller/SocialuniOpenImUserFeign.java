package com.socialuni.social.im.contrller;

import com.socialuni.social.im.api.SocialuniOpenImUserInterface;
import com.socialuni.social.im.model.SocialuniImUserModel;
import com.socialuni.social.im.service.ImAuthService;
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
    public String getAndRefreshToken(String userId) {
        return imAuthService.getToken(userId);
    }
}
