package com.socialuni.center.web.entity;


import com.socialuni.api.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.center.web.factory.RO.user.CenterMineUserDetailROFactory;
import com.socialuni.center.web.manage.ThirdUserAuthManage;
import com.socialuni.center.web.manage.ThirdUserManage;
import com.socialuni.center.web.manage.ThirdUserTokenManage;
import com.socialuni.center.web.model.DO.ThirdUserDO;
import com.socialuni.center.web.utils.DevAccountUtils;
import com.socialuni.entity.model.DevAccountDO;
import com.socialuni.social.entity.model.DO.user.SocialUserPhoneDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.exception.SocialBusinessException;
import com.socialuni.social.sdk.constant.AuthType;
import com.socialuni.social.sdk.store.SocialUserPhoneStore;
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
    SocialUserPhoneStore socialUserPhoneStore;

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

    public CenterMineUserDetailRO authThirdUser(UserDO mineUser, String authType) {
        DevAccountDO threeDevDO = DevAccountUtils.getDevAccount();

        //只是记录一个授权记录
        ThirdUserDO threeUserDO = thirdUserManage.getOrCreate(threeDevDO.getId(), mineUser.getId());

        thirdUserAuthManage.getOrCreate(threeUserDO, authType);
        //授权成功
        //token有效期三个月
        //生成userToken
        CenterMineUserDetailRO centerUserDetailRO = CenterMineUserDetailROFactory.getMineUserDetail(mineUser);

        //只是记录一个授权记录
        //授权成功
        //token有效期三个月
        //生成userToken
        //只有为手机号授权时才返回手机号
        if (authType.equals(AuthType.phone)) {
            SocialUserPhoneDO socialUserPhoneDO = socialUserPhoneStore.findByUserId(mineUser.getId());
            if (socialUserPhoneDO == null) {
                throw new SocialBusinessException("用户未绑定手机号，请先绑定手机号");
            }
            centerUserDetailRO.setPhoneNum(socialUserPhoneDO.getPhoneNum());
        } else {
            centerUserDetailRO.setPhoneNum(null);
        }
        return centerUserDetailRO;
    }
}
