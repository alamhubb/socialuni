package com.socialuni.social.im.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.socialuni.social.common.api.utils.JsonUtil;
import com.socialuni.social.im.model.ImTokenModel;
import com.socialuni.social.im.model.SocialuniImUserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <a href='https://doc.rentsoft.cn:8000/swagger/index.html#/鉴权认证'>鉴权服务接口</a>
 *
 * @author wulinghui
 * @version 1.0
 * @module Socialuni
 * @date 2022/11/4 15:20
 * @since 1.0
 */
@Service
@Slf4j
public class ImAuthService {
    @Resource
    private ImHttpComponent imHttpComponent;

    /**
     * 用户注册
     *
     * @param userModel
     */
    public String userRegister(SocialuniImUserModel userModel) {
        String post = imHttpComponent.post("/auth/user_register", userModel);
        ImTokenModel tokenModel = null;
        try {
            tokenModel = imHttpComponent.parseResponse(post, ImTokenModel.class);
        } catch (Exception exception) {
            // 重复注册的bug。  这里接直接再去尝试直接获得token.
            tokenModel = this.userToken(userModel);
        }
        String token = tokenModel.getToken();
        return token;
    }

    @Async
    public void userRegisterAsync(SocialuniImUserModel userModel) {
        this.userRegister(userModel);
    }

    /**
     * 修改用户信息
     *
     * @param userModel
     */
    public String update_user_info(SocialuniImUserModel userModel) {
        String userID = userModel.getUserID();
        String token;
        try {
            token = this.getToken(userID);
        } catch (Exception e) {
            token = this.userRegister(userModel);
        }
        String response = imHttpComponent.post("/user/update_user_info", userModel, token);
        return response;
    }

    /**
     * 获取用户的token
     * (免密的用户登录)
     *
     * @param userID
     */
    public ImTokenModel userToken(String userID) {
        SocialuniImUserModel userModel = new SocialuniImUserModel();
        userModel.setUserID(userID);
        String post = imHttpComponent.post("/auth/user_token", userModel);
        return imHttpComponent.parseResponse(post, ImTokenModel.class);
    }

    /**
     * 获取用户的token
     * (免密的用户登录)
     *
     * @param userModel
     */
    public ImTokenModel userToken(SocialuniImUserModel userModel) {
        String post = imHttpComponent.post("/auth/user_token", userModel);
        return imHttpComponent.parseResponse(post, ImTokenModel.class);
    }

    /**
     * 获得token
     *
     * @param userID
     * @return
     * @see #userToken(String)
     */
    public String getToken(String userID) {
        return this.userToken(userID).getToken();
    }
}
