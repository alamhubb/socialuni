package com.socialuni.social.user.sdk.logic.manage;

import com.socialuni.social.common.api.constant.PlatformType;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.api.utils.RequestUtil;
import com.socialuni.social.user.sdk.constant.SocialuniAccountProviderType;
import com.socialuni.social.common.sdk.dao.DO.SocialUserPlatformAccountDO;
import com.socialuni.social.user.sdk.model.QO.SocialProviderLoginQO;
import com.socialuni.social.user.sdk.model.RO.UniUnionIdRO;
import com.socialuni.social.common.sdk.dao.repository.SocialUserPlatformAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class SocialUserAccountStore {
    @Resource
    SocialUserPlatformAccountRepository socialUserPlatformAccountRepository;

    public SocialUserPlatformAccountDO getAccountByUnionId(SocialProviderLoginQO loginQO, UniUnionIdRO uniUnionIdRO) {
        String provider = loginQO.getProvider();
        String unionId = uniUnionIdRO.getUnionid();
        String platform = loginQO.getPlatform();
        if (platform == null) {
            platform = RequestUtil.getPlatform();
        }
        SocialUserPlatformAccountDO socialUserAccountDO = null;
        //unionId不为null才查询
        if (StringUtils.isNotEmpty(unionId)) {
//            log.info("查询渠道账号platform:{}，provider:{}，unionId:{}", platform, provider, unionId);
            socialUserAccountDO = socialUserPlatformAccountRepository.findByProviderAndUnionId(provider, unionId);
        }
        //根据unionId找不到，代表没有开通开放平台或者没注册
        if (socialUserAccountDO == null) {
            //通过openId查询试试
            String openId = uniUnionIdRO.getOpenid();
            if (StringUtils.isEmpty(openId)) {
                throw new SocialParamsException("错误的渠道openId");
            }
            if (PlatformType.app.equals(platform)) {
                socialUserAccountDO = socialUserPlatformAccountRepository.findByProviderAndAppOpenId(provider, openId);
            } else if (PlatformType.mp.equals(platform)) {
                socialUserAccountDO = socialUserPlatformAccountRepository.findByProviderAndMpOpenId(provider, openId);
            } else if (PlatformType.h5.equals(platform)) {
                socialUserAccountDO = socialUserPlatformAccountRepository.findByProviderAndH5OpenId(provider, openId);
            } else {
                throw new SocialParamsException(PlatformType.notSupportTypeErrorMsg + ":" + platform);
            }
        }
        return socialUserAccountDO;
    }


    public SocialUserPlatformAccountDO getSocialAccountByUnionId(String unionId) {
        SocialUserPlatformAccountDO socialUserAccountDO = socialUserPlatformAccountRepository.findByProviderAndUnionId(SocialuniAccountProviderType.socialuni, unionId);
        //根据unionId找不到，代表没有开通开放平台或者没注册
        //目前只有查询用户详情使用，查询这个社交平台的用户是否在本平台注册了，在本平台注册，则使用本平台的用户信息
        /*if (socialUserAccountDO == null) {
            throw new SocialParamsException("不存在的用户");
        }*/
        return socialUserAccountDO;
    }
}
