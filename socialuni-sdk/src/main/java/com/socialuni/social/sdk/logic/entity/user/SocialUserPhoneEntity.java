package com.socialuni.social.sdk.logic.entity.user;

import com.socialuni.social.sdk.logic.manage.SocialUserFansDetailManage;
import com.socialuni.social.sdk.logic.manage.SocialUserManage;
import com.socialuni.social.sdk.logic.manage.phone.SocialUserPhoneManage;
import com.socialuni.social.user.sdk.model.SocialuniUserDO;
import com.socialuni.social.user.sdk.api.UserRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;

@Component
public class SocialUserPhoneEntity {
    @Resource
    private SocialUserPhoneManage socialUserPhoneManage;
    @Resource
    private UserRepository userRepository;
    @Resource
    SocialUserManage socialUserManage;
    @Resource
    SocialUserFansDetailManage socialUserFansDetailManage;

    //外层已经校验过了
    @Transactional
    public SocialuniUserDO createUserPhoneEntity(String phoneNum) {
        SocialuniUserDO mineUser = socialUserManage.createUserByPhoneLogin();
        //创建或返回
        socialUserFansDetailManage.getOrCreateUserFollowDetail(mineUser);
        socialUserPhoneManage.createUserPhoneNum(mineUser.getUnionId(), "86", phoneNum);
        return mineUser;
    }

    //绑定手机号
    public SocialuniUserDO checkPhoneNumAndCreateBind(SocialuniUserDO mineUser, String phoneCountryCode, String phoneNum) {
        //业务校验
        //校验手机号是否已被使用
        socialUserPhoneManage.checkBindPhoneNum(phoneNum, mineUser.getUnionId());

        socialUserPhoneManage.createUserPhoneNum(mineUser.getUnionId(), phoneCountryCode, phoneNum);

        mineUser.setUpdateTime(new Date());
        mineUser = userRepository.save(mineUser);
        return mineUser;
    }
}
