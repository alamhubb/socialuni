package com.socialuni.center.web.entity;

import com.socialuni.center.web.manage.UniUserAccountRepository;
import com.socialuni.center.web.model.DO.UniUserAccountDO;
import com.socialuni.social.entity.model.DO.dev.DevAccountDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.sdk.manage.SocialUserFansDetailManage;
import com.socialuni.social.sdk.manage.SocialUserManage;
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

    //根据渠道登录信息获取user，支持social比commonUserDomain
    //这个单独出来是因为区分了基础provider和社交，这个单独增加了对社交渠道的支持
    public UserDO registryUser(DevAccountDO devAccountDO, SocialProviderLoginQO loginQO) {
        Integer devId = devAccountDO.getId();
        String thirdUserId = loginQO.getUnionId();

        UniUserAccountDO uniUnionIdRO = uniUserAccountRepository.findByDevIdAndThirdUserId(devId, thirdUserId);
        UserDO mineUser;
        //如果已经注册过
        if (uniUnionIdRO != null) {
            mineUser = SocialUserUtil.get(uniUnionIdRO.getUserId());
        } else {
            mineUser = socialUserManage.createUserByProviderLogin(loginQO);
            //创建或返回
            socialUserFansDetailManage.getOrCreateUserFollowDetail(mineUser);

            UniUserAccountDO uniUserAccountDO = new UniUserAccountDO(devId, thirdUserId, mineUser.getId());

            uniUserAccountRepository.save(uniUserAccountDO);
        }
        return mineUser;
    }
}
