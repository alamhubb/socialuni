package com.socialuni.sdk.entity;

import com.socialuni.sdk.entity.user.SocialPhoneLoginEntity;
import com.socialuni.sdk.entity.user.SocialUserEntity;
import com.socialuni.sdk.entity.user.SocialUserPhoneEntity;
import com.socialuni.sdk.factory.RO.user.CenterMineUserDetailROFactory;
import com.socialuni.sdk.manage.SocialUserFansDetailManage;
import com.socialuni.sdk.manage.SocialUserManage;
import com.socialuni.sdk.manage.TokenManage;
import com.socialuni.sdk.manage.phone.SocialUserPhoneManage;
import com.socialuni.sdk.model.DO.UniContentUnionIdDO;
import com.socialuni.sdk.model.DO.user.SocialTokenDO;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.model.QO.user.SocialProviderLoginQO;
import com.socialuni.sdk.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.sdk.model.RO.user.login.SocialLoginRO;
import com.socialuni.sdk.utils.SocialUserUtil;
import com.socialuni.sdk.utils.UnionIdDbUtil;
import com.socialuni.social.exception.SocialParamsException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UniUserRegistryDomain {
    @Resource
    private SocialUserFansDetailManage socialUserFansDetailManage;
    @Resource
    private SocialUserManage socialUserManage;
    @Resource
    SocialUserPhoneManage socialUserPhoneManage;
    @Resource
    SocialPhoneLoginEntity socialPhoneLoginEntity;
    @Resource
    SocialUserPhoneEntity socialUserPhoneEntity;
    @Resource
    SocialUserEntity socialUserEntity;
    @Resource
    TokenManage tokenManage;

    //根据渠道登录信息获取user，支持social比commonUserDomain
    //这个单独出来是因为区分了基础provider和社交，这个单独增加了对社交渠道的支持
    public SocialLoginRO<CenterMineUserDetailRO> registryUser(SocialProviderLoginQO loginQO) {
        SocialUserDO mineUser = null;
        //如果已经注册过
        String phoneNum = loginQO.getPhoneNum();

        String userUid = loginQO.getUnionId();
        if (StringUtils.isEmpty(userUid)) {
            throw new SocialParamsException("用户唯一标识不能为空");
        }

        UniContentUnionIdDO uniContentUnionIdDO = UnionIdDbUtil.getUnionByUidAllowNull(userUid);
        //新注册
        if (uniContentUnionIdDO == null) {
            //为空，则只注册
            if (StringUtils.isEmpty(phoneNum)) {
                mineUser = socialUserEntity.createUserAndDetail(loginQO);
            } else {
                //不为空，则用手机号注册
                socialUserPhoneManage.checkBindPhoneNumHasBind(phoneNum);
                mineUser = socialUserEntity.createUserAndDetail(loginQO);
                socialUserPhoneManage.createUserPhoneNum(mineUser.getUnionId(), "86", phoneNum);
            }
        } else {
            //已注册，更新token
            mineUser = SocialUserUtil.getUserNotNull(uniContentUnionIdDO.getId());
        }
        SocialTokenDO socialUserTokenDO = tokenManage.create(mineUser.getUnionId());

        CenterMineUserDetailRO userDetailRO = CenterMineUserDetailROFactory.getMineUserDetail(mineUser);

        return new SocialLoginRO<>(socialUserTokenDO.getToken(), userDetailRO);
        //如果uid存在，则代表用户注册过
    }
}
