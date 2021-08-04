package com.socialuni.sdk.manage;

import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;
import com.socialuni.sdk.constant.platform.PlatformType;
import com.socialuni.sdk.constant.platform.ProviderType;
import com.socialuni.sdk.exception.UniSdkException;
import com.socialuni.sdk.model.DO.user.SocialUserAccountDO;
import com.socialuni.sdk.model.DO.user.UserDO;
import com.socialuni.sdk.model.UniUnionIdRO;
import com.socialuni.sdk.repository.SocialUserAccountRepository;
import com.socialuni.sdk.store.SocialUserAccountStore;
import org.apache.commons.lang.StringUtils;
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

    public SocialUserAccountDO updateSessionKey(String sessionKey, Integer mineUserId) {
        SocialUserAccountDO socialUserAccountDO = socialUserAccountRepository.findByProviderAndUserId(ProviderType.wx, mineUserId);
        //更新数据库的key
        socialUserAccountDO.setUpdateTime(new Date());
        //再次更新下sessionkey
        socialUserAccountDO.setSessionKey(sessionKey);
        socialUserAccountDO = socialUserAccountRepository.save(socialUserAccountDO);
        return socialUserAccountDO;
    }

    @Async
    public void checkOrCreate(UserDO user, SocialProviderLoginQO loginQO, UniUnionIdRO uniUnionIdRO) {
        SocialUserAccountDO socialUserAccountDO = socialUserAccountStore.getAccountByUnionId(loginQO, uniUnionIdRO);
        if (socialUserAccountDO == null) {
            this.create(user, loginQO, uniUnionIdRO);
        }
    }

    public SocialUserAccountDO create(UserDO user, SocialProviderLoginQO loginQO, UniUnionIdRO uniUnionIdRO) {
        SocialUserAccountDO socialUserAccountDO = new SocialUserAccountDO();
        socialUserAccountDO.setUserId(user.getId());
        socialUserAccountDO.setPlatform(loginQO.getPlatform());
        socialUserAccountDO.setProvider(loginQO.getProvider());
        String openId = uniUnionIdRO.getOpenid();
        if (StringUtils.isEmpty(openId)) {
            throw new UniSdkException("openId为空");
        }
        if (PlatformType.mp.equals(loginQO.getPlatform())) {
            //相同都为unionid
            socialUserAccountDO.setMpOpenId(openId);
            socialUserAccountDO.setAppOpenId("");
        } else {
            socialUserAccountDO.setAppOpenId(openId);
            socialUserAccountDO.setMpOpenId("");
        }
        if (StringUtils.isEmpty(uniUnionIdRO.getUnionid())) {
            socialUserAccountDO.setUnionId("");
        } else {
            socialUserAccountDO.setUnionId(uniUnionIdRO.getUnionid());
        }
        socialUserAccountDO.setSessionKey(uniUnionIdRO.getSession_key());
        socialUserAccountRepository.save(socialUserAccountDO);
        return socialUserAccountDO;
    }
}
