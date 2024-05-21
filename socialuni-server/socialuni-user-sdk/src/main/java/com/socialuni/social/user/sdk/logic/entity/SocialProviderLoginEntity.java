package com.socialuni.social.user.sdk.logic.entity;

import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.user.sdk.logic.manage.SocialUserAccountManage;
import com.socialuni.social.user.sdk.logic.manage.SocialUserAccountStore;
import com.socialuni.social.common.sdk.dao.DO.SocialUserPlatformAccountDO;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.model.QO.SocialProviderLoginQO;
import com.socialuni.social.user.sdk.model.RO.UniUnionIdRO;
import com.socialuni.social.user.sdk.utils.SocialUniProviderUtil;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialProviderLoginEntity {
    @Resource
    private SocialUserAccountStore socialUserAccountStore;
    @Resource
    private SocialUserAccountManage socialUserAccountManage;
    @Resource
    SocialUserEntity socialUserEntity;


    //根据渠道登录信息获取user，支持social比commonUserDomain
    //这个单独出来是因为区分了基础provider和社交，这个单独增加了对社交渠道的支持
    public SocialuniUserDo providerLogin(SocialProviderLoginQO loginQO) {
        //创建或返回
        UniUnionIdRO uniUnionIdRO = SocialUniProviderUtil.getUnionIdRO(loginQO);
        if (uniUnionIdRO.hasError()) {
            throw new SocialParamsException("获取openId失败");
        }
        SocialUserPlatformAccountDO socialUserAccountDO = socialUserAccountStore.getAccountByUnionId(loginQO, uniUnionIdRO);
        SocialuniUserDo mineUser;
        //如果已经注册过
        if (socialUserAccountDO != null) {
            mineUser = SocialuniUserUtil.getUserNotNull(socialUserAccountDO.getUserId());
        } else {
            mineUser = socialUserEntity.createUserAndDetail(loginQO);
        }
        socialUserAccountManage.checkOrCreateOrUpdate(mineUser.getUserId(), loginQO, uniUnionIdRO);

        return mineUser;
    }
}
