package com.socialuni.social.sdk.store;

import com.socialuni.social.sdk.constant.ProviderLoginType;
import com.socialuni.social.sdk.constant.platform.PlatformType;
import com.socialuni.entity.model.DO.user.SocialUserAccountDO;
import com.socialuni.social.sdk.model.UniUnionIdRO;
import com.socialuni.social.sdk.repository.SocialUserAccountRepository;
import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialUserAccountStore {
    @Resource
    SocialUserAccountRepository socialUserAccountRepository;

    public SocialUserAccountDO getAccountByUnionId(SocialProviderLoginQO loginQO, UniUnionIdRO uniUnionIdRO) {
        String provider = loginQO.getProvider();
        String unionId = uniUnionIdRO.getUnionid();
        String platform = loginQO.getPlatform();

        SocialUserAccountDO socialUserAccountDO = socialUserAccountRepository.findByProviderAndUnionId(provider, unionId);

        //根据unionId找不到，代表没有开通开放平台或者没注册
        if (socialUserAccountDO == null) {
            //通过openId查询试试
            String openId = uniUnionIdRO.getOpenid();
            if (PlatformType.app.equals(platform)) {
                socialUserAccountDO = socialUserAccountRepository.findByProviderAndAppOpenId(provider, openId);
            } else {
                socialUserAccountDO = socialUserAccountRepository.findByProviderAndMpOpenId(provider, openId);
            }
        }
        return socialUserAccountDO;
    }


    public SocialUserAccountDO getSocialAccountByUnionId(String unionId) {
        SocialUserAccountDO socialUserAccountDO = socialUserAccountRepository.findByProviderAndUnionId(ProviderLoginType.socialuni, unionId);
        //根据unionId找不到，代表没有开通开放平台或者没注册
        //目前只有查询用户详情使用，查询这个社交平台的用户是否在本平台注册了，在本平台注册，则使用本平台的用户信息
        /*if (socialUserAccountDO == null) {
            throw new SocialParamsException("不存在的用户");
        }*/
        return socialUserAccountDO;
    }
}
