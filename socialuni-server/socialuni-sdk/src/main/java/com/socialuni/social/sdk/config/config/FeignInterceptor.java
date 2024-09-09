package com.socialuni.social.sdk.config.config;

import com.socialuni.social.common.api.config.SocialRequestUserConfig;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.user.SocialuniUserShowRO;
import com.socialuni.social.common.api.utils.RequestUtil;
import com.socialuni.social.common.api.utils.SocialTokenFacade;
import com.socialuni.social.sdk.dao.repository.UniOutRegisterUserRepository;
import com.socialuni.social.tance.dev.config.SocialuniDevConfig;
import com.socialuni.social.tance.dev.dao.DO.SocialuniThirdTokenDO;
import com.socialuni.social.tance.dev.facade.SocialuniUnionIdFacede;
import com.socialuni.social.tance.dev.util.SocialuniThirdTokenUtil;
import com.socialuni.social.user.sdk.api.SocialuniThirdUserAPI;
import com.socialuni.social.tance.dev.enumeration.SocialFeignHeaderName;
import com.socialuni.social.tance.dev.facade.DevAccountFacade;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.constant.GenderTypeNumEnum;
import com.socialuni.social.user.sdk.model.QO.SocialProviderLoginQO;
import com.socialuni.social.user.sdk.model.RO.login.SocialLoginRO;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.web.sdk.config.SocialuniWebConfig;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private SocialuniThirdTokenUtil socialuniThirdTokenUtil;
    @Autowired
    private SocialuniDevConfig socialuniDevConfig;

    public SocialProviderLoginQO createRegistryQO(SocialuniUserDo mineUser) {
        String phoneNum = SocialuniUserUtil.getUserPhoneNum(mineUser.getUserId());

        SocialProviderLoginQO socialProviderLoginQO = new SocialProviderLoginQO();
        socialProviderLoginQO.setNickName(mineUser.getNickname());
        socialProviderLoginQO.setAvatarUrl(mineUser.getAvatar());
        socialProviderLoginQO.setGender(GenderTypeNumEnum.getValueByName(mineUser.getGender()));
        socialProviderLoginQO.setBirthday(mineUser.getBirthday());
        socialProviderLoginQO.setCity(mineUser.getCity());
        socialProviderLoginQO.setUserType(mineUser.getType());
        socialProviderLoginQO.setProvider(RequestUtil.getProvider());
//                    socialProviderLoginQO.setPlatform(RequestUtil.getPlatform());
//                    socialProviderLoginQO.setSystem(RequestUtil.getSystem());
        socialProviderLoginQO.setPhoneNum(phoneNum);
        return socialProviderLoginQO;
    }

    @Resource
    SocialRequestUserConfig socialRequestUserConfig;

    @Override
    public void apply(RequestTemplate requestTemplate) {

        String postUrl = requestTemplate.path();

        //还是要加一个联盟账户渠道
        String token = RequestUtil.getRequestValue(SocialuniWebConfig.getTokenName());
        String token1 = socialRequestUserConfig.getToken();
        Long userId = socialRequestUserConfig.getUserId();
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserAllowNull();

        if (!postUrl.contains("/registryUser")) {
            if (mineUser != null) {
                Integer serverDevId = DevAccountFacade.getCenterDevIdNotNull();
                SocialuniThirdTokenDO socialuniThirdTokenDO = SocialuniThirdTokenUtil.getThirdUserToken(serverDevId, mineUser.getUserId());

                if (socialuniThirdTokenDO == null) {
                    SocialProviderLoginQO socialProviderLoginQO = createRegistryQO(mineUser);
                    ResultRO<SocialLoginRO<SocialuniUserShowRO>> resultRO = socialuniThirdUserAPI.registryUser(socialProviderLoginQO);

                    SocialuniUserShowRO socialuniUserRO = resultRO.getData().getUser();

                    String uuid = socialuniUserRO.getId();

                    Long mineUserUnionId = mineUser.getUnionId();

                    //保存三方token
                    socialuniThirdTokenDO = SocialuniThirdTokenUtil.createdThirdTokenOrGet(mineUserUnionId, resultRO.getData().getToken(), serverDevId, uuid);


                    log.info("DevAccountFacade.hasDevKey():{}", DevAccountFacade.hasDevKey());
                    log.info("getDevIdNullElseCenterDevId:{}", DevAccountFacade.getDevIdNullElseCenterDevId());
                    log.info("mineUserUnionId:{}", mineUserUnionId);
                    log.info("socialuniUserRO.getId():{}", socialuniUserRO.getId());
                    SocialuniUnionIdFacede.updateUuidByUnionIdNotNull(mineUserUnionId, socialuniUserRO.getId());
                }
                requestTemplate.header(SocialuniWebConfig.getTokenName(), socialuniThirdTokenDO.getToken());
            }
        }
        requestTemplate.header(SocialFeignHeaderName.socialuniSecretKey, "d9a53b2892a540689b4ef608bdca3d2e");

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