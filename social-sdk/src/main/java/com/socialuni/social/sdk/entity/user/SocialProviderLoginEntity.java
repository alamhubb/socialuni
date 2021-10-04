package com.socialuni.social.sdk.entity.user;

import com.socialuni.social.entity.model.DO.user.SocialUserAccountDO;
import com.socialuni.social.exception.SocialParamsException;
import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.sdk.manage.SocialUserAccountManage;
import com.socialuni.social.sdk.manage.SocialUserFansDetailManage;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.sdk.manage.SocialUserManage;
import com.socialuni.social.sdk.model.UniUnionIdRO;
import com.socialuni.social.sdk.store.SocialUserAccountStore;
import com.socialuni.social.sdk.utils.SocialUniProviderUtil;
import com.socialuni.social.sdk.utils.SocialUserUtil;
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

    //根据渠道登录信息获取user，支持social比commonUserDomain
    //这个单独出来是因为区分了基础provider和社交，这个单独增加了对社交渠道的支持
    public UserDO providerLogin(SocialProviderLoginQO loginQO) {
        //创建或返回
        UniUnionIdRO uniUnionIdRO = SocialUniProviderUtil.getUnionIdRO(loginQO);
        if (uniUnionIdRO.hasError()) {
            throw new SocialParamsException("获取openId失败");
        }
        SocialUserAccountDO socialUserAccountDO = socialUserAccountStore.getAccountByUnionId(loginQO, uniUnionIdRO);
        UserDO mineUser;
        //如果已经注册过
        if (socialUserAccountDO != null) {
            mineUser = SocialUserUtil.get(socialUserAccountDO.getUserId());
            socialUserAccountManage.updateSessionKey(loginQO.getProvider(), uniUnionIdRO.getSession_key(), mineUser.getId());
        } else {
            mineUser = socialUserManage.createUserByProviderLogin(loginQO);
            //创建或返回
            socialUserFansDetailManage.getOrCreateUserFollowDetail(mineUser);
            socialUserAccountManage.create(mineUser.getId(), loginQO, uniUnionIdRO);
        }
        return mineUser;
    }
}
