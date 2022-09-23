package com.socialuni.sdk.dao.store;

import com.socialuni.sdk.model.UniUnionIdRO;
import com.socialuni.sdk.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.common.exception.exception.SocialParamsException;
import com.socialuni.sdk.constant.SocialuniAccountProviderType;
import com.socialuni.sdk.constant.platform.PlatformType;
import com.socialuni.sdk.dao.DO.user.SocialUserAccountDO;
import com.socialuni.sdk.dao.repository.user.SocialUserAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class SocialUserAccountStore {
    @Resource
    SocialUserAccountRepository socialUserAccountRepository;

    public SocialUserAccountDO getAccountByUnionId(SocialProviderLoginQO loginQO, UniUnionIdRO uniUnionIdRO) {
        String provider = loginQO.getProvider();
        String unionId = uniUnionIdRO.getUnionid();
        String platform = loginQO.getPlatform();
        SocialUserAccountDO socialUserAccountDO = null;
        //unionId不为null才查询
        if (StringUtils.isNotEmpty(unionId)) {
            log.info("查询渠道账号platform:{}，provider:{}，unionId:{}", platform, provider, unionId);
            socialUserAccountDO = socialUserAccountRepository.findByProviderAndUnionId(provider, unionId);
        }
        //根据unionId找不到，代表没有开通开放平台或者没注册
        if (socialUserAccountDO == null) {
            //通过openId查询试试
            String openId = uniUnionIdRO.getOpenid();
            if (StringUtils.isEmpty(openId)) {
                throw new SocialParamsException("错误的渠道openId");
            }
            if (PlatformType.app.equals(platform)) {
                socialUserAccountDO = socialUserAccountRepository.findByProviderAndAppOpenId(provider, openId);
            } else if (PlatformType.mp.equals(platform)) {
                socialUserAccountDO = socialUserAccountRepository.findByProviderAndMpOpenId(provider, openId);
            } else if (PlatformType.h5.equals(platform)) {
                socialUserAccountDO = socialUserAccountRepository.findByProviderAndH5OpenId(provider, openId);
            } else {
                throw new SocialParamsException(PlatformType.notSupportTypeErrorMsg + ":" + platform);
            }
        }
        return socialUserAccountDO;
    }


    public SocialUserAccountDO getSocialAccountByUnionId(String unionId) {
        SocialUserAccountDO socialUserAccountDO = socialUserAccountRepository.findByProviderAndUnionId(SocialuniAccountProviderType.socialuni, unionId);
        //根据unionId找不到，代表没有开通开放平台或者没注册
        //目前只有查询用户详情使用，查询这个社交平台的用户是否在本平台注册了，在本平台注册，则使用本平台的用户信息
        /*if (socialUserAccountDO == null) {
            throw new SocialParamsException("不存在的用户");
        }*/
        return socialUserAccountDO;
    }
}
