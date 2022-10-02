package com.socialuni.social.sdk.logic.entity;

import com.socialuni.social.sdk.logic.factory.RO.user.SocialuniMineUserDetailROFactory;
import com.socialuni.social.sdk.logic.entity.user.SocialPhoneLoginEntity;
import com.socialuni.social.sdk.logic.entity.user.SocialUserEntity;
import com.socialuni.social.sdk.logic.entity.user.SocialUserPhoneEntity;
import com.socialuni.social.sdk.logic.manage.SocialUserFansDetailManage;
import com.socialuni.social.sdk.logic.manage.SocialUserManage;
import com.socialuni.social.sdk.logic.manage.TokenManage;
import com.socialuni.social.sdk.logic.manage.phone.SocialUserPhoneManage;
import com.socialuni.social.sdk.dao.DO.SocialuniUnionIdDO;
import com.socialuni.social.sdk.dao.DO.user.SocialTokenDO;
import com.socialuni.social.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.social.sdk.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.sdk.model.RO.user.SocialuniMineUserDetailRO;
import com.socialuni.social.sdk.model.RO.user.login.SocialLoginRO;
import com.socialuni.social.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.sdk.utils.SocialuniUnionIdUtil;
import com.socialuni.social.common.exception.exception.SocialParamsException;
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
    public SocialLoginRO<SocialuniMineUserDetailRO> registryUser(SocialProviderLoginQO loginQO) {
        SocialuniUserDO mineUser = null;
        //如果已经注册过
        String phoneNum = loginQO.getPhoneNum();

        String userUid = loginQO.getUnionId();
        if (StringUtils.isEmpty(userUid)) {
            throw new SocialParamsException("用户唯一标识不能为空");
        }

        SocialuniUnionIdDO uniContentUnionIdDO = SocialuniUnionIdUtil.getUnionByUuidAllowNull(userUid);
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
            mineUser = SocialuniUserUtil.getUserNotNull(uniContentUnionIdDO.getId());
        }
        SocialTokenDO socialUserTokenDO = tokenManage.create(mineUser.getUnionId());

        SocialuniMineUserDetailRO userDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(mineUser);

        return new SocialLoginRO<>(socialUserTokenDO.getToken(), userDetailRO);
        //如果uid存在，则代表用户注册过
    }
}