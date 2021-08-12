package com.socialuni.sdk.entity.user;

import com.socialuni.sdk.exception.UniSdkException;
import com.socialuni.sdk.manage.SocialUserAccountManage;
import com.socialuni.sdk.manage.SocialUserFansDetailManage;
import com.socialuni.sdk.manage.SocialUserManage;
import com.socialuni.entity.model.DO.user.SocialUserAccountDO;
import com.socialuni.entity.model.DO.user.UserDO;
import com.socialuni.sdk.model.UniUnionIdRO;
import com.socialuni.sdk.store.SocialUserAccountStore;
import com.socialuni.sdk.utils.SocialUniProviderUtil;
import com.socialuni.sdk.utils.SocialUserUtil;
import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialUserCreateEntity {
    @Resource
    private SocialUserManage socialUserManage;
    @Resource
    private SocialUserAccountStore socialUserAccountStore;
    @Resource
    private SocialUserAccountManage socialUserAccountManage;
    @Resource
    private SocialUserFansDetailManage socialUserFansDetailManage;

    //根据渠道登录信息获取user
    public UserDO getOrCreateUserEntity(SocialProviderLoginQO loginQO) {
        UniUnionIdRO uniUnionIdRO = SocialUniProviderUtil.getUnionIdRO(loginQO);
        if (uniUnionIdRO.hasError()) {
            throw new UniSdkException("获取openId失败");
        }
        UserDO userDO = getOrCreateUserEntity(loginQO, uniUnionIdRO);
        return userDO;
    }

    private UserDO getOrCreateUserEntity(SocialProviderLoginQO loginQO, UniUnionIdRO uniUnionIdRO) {
        SocialUserAccountDO socialUserAccountDO = socialUserAccountStore.getAccountByUnionId(loginQO, uniUnionIdRO);
        UserDO mineUser;
        //如果已经注册过
        if (socialUserAccountDO != null) {
            mineUser = SocialUserUtil.get(socialUserAccountDO.getUserId());
            socialUserAccountManage.updateSessionKey(uniUnionIdRO.getSession_key(), mineUser.getId());
        } else {
            mineUser = socialUserManage.createUserByProviderLogin(loginQO);
            //创建或返回
            socialUserFansDetailManage.getOrCreateUserFollowDetail(mineUser);
            socialUserAccountManage.create(mineUser, loginQO, uniUnionIdRO);
        }
        return mineUser;
    }

    /*private UserDO create(SocialProviderLoginQO loginQO, UniUnionIdRO uniUnionIdRO) {
        UserDO userDO = uniUserDOCreate.createUserByProviderLogin(loginQO);
        uniUserAccountDOCreate.create(userDO, loginQO, uniUnionIdRO);
        return userDO;
    }*/
}
