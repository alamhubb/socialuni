package com.socialuni.sdk.store;

import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;
import com.socialuni.sdk.constant.ProviderLoginType;
import com.socialuni.sdk.exception.SocialParamsException;
import com.socialuni.sdk.model.DO.user.SocialUserAccountDO;
import com.socialuni.sdk.repository.SocialUserAccountRepository;
import com.socialuni.sdk.constant.platform.PlatformType;
import com.socialuni.sdk.model.UniUnionIdRO;
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
            if (platform.equals(PlatformType.app)) {
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
        if (socialUserAccountDO == null) {
            throw new SocialParamsException("不存在的用户");
        }
        return socialUserAccountDO;
    }
}
