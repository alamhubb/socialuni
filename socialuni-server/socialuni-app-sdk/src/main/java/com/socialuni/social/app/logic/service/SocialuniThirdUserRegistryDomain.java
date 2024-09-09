package com.socialuni.social.app.logic.service;

import com.socialuni.social.app.factory.SocialuniMineUserDetailROFactory;
import com.socialuni.social.common.api.model.user.SocialuniUserRO;
import com.socialuni.social.tance.dev.facade.SocialuniUnionIdFacede;
import com.qingchi.qing.user.dao.model.SocialUserPhoneDo;
import com.socialuni.social.user.sdk.dao.DO.SocialuniTokenDO;
import com.socialuni.social.user.sdk.logic.entity.SocialUserEntity;
import com.socialuni.social.user.sdk.logic.manage.SocialuniTokenManage;
import com.socialuni.social.user.sdk.logic.manage.SocialUserPhoneManage;
import com.socialuni.social.user.sdk.model.QO.SocialProviderLoginQO;
import com.socialuni.social.app.model.SocialuniMineUserDetailRO;
import com.socialuni.social.user.sdk.model.RO.login.SocialLoginRO;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialuniThirdUserRegistryDomain {
    @Resource
    SocialUserPhoneManage socialUserPhoneManage;
    @Resource
    SocialUserEntity socialUserEntity;
    @Resource
    SocialuniTokenManage tokenManage;

    //中心注册user
    //根据渠道登录信息获取user，支持social比commonUserDomain
    //这个单独出来是因为区分了基础provider和社交，这个单独增加了对社交渠道的支持
    public SocialLoginRO<SocialuniUserRO> registryUser(SocialProviderLoginQO loginQO) {
        //1. 根据手机号+devId查找
        //如果已经注册过
        String phoneNum = loginQO.getPhoneNum();
        SocialuniUserDo mineUser = null;
        long unionId = SocialuniUnionIdFacede.createUserUnionId();

        if (StringUtils.isEmpty(phoneNum)) {
            mineUser = socialUserEntity.createUserAndDetailByThird(loginQO, loginQO.getUserType(), unionId);
        } else {
            SocialUserPhoneDo socialUserPhoneDo = socialUserPhoneManage.checkLoginPhoneNumAndGetUser(phoneNum);
            if (socialUserPhoneDo == null) {
                mineUser = socialUserEntity.createUserAndDetailByThird(loginQO, loginQO.getUserType(), unionId);
                socialUserPhoneManage.createUserPhoneNum(mineUser.getUnionId(), "86", phoneNum);
            } else {
                mineUser = SocialuniUserUtil.getUserNotNull(socialUserPhoneDo.getUserId());
            }
        }
        SocialuniTokenDO socialUserTokenDO = tokenManage.create(mineUser.getUnionId());

        SocialuniMineUserDetailRO userDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(mineUser);

        return new SocialLoginRO<>(socialUserTokenDO.getToken(), userDetailRO);
        //如果uid存在，则代表用户注册过
    }
}
