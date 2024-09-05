package com.socialuni.social.sdk.config.config;

import com.socialuni.social.sdk.dao.repository.UniOutRegisterUserRepository;
import com.socialuni.social.user.sdk.api.SocialuniThirdUserAPI;
import com.socialuni.social.tance.dev.enumeration.SocialFeignHeaderName;
import com.socialuni.social.tance.dev.facade.DevAccountFacade;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

//开发环境访问线上环境需要
@Slf4j
@Component
@ConditionalOnClass(RequestInterceptor.class)
public class FeignInterceptor implements RequestInterceptor {
    @Resource
    UniOutRegisterUserRepository uniOutRegisterUserRepository;
    @Resource
    SocialuniThirdUserAPI socialuniThirdUserAPI;

    @Override
    public void apply(RequestTemplate requestTemplate) {

        String postUrl = requestTemplate.path();

        //还是要加一个联盟账户渠道

        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserAllowNull();

        System.out.println(postUrl);
        System.out.println(mineUser != null);

        if (!postUrl.contains("/registryUser")) {
            if (mineUser != null) {
                Long mineUserUnionId = mineUser.getUnionId();
                //主要是记录有没有的
                Integer centerDevId = DevAccountFacade.getCenterDevIdNotNull();


//                requestTemplate.header(SocialuniWebConfig.getTokenName(), mineUserUid);


                //根据库里表有没有数据判断，是否调用，如果注册了，就在自己表里设置下，记录下。


//                requestTemplate.header(SocialFeignHeaderName.socialuniSecretKey, devAccountModel.getApiSecretKey());
//                requestTemplate.target(devAccountModel.getApiUrl());
            }
        }

//        DevAccountModel devAccountModel = DevAccountFacade.getDevAccountNotNUll();
        requestTemplate.header(SocialFeignHeaderName.socialuniSecretKey, "d9a53b2892a540689b4ef608bdca3d2e");
//        }


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