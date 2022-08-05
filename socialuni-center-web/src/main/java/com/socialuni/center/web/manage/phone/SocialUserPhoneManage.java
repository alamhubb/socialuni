package com.socialuni.center.web.manage.phone;

import com.socialuni.center.web.model.DO.user.SocialUserPhoneDO;
import com.socialuni.center.web.model.DO.user.UserDO;
import com.socialuni.social.exception.SocialBusinessException;
import com.socialuni.social.exception.SocialParamsException;
import com.socialuni.center.web.constant.status.UserStatus;
import com.socialuni.center.web.redis.SocialUserPhoneRedis;
import com.socialuni.center.web.repository.user.SocialUserPhoneRepository;
import com.socialuni.center.web.utils.DevAccountUtils;
import com.socialuni.center.web.utils.SocialUserUtil;
import com.socialuni.social.utils.PhoneNumUtil;
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
        PhoneNumUtil.checkPhoneNum(phoneNum);
        SocialUserPhoneDO socialUserPhoneDO = socialUserPhoneRedis.findUserPhoneByUserId(mineUserId);
        //校验用户是否已经绑定手机号
        if (socialUserPhoneDO != null) {
            throw new SocialParamsException("用户已绑定手机号");
        }
        //校验手机号状态是否可用
        socialUserPhoneDO = socialUserPhoneRedis.findByPhoneNum(phoneNum);
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
            UserDO phoneUser = SocialUserUtil.getNotNull(socialUserPhoneDO.getUserId());
            //如果手机号违规，则返回手机号不可用
            if (phoneUser.getStatus().equals(UserStatus.violation)) {
                throw new SocialBusinessException("手机号不可用");
            }
        }
        return socialUserPhoneDO;
    }

    //创建手机号信息
    public SocialUserPhoneDO createUserPhoneNum(Integer mineUserId, String phoneCountryCode, String phoneNum) {
        SocialUserPhoneDO socialUserPhoneDO = new SocialUserPhoneDO(mineUserId, phoneCountryCode, phoneNum, DevAccountUtils.getDevIdNotNull());
        socialUserPhoneDO = socialUserPhoneRepository.save(socialUserPhoneDO);
        return socialUserPhoneDO;
    }
}
