package com.socialuni.center.web.entity;

import com.socialuni.center.web.model.DO.UniUserAccountDO;
import com.socialuni.center.web.repository.UniUserAccountRepository;
import com.socialuni.center.web.model.DO.user.SocialUserPhoneDO;
import com.socialuni.center.web.model.DO.user.SocialUserDO;
import com.socialuni.center.web.model.QO.user.SocialProviderLoginQO;
import com.socialuni.center.web.entity.user.SocialPhoneLoginEntity;
import com.socialuni.center.web.entity.user.SocialUserPhoneEntity;
import com.socialuni.center.web.manage.SocialUserFansDetailManage;
import com.socialuni.center.web.manage.SocialUserManage;
import com.socialuni.center.web.manage.phone.SocialUserPhoneManage;
import com.socialuni.center.web.utils.SocialUserUtil;
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
    public SocialUserDO registryUser(Integer dataDevId, SocialProviderLoginQO loginQO) {
        String thirdUserIdStr = loginQO.getUnionId();
        UniUserAccountDO uniUnionIdRO = uniUserAccountRepository.findByDevIdAndThirdUserId(dataDevId, thirdUserIdStr);
        SocialUserDO mineUser;
        //如果已经注册过
        if (uniUnionIdRO != null) {
            mineUser = SocialUserUtil.getNotNull(uniUnionIdRO.getUserId());
        } else {
            String phoneNum = loginQO.getPhoneNum();
            SocialUserPhoneDO socialUserPhoneDO = socialUserPhoneManage.checkLoginPhoneNum(phoneNum);
            if (socialUserPhoneDO != null) {
                mineUser = SocialUserUtil.getNotNull(socialUserPhoneDO.getUserId());
            } else {
                mineUser = socialUserPhoneEntity.createUserPhoneEntity(phoneNum);
            }
            //创建或返回
            socialUserFansDetailManage.getOrCreateUserFollowDetail(mineUser);

            UniUserAccountDO uniUserAccountDO = new UniUserAccountDO(dataDevId, thirdUserIdStr, mineUser.getId());

            uniUserAccountRepository.save(uniUserAccountDO);

            socialUserPhoneManage.createUserPhoneNum(mineUser.getId(), "86", loginQO.getPhoneNum());
        }
        return mineUser;
    }
}
