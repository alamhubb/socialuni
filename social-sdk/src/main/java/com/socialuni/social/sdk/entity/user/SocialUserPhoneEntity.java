package com.socialuni.social.sdk.entity.user;

import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.sdk.manage.SocialUserFansDetailManage;
import com.socialuni.social.sdk.manage.SocialUserManage;
import com.socialuni.social.sdk.manage.phone.SocialUserPhoneManage;
import com.socialuni.social.sdk.repository.UserRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
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

    public UserDO createUserPhoneEntity(String phoneNum) {
        UserDO mineUser = socialUserManage.createUserByPhoneLogin();
        //创建或返回
        socialUserFansDetailManage.getOrCreateUserFollowDetail(mineUser);
        socialUserPhoneManage.createUserPhoneNum(mineUser, "86", phoneNum);
        return mineUser;
    }

    public UserDO checkPhoneNumAndBind(UserDO mineUser, String phoneCountryCode, String phoneNum) {
        //业务校验
        //校验手机号是否已被使用
        socialUserPhoneManage.checkBindPhoneNum(phoneNum, mineUser);

        socialUserPhoneManage.createUserPhoneNum(mineUser, phoneCountryCode, phoneNum);

        mineUser.setUpdateTime(new Date());
        mineUser = userRepository.save(mineUser);
        return mineUser;
    }
}
