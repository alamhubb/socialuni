package com.socialuni.social.im.service;

import com.socialuni.social.im.model.ImTokenModel;
import com.socialuni.social.im.model.SocialuniImUserModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <a href='https://doc.rentsoft.cn:8000/swagger/index.html#/鉴权认证'>鉴权服务接口</a>
 * @author wulinghui
 * @version 1.0
 * @module Socialuni
 * @date 2022/11/4 15:20
 * @since 1.0
 */
@Service
public class ImAuthService {
    @Resource
    private ImHttpComponent imHttpComponent;

    /**
     * 用户注册
     * @param userModel
     */
    public void userRegister(SocialuniImUserModel userModel){
        imHttpComponent.post("/auth/user_register",userModel);
    }
    /**
     * 获取用户的token
     * (免密的用户登录)
     * @param userID
     */
    public ImTokenModel userToken(String userID){
        SocialuniImUserModel userModel = new SocialuniImUserModel();
        userModel.setUserID(userID);
        String post = imHttpComponent.post("/auth/user_token", userModel);
        return imHttpComponent.parseResponse(post,ImTokenModel.class);
    }

}
