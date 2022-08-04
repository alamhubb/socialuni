package com.socialuni.center.web.config;

import com.socialuni.api.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.center.web.utils.CenterUserUtil;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.constant.SocialFeignHeaderName;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.sdk.constant.GenderTypeNumEnum;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import com.socialuni.social.web.sdk.utils.RequestUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

//开发环境访问线上环境需要
@Slf4j
@Component
@ConditionalOnClass(RequestInterceptor.class)
public class FeignInterceptor implements RequestInterceptor {
    @Value("${socialuni.secret-key:null}")
    private String socialuniDevSecretKey;

    @Override
    public void apply(RequestTemplate requestTemplate) {

        //根据库里表有没有数据判断，是否调用，如果注册了，就在自己表里设置下，记录下。

        requestTemplate.header(SocialFeignHeaderName.socialSecretKeyHeaderName, socialuniDevSecretKey);


        /*ResultRO<CenterMineUserDetailRO> resultRO = socialuniUserAPI.queryThirdUser();
        CenterMineUserDetailRO centerMineUserDetailRO = resultRO.getData();
        if (centerMineUserDetailRO == null) {
            UserDO mineUser = CenterUserUtil.getMineUser();
            String phoneNum =  SocialUserUtil.getUserPhoneNum(mineUser.getId());
            //生成登录类
            SocialProviderLoginQO socialProviderLoginQO = new SocialProviderLoginQO();
            socialProviderLoginQO.setNickName(mineUser.getNickname());
            socialProviderLoginQO.setAvatarUrl(mineUser.getAvatar());
            socialProviderLoginQO.setGender(GenderTypeNumEnum.getValueByName(mineUser.getGender()));
            socialProviderLoginQO.setBirthday(mineUser.getBirthday());
            socialProviderLoginQO.setCity(mineUser.getCity());
            socialProviderLoginQO.setUnionId(mineUser.getId().toString());

            socialProviderLoginQO.setProvider(RequestUtil.getProvider());
            socialProviderLoginQO.setPlatform(RequestUtil.getPlatform());
            socialProviderLoginQO.setSystem(RequestUtil.getSystem());
            socialProviderLoginQO.setPhoneNum(phoneNum);

            socialuniUserAPI.registryUser(socialProviderLoginQO);
        }*/

        UserDO mineUser = CenterUserUtil.getMineUserAllowNull();
        if (mineUser != null) {
            requestTemplate.header(SocialFeignHeaderName.dataUserUnionId, mineUser.getId().toString());
        }

        //理论上不可能存在下列情况，没有dataSocialuniId只能是自有数据和无后台，无后台也应该在request设置dataSocialuniId
        /*if (StringUtils.isEmpty(dataSocialuniId)) {
            if (!DevAccountUtils.pushServer()) {
                throw new SocialParamsException("请设置数据所属开发者Id");
            }
        }*/
        //就是看有没有dataOwnerId


        //是在判断什么，判断是转发还是发起的。
        //发起的有哪些特征， 肯定是devId=1的
        //是否存在devId为1，但是是转发的情况呢。

        //还有其他情况吗，thirdId不可以判断，无后台的时候thirdId也为null、

        //

        //如果有三方id，则使用三方用户id，如果没有则使用本方


        //一个是推给下一级的本层的秘钥，要给所有子应用推，排除来源的子应用
        //一个是秘钥
        //一个是记录原始应用id
    }
}