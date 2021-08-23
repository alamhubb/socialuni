package com.socialuni.social.sdk.manage;

import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.sdk.constant.platform.PlatformType;
import com.socialuni.social.sdk.constant.platform.ProviderType;
import com.socialuni.social.exception.UniSdkException;
import com.socialuni.social.entity.model.DO.user.SocialUserAccountDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.sdk.model.UniUnionIdRO;
import com.socialuni.social.sdk.repository.SocialUserAccountRepository;
import com.socialuni.social.sdk.store.SocialUserAccountStore;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class SocialUserAccountManage {
    @Resource
    SocialUserAccountRepository socialUserAccountRepository;
    @Resource
    SocialUserAccountStore socialUserAccountStore;

    public void updateSessionKey(String provider, String sessionKey, Integer mineUserId) {
        SocialUserAccountDO socialUserAccountDO = socialUserAccountRepository.findByProviderAndUserId(provider, mineUserId);
        //更新数据库的key
        socialUserAccountDO.setUpdateTime(new Date());
        //再次更新下sessionkey
        socialUserAccountDO.setSessionKey(sessionKey);
        socialUserAccountDO = socialUserAccountRepository.save(socialUserAccountDO);
    }

    @Async
    public void checkOrCreate(Integer userId, SocialProviderLoginQO loginQO, UniUnionIdRO uniUnionIdRO) {
        SocialUserAccountDO socialUserAccountDO = socialUserAccountStore.getAccountByUnionId(loginQO, uniUnionIdRO);
        if (socialUserAccountDO == null) {
            this.create(userId, loginQO, uniUnionIdRO);
        }
    }

    //两个相似逻辑引用，但逻辑不相同，无法通用，所以两个地方调用
    public SocialUserAccountDO create(Integer userId, SocialProviderLoginQO loginQO, UniUnionIdRO uniUnionIdRO) {
        SocialUserAccountDO socialUserAccountDO = new SocialUserAccountDO();
        socialUserAccountDO.setUserId(userId);
        socialUserAccountDO.setPlatform(loginQO.getPlatform());
        socialUserAccountDO.setProvider(loginQO.getProvider());
        String openId = uniUnionIdRO.getOpenid();
        if (StringUtils.isEmpty(openId)) {
            throw new UniSdkException("openId为空");
        }
        if (PlatformType.mp.equals(loginQO.getPlatform())) {
            //相同都为unionid
            socialUserAccountDO.setMpOpenId(openId);
        } else {
            socialUserAccountDO.setAppOpenId(openId);
        }
        if (!StringUtils.isEmpty(uniUnionIdRO.getUnionid())) {
            socialUserAccountDO.setUnionId(uniUnionIdRO.getUnionid());
        }
        socialUserAccountDO.setSessionKey(uniUnionIdRO.getSession_key());
        socialUserAccountRepository.save(socialUserAccountDO);
        return socialUserAccountDO;
    }
}
