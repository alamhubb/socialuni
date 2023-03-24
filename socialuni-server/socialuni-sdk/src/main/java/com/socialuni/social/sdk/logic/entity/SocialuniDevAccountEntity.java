package com.socialuni.social.sdk.logic.entity;

import com.socialuni.social.user.sdk.redis.SocialUserPhoneRedis;
import com.socialuni.social.user.sdk.logic.entity.SocialUserPhoneEntity;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.tance.sdk.model.DevAccountModel;
import com.socialuni.social.user.sdk.model.DO.SocialUserPhoneDo;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

//授权用户信息
@Component
public class SocialuniDevAccountEntity {
    @Resource
    private SocialUserPhoneRedis socialUserPhoneRedis;
    @Resource
    private SocialUserPhoneEntity socialUserPhoneEntity;

    public SocialuniUserDo getOrCreateDevAccountUserDO(DevAccountModel devAccountModel) {
        String phoneNum = devAccountModel.getPhoneNum();

        SocialUserPhoneDo SocialUserPhoneDo = socialUserPhoneRedis.findByPhoneNum(phoneNum);

        SocialuniUserDo mineUser;
        if (SocialUserPhoneDo == null) {
            //如果没注册账号，则直接注册
            //            throw new SocialBusinessException("默认使用开发者账号绑定的手机号对应的清池账号进行测试，请登录清池注册后测试");
            mineUser = socialUserPhoneEntity.createUserPhoneEntity(phoneNum);
        } else {
            mineUser = SocialuniUserUtil.getUserNotNull(SocialUserPhoneDo.getUserId());
        }
        return mineUser;
    }
}
