package com.socialuni.center.web.config;

import com.socialuni.center.web.utils.CenterUserUtil;
import com.socialuni.social.constant.SocialFeignHeaderName;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.exception.SocialParamsException;
import com.socialuni.social.sdk.utils.DevAccountUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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


        requestTemplate.header(SocialFeignHeaderName.socialSecretKeyHeaderName, socialuniDevSecretKey);

        UserDO mineUser = CenterUserUtil.getMineUserAllowNull();
        if (mineUser != null) {
            requestTemplate.header(SocialFeignHeaderName.socialuniThirdUserId, mineUser.getId().toString());
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