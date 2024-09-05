package com.socialuni.social.app.logic.domain;

import com.socialuni.social.app.factory.SocialuniMineUserDetailROFactory;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.api.model.user.SocialuniUserRO;
import com.socialuni.social.user.sdk.dao.DO.SocialuniTokenDO;
import com.socialuni.social.tance.dev.facade.SocialuniUnionIdFacede;
import com.socialuni.social.user.sdk.logic.entity.SocialPhoneLoginEntity;
import com.socialuni.social.user.sdk.logic.entity.SocialUserEntity;
import com.socialuni.social.user.sdk.logic.entity.SocialUserPhoneEntity;
import com.socialuni.social.follow.logic.manage.SocialUserFansDetailManage;
import com.socialuni.social.user.sdk.logic.manage.SocialUserManage;
import com.socialuni.social.user.sdk.logic.manage.SocialuniTokenManage;
import com.socialuni.social.user.sdk.logic.manage.SocialUserPhoneManage;
import com.socialuni.social.user.sdk.model.QO.SocialProviderLoginQO;
import com.socialuni.social.app.model.SocialuniMineUserDetailRO;
import com.socialuni.social.user.sdk.model.RO.login.SocialLoginRO;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.tance.dev.model.SocialuniUnionIdModler;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
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
    SocialuniTokenManage tokenManage;

    //根据渠道登录信息获取user，支持social比commonUserDomain
    //这个单独出来是因为区分了基础provider和社交，这个单独增加了对社交渠道的支持
    public SocialLoginRO<SocialuniUserRO> registryUser(SocialProviderLoginQO loginQO) {
        SocialuniUserDo mineUser = null;
        //如果已经注册过
        String phoneNum = loginQO.getPhoneNum();

        String userUid = loginQO.getUnionId();
        if (StringUtils.isEmpty(userUid)) {
            throw new SocialParamsException("用户唯一标识不能为空");
        }

        SocialuniUnionIdModler uniContentUnionIdDO = SocialuniUnionIdFacede.getUnionByUuidAllowNull(userUid);
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
            mineUser = SocialuniUserUtil.getUserNotNull(uniContentUnionIdDO.getUnionId());
        }
        SocialuniTokenDO socialUserTokenDO = tokenManage.create(mineUser.getUnionId());

        SocialuniMineUserDetailRO userDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(mineUser);

        return new SocialLoginRO<>(socialUserTokenDO.getToken(), userDetailRO);
        //如果uid存在，则代表用户注册过
    }
}
