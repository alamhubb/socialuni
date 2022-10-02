package com.socialuni.social.sdk.logic.manage.phone;

import com.socialuni.social.sdk.dao.DO.user.SocialUserPhoneDO;
import com.socialuni.social.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.social.sdk.constant.status.UserStatus;
import com.socialuni.social.sdk.dao.redis.SocialUserPhoneRedis;
import com.socialuni.social.sdk.dao.repository.user.SocialUserPhoneRepository;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import com.socialuni.social.sdk.utils.PhoneNumUtil;
import com.socialuni.social.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.common.exception.exception.SocialBusinessException;
import com.socialuni.social.common.exception.exception.SocialParamsException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

//授权用户信息
@Component
public class SocialUserPhoneManage {
    @Resource
    SocialUserPhoneRepository socialUserPhoneRepository;
    @Resource
    SocialUserPhoneRedis socialUserPhoneRedis;

    //校验用户是否已经绑定手机号，手机号是否已被绑定
    //不存在已经授权了清池用户，再来绑定手机号的情况。
    public void checkBindPhoneNum(String phoneNum, Integer mineUserId) {
        //进入这个方法一定是已经登陆了。
        //校验手机号格式
        checkBindPhoneNumHasBind(phoneNum);
        SocialUserPhoneDO socialUserPhoneDO = socialUserPhoneRedis.findUserPhoneByUserId(mineUserId);
        //校验用户是否已经绑定手机号
        if (socialUserPhoneDO != null) {
            throw new SocialParamsException("用户已绑定手机号");
        }
    }

    public void checkBindPhoneNumHasBind(String phoneNum) {
        //进入这个方法一定是已经登陆了。
        //校验手机号格式
        PhoneNumUtil.checkPhoneNum(phoneNum);
        //校验手机号状态是否可用
        SocialUserPhoneDO socialUserPhoneDO = socialUserPhoneRedis.findByPhoneNum(phoneNum);
        if (socialUserPhoneDO != null) {
            //用户为空，则代表就是这个用户，用户不为空才校验手机号是否已被使用
            throw new SocialBusinessException("手机号已被绑定");
        }
    }

    public SocialUserPhoneDO checkLoginPhoneNum(String phoneNum) {
        //校验手机号格式
        PhoneNumUtil.checkPhoneNum(phoneNum);
        //校验手机号状态是否可用
        SocialUserPhoneDO socialUserPhoneDO = socialUserPhoneRedis.findByPhoneNum(phoneNum);
        if (socialUserPhoneDO != null) {
            SocialuniUserDO phoneUser = SocialuniUserUtil.getUserNotNull(socialUserPhoneDO.getUserId());
            //如果手机号违规，则返回手机号不可用
            if (phoneUser.getStatus().equals(UserStatus.violation)) {
                throw new SocialBusinessException("手机号不可用");
            }
        }
        return socialUserPhoneDO;
    }

    //创建手机号信息
    public SocialUserPhoneDO createUserPhoneNum(Integer mineUserId, String phoneCountryCode, String phoneNum) {
        SocialUserPhoneDO socialUserPhoneDO = new SocialUserPhoneDO(mineUserId, phoneCountryCode, phoneNum, DevAccountFacade.getDevIdNotNull());
        socialUserPhoneDO = socialUserPhoneRepository.save(socialUserPhoneDO);
        return socialUserPhoneDO;
    }
}