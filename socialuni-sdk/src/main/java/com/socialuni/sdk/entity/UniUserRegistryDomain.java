package com.socialuni.sdk.entity;

import com.socialuni.sdk.entity.user.SocialPhoneLoginEntity;
import com.socialuni.sdk.entity.user.SocialUserPhoneEntity;
import com.socialuni.sdk.manage.SocialUserFansDetailManage;
import com.socialuni.sdk.manage.SocialUserManage;
import com.socialuni.sdk.manage.phone.SocialUserPhoneManage;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.model.DO.user.SocialUserPhoneDO;
import com.socialuni.sdk.model.QO.user.SocialProviderLoginQO;
import com.socialuni.sdk.utils.SocialUserUtil;
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


    //根据渠道登录信息获取user，支持social比commonUserDomain
    //这个单独出来是因为区分了基础provider和社交，这个单独增加了对社交渠道的支持
    public SocialUserDO registryUser(SocialProviderLoginQO loginQO) {
        SocialUserDO mineUser;
        //如果已经注册过
        String phoneNum = loginQO.getPhoneNum();
//        if (StringUtils.isNotEmpty(phoneNum)) {
        SocialUserPhoneDO socialUserPhoneDO = socialUserPhoneManage.checkLoginPhoneNum(phoneNum);
        if (socialUserPhoneDO != null) {
            mineUser = SocialUserUtil.getNotNull(socialUserPhoneDO.getUserId());
        } else {
            mineUser = socialUserPhoneEntity.createUserPhoneEntity(phoneNum);
        }
        /*} else {
            mineUser = socialUserPhoneEntity.createUserPhoneEntity(phoneNum);
        }*/
        //创建或返回
        socialUserFansDetailManage.getOrCreateUserFollowDetail(mineUser);

        return mineUser;
    }
}
