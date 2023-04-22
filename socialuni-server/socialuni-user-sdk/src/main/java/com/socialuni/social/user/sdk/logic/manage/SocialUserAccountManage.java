package com.socialuni.social.user.sdk.logic.manage;

import com.socialuni.social.common.api.constant.PlatformType;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.api.utils.RequestUtil;
import com.socialuni.social.user.sdk.constant.GenderTypeNumEnum;
import com.socialuni.social.common.sdk.dao.DO.SocialUserAccountDO;
import com.socialuni.social.user.sdk.model.QO.SocialProviderLoginQO;
import com.socialuni.social.user.sdk.model.RO.UniUnionIdRO;
import com.socialuni.social.common.sdk.dao.repository.SocialUserAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
@Slf4j
public class SocialUserAccountManage {
    @Resource
    SocialUserAccountRepository socialUserAccountRepository;
    @Resource
    SocialUserAccountStore socialUserAccountStore;

    public SocialUserAccountDO updateSessionKey(Integer mineUserId, String provider, String sessionKey) {
        SocialUserAccountDO socialUserAccountDO = socialUserAccountRepository.findByProviderAndUserId(provider, mineUserId);
        //更新数据库的key
        socialUserAccountDO.setUpdateTime(new Date());
        //再次更新下sessionkey
        socialUserAccountDO.setSessionKey(sessionKey);
        socialUserAccountDO = socialUserAccountRepository.save(socialUserAccountDO);
        return socialUserAccountDO;
    }

    public SocialUserAccountDO checkOrCreateOrUpdate(Integer userId, SocialProviderLoginQO loginQO, UniUnionIdRO uniUnionIdRO) {
        SocialUserAccountDO socialUserAccountDO = socialUserAccountStore.getAccountByUnionId(loginQO, uniUnionIdRO);
        if (socialUserAccountDO == null) {
            return this.create(userId, loginQO, uniUnionIdRO);
        } else {
            return this.updateSessionKey(userId, loginQO.getProvider(), uniUnionIdRO.getSession_key());
        }
    }

    //两个相似逻辑引用，但逻辑不相同，无法通用，所以两个地方调用
    public SocialUserAccountDO create(Integer userId, SocialProviderLoginQO loginQO, UniUnionIdRO uniUnionIdRO) {
        SocialUserAccountDO socialUserAccountDO = new SocialUserAccountDO();
        socialUserAccountDO.setUserId(userId);
//        socialUserAccountDO.setDevId(loginQO.getDevId());
        String platform = loginQO.getPlatform();
        if (platform == null) {
            platform = RequestUtil.getPlatform();
        }

        socialUserAccountDO.setPlatform(platform);
        socialUserAccountDO.setProvider(loginQO.getProvider());

        socialUserAccountDO.setNickname(loginQO.getNickName());
        socialUserAccountDO.setAvatar(loginQO.getAvatarUrl());
        socialUserAccountDO.setGender(GenderTypeNumEnum.getNameByValue(loginQO.getGender()));

        String openId = uniUnionIdRO.getOpenid();



        if (StringUtils.isEmpty(openId)) {
            throw new SocialParamsException("openId为空");
        }
        if (PlatformType.mp.equals(platform)) {
            //相同都为unionid
            socialUserAccountDO.setMpOpenId(openId);
        } else if (PlatformType.app.equals(platform)) {
            socialUserAccountDO.setAppOpenId(openId);
        } else if (PlatformType.h5.equals(platform)) {
            socialUserAccountDO.setH5OpenId(openId);
        } else {
            throw new SocialParamsException("不支持的渠道类型：" + platform);
        }
        if (!StringUtils.isEmpty(uniUnionIdRO.getUnionid())) {
            socialUserAccountDO.setUnionId(uniUnionIdRO.getUnionid());
        }
        socialUserAccountDO.setSessionKey(uniUnionIdRO.getSession_key());
        socialUserAccountRepository.save(socialUserAccountDO);
        return socialUserAccountDO;
    }
}
