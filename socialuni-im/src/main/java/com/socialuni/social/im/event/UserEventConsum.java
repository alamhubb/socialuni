package com.socialuni.social.im.event;

import cn.hutool.http.HttpUtil;
import com.socialuni.social.common.sdk.event.ddd.AbstractJsonEventConsum;
import com.socialuni.social.im.model.SocialuniImUserModel;
import com.socialuni.social.im.service.ImAuthService;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**用于同步user信息到im系统里面去。
 * @author wulinghui
 * @version 1.0
 * @module Socialuni
 * @date 2022/11/4 11:17
 * @since 1.0
 */
@Component
public class UserEventConsum extends AbstractJsonEventConsum {
    public static final String openImIp = "82.157.32.169";
    @Resource
    private ImAuthService imAuthService;
    @Resource
    private ConversionService conversionService;
    @Override
    public void consumEvent(Map<String, Object> map) {
        // 注册到Im
        SocialuniImUserModel imUserModel = new SocialuniImUserModel();
        imUserModel.setUserID( conversionService.convert(map.get("id"),String.class) );
        imUserModel.setNickname(conversionService.convert(map.get("nickname"),String.class) );
        imUserModel.setFaceURL( conversionService.convert(map.get("avatar"),String.class));
        imUserModel.setGender( conversionService.convert(map.get("gender"),int.class));
        imUserModel.setPhoneNumber(conversionService.convert(map.get("phoneNum"),String.class) );
        imUserModel.setBirth(conversionService.convert(map.get("birthday"),Date.class));
        imUserModel.setCreateTime(new Date());
        imAuthService.userRegister(imUserModel);
    }

    @Override
    public String getMatchClassName() {
        return "com.socialuni.social.sdk.model.RO.user.SocialuniMineUserDetailRO";
    }

    public static void main(String[] args) {
        GenericConversionService genericConversionService = new GenericConversionService();
        Integer convert = genericConversionService.convert("1", Integer.class);
        System.out.println(convert);

        String post = HttpUtil.post(openImIp + ":10002/auth/user_register", "{\n" +
                "  \"birth\": 0,\n" +
                "  \"email\": \"string\",\n" +
                "  \"ex\": \"string\",\n" +
                "  \"faceURL\": \"string\",\n" +
                "  \"gender\": 0,\n" +
                "  \"nickname\": \"string\",\n" +
                "  \"operationID\": \"string\",\n" +
                "  \"phoneNumber\": \"string\",\n" +
                "  \"platform\": 7,\n" +
                "  \"secret\": \"tuoyun\",\n" +
                "  \"userID\": \"string\"\n" +
                "}");
        System.out.println(post);


        post = HttpUtil.post(openImIp + ":10002/auth/user_token", "{\n" +
                "  \"operationID\": \"string\",\n" +
                "  \"platform\": 8,\n" +
                "  \"secret\": \"tuoyun\",\n" +
                "  \"userID\": \"string\"\n" +
                "}");
        System.out.println(post);



    }
}
