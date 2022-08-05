package com.socialuni.center.web.entity.user;

import com.socialuni.center.web.manage.SocialUserFansDetailManage;
import com.socialuni.center.web.manage.SocialUserManage;
import com.socialuni.center.web.manage.phone.SocialUserPhoneManage;
import com.socialuni.center.web.repository.UserRepository;
import com.socialuni.social.entity.model.DO.user.UserDO;
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

    //外层已经校验过了
    public UserDO createUserPhoneEntity(String phoneNum) {
        UserDO mineUser = socialUserManage.createUserByPhoneLogin();
        //创建或返回
        socialUserFansDetailManage.getOrCreateUserFollowDetail(mineUser);
        socialUserPhoneManage.createUserPhoneNum(mineUser.getId(), "86", phoneNum);
        return mineUser;
    }

    //绑定手机号
    public UserDO checkPhoneNumAndCreateBind(UserDO mineUser, String phoneCountryCode, String phoneNum) {
        //业务校验
        //校验手机号是否已被使用
        socialUserPhoneManage.checkBindPhoneNum(phoneNum, mineUser.getId());

        socialUserPhoneManage.createUserPhoneNum(mineUser.getId(), phoneCountryCode, phoneNum);

        mineUser.setUpdateTime(new Date());
        mineUser = userRepository.save(mineUser);
        return mineUser;
    }
}
