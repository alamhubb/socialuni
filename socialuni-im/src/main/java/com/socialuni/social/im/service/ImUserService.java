package com.socialuni.social.im.service;

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
public class ImUserService {
    @Resource
    private ImHttpComponent imHttpComponent;
   /**
     * 修改用户信息 gender nickname faceURL等
     * @param userModel
     * @param token
     */
    public void updateUserInfo(SocialuniImUserModel userModel,String token){
        imHttpComponent.post("/auth/user_token", userModel,token);
    }

}
