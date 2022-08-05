package com.socialuni.center.web.config;

import com.socialuni.api.feignAPI.SocialuniUserAPI;
import com.socialuni.center.web.utils.CenterUserUtil;
import com.socialuni.social.constant.SocialFeignHeaderName;
import com.socialuni.social.entity.model.DO.UniOutRegisterUserDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;
import com.socialuni.center.web.constant.GenderTypeNumEnum;
import com.socialuni.center.web.repository.UniOutRegisterUserRepository;
import com.socialuni.center.web.utils.DevAccountUtils;
import com.socialuni.center.web.utils.SocialUserUtil;
import com.socialuni.social.web.sdk.utils.RequestUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

//开发环境访问线上环境需要
@Slf4j
@Component
@ConditionalOnClass(RequestInterceptor.class)
public class FeignInterceptor implements RequestInterceptor {
    @Value("${socialuni.secret-key:null}")
    private String socialuniDevSecretKey;

    @Resource
    UniOutRegisterUserRepository uniOutRegisterUserRepository;

    @Resource
    SocialuniUserAPI socialuniUserAPI;

    @Override
    public void apply(RequestTemplate requestTemplate) {

        //根据库里表有没有数据判断，是否调用，如果注册了，就在自己表里设置下，记录下。

        requestTemplate.header(SocialFeignHeaderName.socialuniSecretKey, socialuniDevSecretKey);
        requestTemplate.header(SocialFeignHeaderName.dataSocialuniId, DevAccountUtils.getAppSocialuniId());


        UserDO mineUser = CenterUserUtil.getMineUserAllowNull();

        if (mineUser != null) {
            Integer userUnionId = CenterUserUtil.getMineUserUnionId();
            Integer centerDevId = DevAccountUtils.getCenterDevIdNotNull();
            UniOutRegisterUserDO uniOutRegisterUserDO = uniOutRegisterUserRepository.findByDevIdAndUserUnionId(centerDevId, userUnionId);
            //有没有可能你自己这边记录了，但是那边给你删掉了
            //未在中心注册，则需要查询一下，未注册注册，如果直接注册呢，应该也可以，查注一体就行了。
            if (uniOutRegisterUserDO == null) {
                uniOutRegisterUserDO = new UniOutRegisterUserDO(centerDevId, userUnionId);
                uniOutRegisterUserRepository.save(uniOutRegisterUserDO);
                String phoneNum = SocialUserUtil.getUserPhoneNum(mineUser.getId());
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
            }
            requestTemplate.header(SocialFeignHeaderName.dataUserUnionId, userUnionId.toString());
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