package com.socialuni.center.web.entity;

import com.socialuni.center.web.repository.UniUserAccountRepository;
import com.socialuni.center.web.model.DO.UniUserAccountDO;
import com.socialuni.social.entity.model.DO.dev.DevAccountDO;
import com.socialuni.social.entity.model.DO.user.SocialUserPhoneDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.QO.user.SocialPhoneNumQO;
import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.sdk.entity.user.SocialPhoneLoginEntity;
import com.socialuni.social.sdk.entity.user.SocialUserPhoneEntity;
import com.socialuni.social.sdk.manage.SocialUserFansDetailManage;
import com.socialuni.social.sdk.manage.SocialUserManage;
import com.socialuni.social.sdk.manage.phone.SocialUserPhoneManage;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UniUserRegistryDomain {
    @Resource
    private SocialUserFansDetailManage socialUserFansDetailManage;
    @Resource
    private SocialUserManage socialUserManage;
    @Resource
    UniUserAccountRepository uniUserAccountRepository;
    @Resource
    SocialUserPhoneManage socialUserPhoneManage;
    @Resource
    SocialPhoneLoginEntity socialPhoneLoginEntity;
    @Resource
    SocialUserPhoneEntity socialUserPhoneEntity;


    //根据渠道登录信息获取user，支持social比commonUserDomain
    //这个单独出来是因为区分了基础provider和社交，这个单独增加了对社交渠道的支持
    public UserDO registryUser(DevAccountDO devAccountDO, SocialProviderLoginQO loginQO) {
        Integer devId = devAccountDO.getId();
        String thirdUserIdStr = loginQO.getUnionId();
        Integer dataUserUnionId = Integer.parseInt(thirdUserIdStr);
        UniUserAccountDO uniUnionIdRO = uniUserAccountRepository.findByDevIdAndThirdUserId(devId, dataUserUnionId);
        UserDO mineUser;
        //如果已经注册过
        if (uniUnionIdRO != null) {
            mineUser = SocialUserUtil.get(uniUnionIdRO.getUserId());
        } else {
            String phoneNum = loginQO.getPhoneNum();
            SocialUserPhoneDO socialUserPhoneDO = socialUserPhoneManage.checkLoginPhoneNum(phoneNum);
            if (socialUserPhoneDO != null) {
                mineUser = SocialUserUtil.get(socialUserPhoneDO.getUserId());
            } else {
                mineUser = socialUserPhoneEntity.createUserPhoneEntity(phoneNum);
            }
            //创建或返回
            socialUserFansDetailManage.getOrCreateUserFollowDetail(mineUser);

            UniUserAccountDO uniUserAccountDO = new UniUserAccountDO(devId, dataUserUnionId, mineUser.getId());

            uniUserAccountRepository.save(uniUserAccountDO);

            socialUserPhoneManage.createUserPhoneNum(mineUser.getId(), "86", loginQO.getPhoneNum());
        }
        return mineUser;
    }
}
