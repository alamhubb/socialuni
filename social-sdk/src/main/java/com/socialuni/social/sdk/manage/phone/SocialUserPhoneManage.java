package com.socialuni.social.sdk.manage.phone;

import com.socialuni.social.sdk.constant.status.UserStatus;
import com.socialuni.social.exception.SocialBusinessException;
import com.socialuni.social.exception.SocialParamsException;
import com.socialuni.social.entity.model.DO.user.SocialUserPhoneDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.sdk.repository.SocialUserPhoneRepository;
import com.socialuni.social.sdk.store.SocialUserPhoneStore;
import com.socialuni.social.sdk.utils.PhoneNumUtil;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

//授权用户信息
@Component
public class SocialUserPhoneManage {
    @Resource
    SocialUserPhoneRepository socialUserPhoneRepository;
    @Resource
    SocialUserPhoneStore socialUserPhoneStore;

    //校验用户是否已经绑定手机号，手机号是否已被绑定
    public void checkBindPhoneNum(String phoneNum, UserDO mineUser) {
        //校验手机号格式
        PhoneNumUtil.checkPhoneNum(phoneNum);
        //校验用户是否已经绑定手机号
        SocialUserPhoneDO socialUserPhoneDO = socialUserPhoneStore.findByUserId(mineUser.getId());
        if (socialUserPhoneDO != null) {
            throw new SocialParamsException("用户已绑定手机号");
        }
        //校验手机号状态是否可用
        socialUserPhoneDO = socialUserPhoneStore.findByPhoneNum(phoneNum);
        if (socialUserPhoneDO != null) {
            //用户为空，则代表就是这个用户，用户不为空才校验手机号是否已被使用
            throw new SocialBusinessException("手机号已被绑定");
        }
    }

    public SocialUserPhoneDO checkLoginPhoneNum(String phoneNum) {
        //校验手机号格式
        PhoneNumUtil.checkPhoneNum(phoneNum);
        //校验手机号状态是否可用
        SocialUserPhoneDO socialUserPhoneDO = socialUserPhoneStore.findByPhoneNum(phoneNum);
        if (socialUserPhoneDO != null) {
            UserDO phoneUser = SocialUserUtil.get(socialUserPhoneDO.getUserId());
            //如果手机号违规，则返回手机号不可用
            if (phoneUser.getStatus().equals(UserStatus.violation)) {
                throw new SocialBusinessException("手机号不可用");
            }
        }
        return socialUserPhoneDO;
    }

    //创建手机号信息
    public void createUserPhoneNum(UserDO mineUser, String phoneCountryCode, String phoneNum) {
        SocialUserPhoneDO socialUserPhoneDO = new SocialUserPhoneDO(mineUser.getId(), phoneCountryCode, phoneNum);
        socialUserPhoneRepository.save(socialUserPhoneDO);
    }
}
