package com.socialuni.social.sdk.logic.entity;


import com.socialuni.social.tance.sdk.model.DevAccountModel;
import com.socialuni.social.sdk.logic.manage.ThirdUserAuthManage;
import com.socialuni.social.sdk.logic.manage.ThirdUserManage;
import com.socialuni.social.sdk.logic.manage.ThirdUserTokenManage;
import com.socialuni.social.sdk.dao.DO.dev.ThirdUserDO;
import com.socialuni.social.sdk.dao.DO.user.SocialUserPhoneDO;
import com.socialuni.social.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.social.common.exception.exception.SocialBusinessException;
import com.socialuni.social.sdk.model.RO.user.SocialuniMineUserDetailRO;
import com.socialuni.social.sdk.constant.AuthType;
import com.socialuni.social.sdk.dao.redis.SocialUserPhoneRedis;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

//授权用户信息
@Service
public class AuthThirdUserEntity {
    @Resource
    ThirdUserTokenManage thirdUserTokenManage;
    @Resource
    private ThirdUserManage thirdUserManage;
    @Resource
    private ThirdUserAuthManage thirdUserAuthManage;
    @Resource
    SocialUserPhoneRedis socialUserPhoneRedis;

    /*public ThirdUserTokenDO authThirdUser(DevAccountDO threeDevDO, UserDO userDO, String oAuthType) {
        //只是记录一个授权记录
        ThirdUserDO threeUserDO = thirdUserManage.getOrCreate(threeDevDO.getId(), userDO.getId());

        thirdUserAuthManage.getOrCreate(threeUserDO, oAuthType);
        //授权成功
        //token有效期三个月
        //生成userToken
        ThirdUserTokenDO tokenDO = thirdUserTokenManage.create(userDO.getId(), threeDevDO.getId());
        return tokenDO;
    }*/

    //登录和 绑定手机号和微信手机号三个地方使用
    public SocialuniMineUserDetailRO authThirdUser(SocialuniUserDO mineUser, String authType, DevAccountModel devAccountModel, SocialuniMineUserDetailRO socialMineUserDetailRO) {
        //只是记录一个授权记录
        ThirdUserDO threeUserDO = thirdUserManage.getOrCreate(devAccountModel.getId(), mineUser.getUnionId(), socialMineUserDetailRO.getId());

        if (AuthType.phone.equals(authType)) {
            thirdUserAuthManage.getOrCreate(threeUserDO, AuthType.user);
            //只有为手机号授权时才返回手机号
            SocialUserPhoneDO socialUserPhoneDO = socialUserPhoneRedis.findUserPhoneByUserId(mineUser.getUnionId());
            if (socialUserPhoneDO == null) {
                throw new SocialBusinessException("用户未绑定手机号，请先绑定手机号");
            }
            socialMineUserDetailRO.setPhoneNum(socialUserPhoneDO.getPhoneNum());
        }
        thirdUserAuthManage.getOrCreate(threeUserDO, authType);

        return socialMineUserDetailRO;
    }
}
