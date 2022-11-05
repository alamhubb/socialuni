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
    public void userLogin(SocialuniImUserModel imUserModel) {
        imAuthService.userRegister(imUserModel);
//        throw new SocialParamsException("测试报错功能");
//        return null;
    }

    @Override
    public String getToken(String userId) {
        return imAuthService.getToken(userId);
    }
}
