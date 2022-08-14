package com.socialuni.sdk.entity.user;

import com.socialuni.sdk.manage.SocialUserAccountManage;
import com.socialuni.sdk.manage.SocialUserFansDetailManage;
import com.socialuni.sdk.manage.SocialUserManage;
import com.socialuni.sdk.model.UniUnionIdRO;
import com.socialuni.sdk.store.SocialUserAccountStore;
import com.socialuni.sdk.utils.SocialUniProviderUtil;
import com.socialuni.sdk.utils.SocialUserUtil;
import com.socialuni.sdk.model.DO.user.SocialUserAccountDO;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.exception.SocialParamsException;
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
    public SocialUserDO providerLogin(SocialProviderLoginQO loginQO) {
        //创建或返回
        UniUnionIdRO uniUnionIdRO = SocialUniProviderUtil.getUnionIdRO(loginQO);
        if (uniUnionIdRO.hasError()) {
            throw new SocialParamsException("获取openId失败");
        }
        SocialUserAccountDO socialUserAccountDO = socialUserAccountStore.getAccountByUnionId(loginQO, uniUnionIdRO);
        SocialUserDO mineUser;
        //如果已经注册过
        if (socialUserAccountDO != null) {
            mineUser = SocialUserUtil.getUserNotNull(socialUserAccountDO.getUserId());
            socialUserAccountManage.updateSessionKey(loginQO.getProvider(), uniUnionIdRO.getSession_key(), mineUser.getUnionId());
        } else {
            mineUser = socialUserEntity.createUserAndDetail(loginQO);
            socialUserAccountManage.create(mineUser.getUnionId(), loginQO, uniUnionIdRO);
        }
        return mineUser;
    }
}
