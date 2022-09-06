package com.socialuni.sdk.config;

import com.socialuni.sdk.constant.GenderTypeNumEnum;
import com.socialuni.sdk.feignAPI.SocialuniThirdUserAPI;
import com.socialuni.sdk.model.DO.UniOutRegisterUserDO;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.model.QO.user.SocialProviderLoginQO;
import com.socialuni.sdk.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.sdk.model.RO.user.login.SocialLoginRO;
import com.socialuni.sdk.repository.UniOutRegisterUserRepository;
import com.socialuni.sdk.repository.user.SocialUserAccountRepository;
import com.socialuni.sdk.store.SocialUserAccountStore;
import com.socialuni.sdk.utils.DevAccountUtils;
import com.socialuni.sdk.utils.SocialUserUtil;
import com.socialuni.sdk.utils.UnionIdDbUtil;
import com.socialuni.social.web.sdk.model.ResultRO;
import com.socialuni.sdk.constant.socialuni.SocialFeignHeaderName;
import com.socialuni.social.web.sdk.config.SocialuniWebConfig;
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
    SocialUserAccountRepository socialUserAccountRepository;

    @Resource
    SocialuniThirdUserAPI socialuniThirdUserAPI;
    @Resource
    private SocialUserAccountStore socialUserAccountStore;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        //根据库里表有没有数据判断，是否调用，如果注册了，就在自己表里设置下，记录下。

        requestTemplate.header(SocialFeignHeaderName.socialuniSecretKey, SocialAppConfig.getDevSecretKey());

        String postUrl = requestTemplate.path();

        //还是要加一个联盟账户渠道

        SocialUserDO mineUser = SocialUserUtil.getMineUserAllowNull();

        if (!postUrl.contains("thirdUser/registryUser")) {
            if (mineUser != null) {
                Integer mineUserUnionId = mineUser.getUnionId();
                //主要是记录有没有的
                Integer centerDevId = DevAccountUtils.getCenterDevIdNotNull();
                UniOutRegisterUserDO uniOutRegisterUserDO = uniOutRegisterUserRepository.findByDevIdAndUserId(centerDevId, mineUserUnionId);

//                SocialUserAccountDO socialUserAccountDO = SocialUserAccountUtil.getUserSocialAccount(mineUser.getId());

                //有可能存在已经查询过这个用户了，然后unionId表已经记录了，然后你登录了，发现是你，然后怎么办。把这个的，把这儿用户的unionId，变为这个unionId就行了。

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
                    socialProviderLoginQO.setUnionId(mineUserUnionId.toString());
                    if (SocialAppConfig.serverIsChild()) {
                        socialProviderLoginQO.setPhoneNum(phoneNum);
                    }
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
                    ResultRO<SocialLoginRO<CenterMineUserDetailRO>> resultRO = socialuniThirdUserAPI.registryUser(socialProviderLoginQO);
                    SocialLoginRO<CenterMineUserDetailRO> loginRO = resultRO.getData();
                    CenterMineUserDetailRO centerMineUserDetailRO = loginRO.getUser();

                    UnionIdDbUtil.updateUidByUnionIdNotNull(mineUserUnionId, centerMineUserDetailRO.getId());
                    uniOutRegisterUserDO = new UniOutRegisterUserDO(centerDevId, mineUserUnionId);
                    uniOutRegisterUserRepository.save(uniOutRegisterUserDO);
                }

                String mineUserUid = UnionIdDbUtil.getUidByUnionIdNotNull(mineUserUnionId);

                requestTemplate.header(SocialuniWebConfig.getTokenName(), mineUserUid);
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