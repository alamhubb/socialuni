package com.socialuni.social.sdk.logic.entity.user;

import com.socialuni.social.sdk.logic.manage.SocialUserAccountManage;
import com.socialuni.social.sdk.logic.manage.SocialUserFansDetailManage;
import com.socialuni.social.sdk.logic.manage.SocialUserManage;
import com.socialuni.social.sdk.model.UniUnionIdRO;
import com.socialuni.social.sdk.dao.store.SocialUserAccountStore;
import com.socialuni.social.sdk.utils.SocialUniProviderUtil;
import com.socialuni.social.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.sdk.dao.DO.user.SocialUserAccountDO;
import com.socialuni.social.user.sdk.model.SocialuniUserModel;
import com.socialuni.social.sdk.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.common.sdk.exception.exception.SocialParamsException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialProviderLoginEntity {
    @Resource
    private SocialUserFansDetailManage socialUserFansDetailManage;
    @Resource
    private SocialUserManage socialUserManage;
    @Resource
    private SocialUserAccountStore socialUserAccountStore;
    @Resource
    private SocialUserAccountManage socialUserAccountManage;
    @Resource
    SocialUserEntity socialUserEntity;

    //根据渠道登录信息获取user，支持social比commonUserDomain
    //这个单独出来是因为区分了基础provider和社交，这个单独增加了对社交渠道的支持
    public SocialuniUserModel providerLogin(SocialProviderLoginQO loginQO) {
        //创建或返回
        UniUnionIdRO uniUnionIdRO = SocialUniProviderUtil.getUnionIdRO(loginQO);
        if (uniUnionIdRO.hasError()) {
            throw new SocialParamsException("获取openId失败");
        }
        SocialUserAccountDO socialUserAccountDO = socialUserAccountStore.getAccountByUnionId(loginQO, uniUnionIdRO);
        SocialuniUserModel mineUser;
        //如果已经注册过
        if (socialUserAccountDO != null) {
            mineUser = SocialuniUserUtil.getUserNotNull(socialUserAccountDO.getUserId());
            socialUserAccountManage.updateSessionKey(loginQO.getProvider(), uniUnionIdRO.getSession_key(), mineUser.getUnionId());
        } else {
            mineUser = socialUserEntity.createUserAndDetail(loginQO);
            socialUserAccountManage.create(mineUser.getUnionId(), loginQO, uniUnionIdRO);
        }
        return mineUser;
    }
}
