package com.socialuni.center.web.manage;

import com.socialuni.center.web.model.UniUnionIdRO;
import com.socialuni.center.web.model.DO.user.SocialUserAccountDO;
import com.socialuni.social.exception.SocialParamsException;
import com.socialuni.center.web.model.QO.user.SocialProviderLoginQO;
import com.socialuni.center.web.constant.GenderTypeNumEnum;
import com.socialuni.center.web.constant.platform.PlatformType;
import com.socialuni.center.web.repository.user.SocialUserAccountRepository;
import com.socialuni.center.web.store.SocialUserAccountStore;
import org.apache.commons.lang3.StringUtils;
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
//        socialUserAccountDO.setDevId(loginQO.getDevId());
        socialUserAccountDO.setPlatform(loginQO.getPlatform());
        socialUserAccountDO.setProvider(loginQO.getProvider());

        socialUserAccountDO.setNickname(loginQO.getNickName());
        socialUserAccountDO.setAvatar(loginQO.getAvatarUrl());
        socialUserAccountDO.setGender(GenderTypeNumEnum.getNameByValue(loginQO.getGender()));

        String openId = uniUnionIdRO.getOpenid();
        if (StringUtils.isEmpty(openId)) {
            throw new SocialParamsException("openId为空");
        }
        if (PlatformType.mp.equals(loginQO.getPlatform())) {
            //相同都为unionid
            socialUserAccountDO.setMpOpenId(openId);
        } else if (PlatformType.app.equals(loginQO.getPlatform())) {
            socialUserAccountDO.setAppOpenId(openId);
        } else if (PlatformType.h5.equals(loginQO.getPlatform())) {
            socialUserAccountDO.setH5OpenId(openId);
        } else {
            throw new SocialParamsException("不支持的渠道类型：" + loginQO.getPlatform());
        }
        if (!StringUtils.isEmpty(uniUnionIdRO.getUnionid())) {
            socialUserAccountDO.setUnionId(uniUnionIdRO.getUnionid());
        }
        socialUserAccountDO.setSessionKey(uniUnionIdRO.getSession_key());
        socialUserAccountRepository.save(socialUserAccountDO);
        return socialUserAccountDO;
    }
}
