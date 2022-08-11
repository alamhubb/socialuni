package com.socialuni.sdk.config;

import com.socialuni.sdk.constant.GenderTypeNumEnum;
import com.socialuni.sdk.feignAPI.SocialuniUserAPI;
import com.socialuni.sdk.model.DO.UniOutRegisterUserDO;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.model.QO.user.SocialProviderLoginQO;
import com.socialuni.sdk.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.sdk.repository.UniOutRegisterUserRepository;
import com.socialuni.sdk.utils.CenterUserUtil;
import com.socialuni.sdk.utils.DevAccountUtils;
import com.socialuni.sdk.utils.SocialUserUtil;
import com.socialuni.sdk.utils.UnionIdDbUtil;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.constant.SocialFeignHeaderName;
import com.socialuni.social.web.sdk.utils.RequestUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//开发环境访问线上环境需要
@Slf4j
@Component
@ConditionalOnClass(RequestInterceptor.class)
public class FeignInterceptor implements RequestInterceptor {
    @Resource
    UniOutRegisterUserRepository uniOutRegisterUserRepository;

    @Resource
    SocialuniUserAPI socialuniUserAPI;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        //根据库里表有没有数据判断，是否调用，如果注册了，就在自己表里设置下，记录下。

//        requestTemplate.header(SocialFeignHeaderName.dataOriginalSocialuniId, DevAccountUtils.getAppSocialuniId());


        System.out.println(requestTemplate);
        System.out.println(requestTemplate.url());
        System.out.println(11111);
        System.out.println(requestTemplate.feignTarget());
        System.out.println(requestTemplate.feignTarget().url());
        System.out.println(requestTemplate.headers().get(SocialFeignHeaderName.socialuniSecretKey));
        SocialUserDO mineUser = CenterUserUtil.getMineUserAllowNull();
        String postUrl = requestTemplate.path();

        System.out.println(postUrl);

        if (!postUrl.contains("user/registryUser")) {
            if (mineUser != null) {
                Integer mineUserUnionId = mineUser.getUnionId();
                //主要是记录有没有的
                Integer centerDevId = DevAccountUtils.getCenterDevIdNotNull();
                UniOutRegisterUserDO uniOutRegisterUserDO = uniOutRegisterUserRepository.findByDevIdAndUserId(centerDevId, mineUserUnionId);
                //有没有可能你自己这边记录了，但是那边给你删掉了
                //未在中心注册，则需要查询一下，未注册注册，如果直接注册呢，应该也可以，查注一体就行了。
                if (uniOutRegisterUserDO == null) {
                    String phoneNum = SocialUserUtil.getUserPhoneNum(mineUserUnionId);
                    //生成登录类
                    SocialProviderLoginQO socialProviderLoginQO = new SocialProviderLoginQO();
                    socialProviderLoginQO.setNickName(mineUser.getNickname());
                    socialProviderLoginQO.setAvatarUrl(mineUser.getAvatar());
                    socialProviderLoginQO.setGender(GenderTypeNumEnum.getValueByName(mineUser.getGender()));
                    socialProviderLoginQO.setBirthday(mineUser.getBirthday());
                    socialProviderLoginQO.setCity(mineUser.getCity());
                    socialProviderLoginQO.setProvider(RequestUtil.getProvider());
                    socialProviderLoginQO.setPlatform(RequestUtil.getPlatform());
                    socialProviderLoginQO.setSystem(RequestUtil.getSystem());
                    if (SocialAppConfig.serverIsChild()) {
                        socialProviderLoginQO.setPhoneNum(phoneNum);
                    }
                    System.out.println(123123);
//                    URI uri = URI.create(postUrl);
//                    String absoluteUri = uri.getScheme() + "://" + uri.getAuthority();
//                    URI determinedBasePathUri = URI.create(absoluteUri);

                    String apiUrl = SocialAppConfig.getSocialuniServerUrl();
                    URI determinedBasePathUri = URI.create(Objects.requireNonNull(apiUrl));

//                    Collection<String> headerKeyValues = requestTemplate.headers().get(SocialFeignHeaderName.socialuniSecretKey);
//                    String devSecretKey = headerKeyValues.iterator().next();
                    Map<String, Object> headerMap = new HashMap<String, Object>() {{
                        put(SocialFeignHeaderName.socialuniSecretKey, SocialAppConfig.getDevSecretKey());
                    }};
                    ResultRO<CenterMineUserDetailRO> resultRO = socialuniUserAPI.registryUser(determinedBasePathUri, headerMap, socialProviderLoginQO);
                    CenterMineUserDetailRO centerMineUserDetailRO = resultRO.getData();
                    UnionIdDbUtil.updateUidByUnionIdNotNull(mineUserUnionId, centerMineUserDetailRO.getId());
                    uniOutRegisterUserDO = new UniOutRegisterUserDO(centerDevId, mineUserUnionId);
                    uniOutRegisterUserRepository.save(uniOutRegisterUserDO);
                }
                requestTemplate.header(SocialFeignHeaderName.dataUserUnionId, CenterUserUtil.getMineUserUnionId());
            }
        }

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