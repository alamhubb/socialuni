package com.socialuni.center.web.entity;

import com.socialuni.social.entity.model.DO.dev.DevAccountDO;
import com.socialuni.social.entity.model.DO.user.SocialUserPhoneDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.center.web.entity.user.SocialUserPhoneEntity;
import com.socialuni.center.web.redis.SocialUserPhoneRedis;
import com.socialuni.center.web.utils.SocialUserUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

//授权用户信息
@Component
public class CenterDevAccountEntity {
    @Resource
    private SocialUserPhoneRedis socialUserPhoneRedis;
    @Resource
    private SocialUserPhoneEntity socialUserPhoneEntity;

    public UserDO getOrCreateDevAccountUserDO(DevAccountDO devAccountDO) {
        String phoneNum = devAccountDO.getPhoneNum();

        SocialUserPhoneDO socialUserPhoneDO = socialUserPhoneRedis.findByPhoneNum(phoneNum);

        UserDO mineUser;
        if (socialUserPhoneDO == null) {
            //如果没注册账号，则直接注册
            //            throw new SocialBusinessException("默认使用开发者账号绑定的手机号对应的清池账号进行测试，请登录清池注册后测试");
            mineUser = socialUserPhoneEntity.createUserPhoneEntity(phoneNum);
        } else {
            mineUser = SocialUserUtil.getNotNull(socialUserPhoneDO.getUserId());
        }
        return mineUser;
    }
}
