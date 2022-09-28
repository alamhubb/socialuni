package com.socialuni.social.sdk.logic.entity;

import com.socialuni.social.tance.entity.DevAccountDO;
import com.socialuni.social.sdk.dao.DO.user.SocialUserPhoneDO;
import com.socialuni.social.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.social.sdk.logic.entity.user.SocialUserPhoneEntity;
import com.socialuni.social.sdk.dao.redis.SocialUserPhoneRedis;
import com.socialuni.social.sdk.utils.SocialuniUserUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

//授权用户信息
@Component
public class SocialuniDevAccountEntity {
    @Resource
    private SocialUserPhoneRedis socialUserPhoneRedis;
    @Resource
    private SocialUserPhoneEntity socialUserPhoneEntity;

    public SocialuniUserDO getOrCreateDevAccountUserDO(DevAccountDO devAccountDO) {
        String phoneNum = devAccountDO.getPhoneNum();

        SocialUserPhoneDO socialUserPhoneDO = socialUserPhoneRedis.findByPhoneNum(phoneNum);

        SocialuniUserDO mineUser;
        if (socialUserPhoneDO == null) {
            //如果没注册账号，则直接注册
            //            throw new SocialBusinessException("默认使用开发者账号绑定的手机号对应的清池账号进行测试，请登录清池注册后测试");
            mineUser = socialUserPhoneEntity.createUserPhoneEntity(phoneNum);
        } else {
            mineUser = SocialuniUserUtil.getUserNotNull(socialUserPhoneDO.getUserId());
        }
        return mineUser;
    }
}
