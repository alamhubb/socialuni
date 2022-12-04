package com.socialuni.social.im.event;

import cn.hutool.http.HttpUtil;
import com.socialuni.social.common.api.entity.SocialuniUserInfoBaseDO;
import com.socialuni.social.common.api.enumeration.PublishDataType;
import com.socialuni.social.common.api.model.PublishDataModel;
import com.socialuni.social.common.sdk.event.ddd.AbstractJsonEventConsum;
import com.socialuni.social.common.sdk.event.ddd.AbstractPublishDataModelConsum;
import com.socialuni.social.im.model.SocialuniImUserModel;
import com.socialuni.social.im.service.ImAuthService;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.user.sdk.constant.GenderTypeNumEnum;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserDo;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

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
public class UserEventConsum extends AbstractPublishDataModelConsum {
    public static final String openImIp = "82.157.32.169";
    @Resource
    private ImAuthService imAuthService;
    @Resource
    private ConversionService conversionService;
//    @Override
    public void consumEvent(Map<String, Object> map) {
        // 注册到Im
        SocialuniImUserModel imUserModel = new SocialuniImUserModel();
        imUserModel.setUserID( conversionService.convert(map.get("id"),String.class) );
        imUserModel.setNickname(conversionService.convert(map.get("nickname"),String.class) );
        imUserModel.setFaceURL( conversionService.convert(map.get("avatar"),String.class));
        imUserModel.setGender( conversionService.convert(map.get("gender"),int.class));
        imUserModel.setPhoneNumber(conversionService.convert(map.get("phoneNum"),String.class) );
        imUserModel.setBirth(conversionService.convert(map.get("birthday"),Integer.class));
        imUserModel.setCreateTime(new Date());
        imAuthService.userRegister(imUserModel);
    }

    @Override
    public void consumEvent(PublishDataModel publishDataModel) {
        PublishDataType type = publishDataModel.getType();
        RequestMethod method = publishDataModel.getMethod();
        Object data = publishDataModel.getData();
        SocialuniImUserModel userModel = new SocialuniImUserModel();
        if(data instanceof SocialuniUserDo){
            SocialuniUserDo userDo = (SocialuniUserDo) data;
            userModel.setBirth(Integer.valueOf(userDo.getBirthday()));
            userModel.setNickname(userDo.getNickname());
            userModel.setFaceURL(userDo.getAvatar());
            String mineUserUid = SocialuniUnionIdFacede.getUuidByUnionIdNotNull(userDo.getUserId());
            userModel.setUserID(mineUserUid);
            userModel.setGender(GenderTypeNumEnum.getEnumByName(userDo.getGender()).getValue() );
            // 邮箱

            // 手机号
        }

        if(RequestMethod.POST.equals(method)){
            // 增加就不操作啦。 ， 之前的代码用了异步http调用去实现啦。
        }else if(RequestMethod.PUT.equals(method)){
            imAuthService.update_user_info(userModel);
        }
    }

    @Override
    public boolean canConsum(PublishDataModel publishDataModel) {
        Object data = publishDataModel.getData();
        return data instanceof SocialuniUserInfoBaseDO;
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
