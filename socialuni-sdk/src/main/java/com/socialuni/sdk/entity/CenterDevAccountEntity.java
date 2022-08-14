package com.socialuni.sdk.entity;

import com.socialuni.sdk.model.DO.dev.DevAccountDO;
import com.socialuni.sdk.model.DO.user.SocialUserPhoneDO;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.entity.user.SocialUserPhoneEntity;
import com.socialuni.sdk.redis.SocialUserPhoneRedis;
import com.socialuni.sdk.utils.SocialUserUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

//授权用户信息
@Component
public class CenterDevAccountEntity {
    @Resource
    private SocialUserPhoneRedis socialUserPhoneRedis;
    @Resource
    private SocialUserPhoneEntity socialUserPhoneEntity;

    public SocialUserDO getOrCreateDevAccountUserDO(DevAccountDO devAccountDO) {
        String phoneNum = devAccountDO.getPhoneNum();

        SocialUserPhoneDO socialUserPhoneDO = socialUserPhoneRedis.findByPhoneNum(phoneNum);

        SocialUserDO mineUser;
        if (socialUserPhoneDO == null) {
            //如果没注册账号，则直接注册
            //            throw new SocialBusinessException("默认使用开发者账号绑定的手机号对应的清池账号进行测试，请登录清池注册后测试");
            mineUser = socialUserPhoneEntity.createUserPhoneEntity(phoneNum);
        } else {
            mineUser = SocialUserUtil.getUserNotNull(socialUserPhoneDO.getUserId());
        }
        return mineUser;
    }
}
