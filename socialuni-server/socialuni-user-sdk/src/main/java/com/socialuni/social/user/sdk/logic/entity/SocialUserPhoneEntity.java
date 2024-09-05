package com.socialuni.social.user.sdk.logic.entity;

import com.socialuni.social.user.sdk.logic.manage.SocialUserManage;
import com.socialuni.social.user.sdk.logic.manage.SocialUserPhoneManage;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.common.sdk.dao.repository.SocialuniUserRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;

@Component
public class SocialUserPhoneEntity {
    @Resource
    private SocialUserPhoneManage socialUserPhoneManage;
    @Resource
    private SocialuniUserRepository userApi;
    @Resource
    SocialUserManage socialUserManage;

    //外层已经校验过了
    @Transactional
    public SocialuniUserDo createUserPhoneEntity(String phoneNum, Long unionId) {
        SocialuniUserDo mineUser = socialUserManage.createUser(unionId);
        //创建或返回
//        socialUserFansDetailManage.getOrCreateUserFollowDetail(mineUser);
        socialUserPhoneManage.createUserPhoneNum(mineUser.getUnionId(), "86", phoneNum);
        return mineUser;
    }

    @Transactional
    public SocialuniUserDo createSysUserPhoneEntity(String phoneNum, Integer devId, Long unionId) {
        SocialuniUserDo mineUser = socialUserManage.createSysUserByPhoneLogin(unionId);
        //创建或返回
//        socialUserFansDetailManage.getOrCreateUserFollowDetail(mineUser);
        socialUserPhoneManage.createUserPhoneNum(mineUser.getUnionId(), "86", phoneNum, devId);
        return mineUser;
    }

    //绑定手机号
    public SocialuniUserDo checkPhoneNumAndCreateBind(SocialuniUserDo mineUser, String phoneCountryCode, String phoneNum) {
        //业务校验
        //校验手机号是否已被使用
        socialUserPhoneManage.checkBindPhoneNum(phoneNum, mineUser.getUnionId());

        socialUserPhoneManage.createUserPhoneNum(mineUser.getUnionId(), phoneCountryCode, phoneNum);

        mineUser.setUpdateTime(new Date());
        mineUser = userApi.savePut(mineUser);
        return mineUser;
    }
}
