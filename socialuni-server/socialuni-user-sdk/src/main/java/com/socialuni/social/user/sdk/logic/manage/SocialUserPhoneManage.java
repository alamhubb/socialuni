package com.socialuni.social.user.sdk.logic.manage;

import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.qingchi.qing.common.exception.base.exception.SocialParamsException;
import com.socialuni.social.content.constant.SocialuniUserStatus;
import com.socialuni.social.tance.dev.facade.DevAccountFacade;
import com.socialuni.social.user.sdk.config.SocialuniUserSysConfig;
import com.qingchi.qing.user.dao.model.SocialUserPhoneDo;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.logic.redis.SocialUserPhoneRedis;
import com.socialuni.social.user.sdk.dao.repository.SocialUserPhoneRepository;
import com.socialuni.social.user.sdk.utils.SocialuniPhoneNumCheck;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

//授权用户信息
@Component
@Slf4j
public class SocialUserPhoneManage {
    @Resource
    SocialUserPhoneRepository socialUserPhoneApi;
    @Resource
    SocialUserPhoneRedis socialUserPhoneRedis;

    //校验用户是否已经绑定手机号，手机号是否已被绑定
    //不存在已经授权了清池用户，再来绑定手机号的情况。
    public void checkBindPhoneNum(String phoneNum, Long mineUserId) {
        //进入这个方法一定是已经登陆了。
        //校验手机号格式
        checkBindPhoneNumHasBind(phoneNum);
        SocialUserPhoneDo SocialUserPhoneDo = socialUserPhoneRedis.findUserPhoneByUserId(mineUserId);
        //校验用户是否已经绑定手机号
        if (SocialUserPhoneDo != null) {
            throw new SocialParamsException("用户已绑定手机号");
        }
    }

    public void checkBindPhoneNumHasBind(String phoneNum) {
        //进入这个方法一定是已经登陆了。
        //校验手机号格式
        SocialuniPhoneNumCheck.checkPhoneNum(phoneNum);
        //校验手机号状态是否可用
        SocialUserPhoneDo SocialUserPhoneDo = socialUserPhoneRedis.findByPhoneNum(phoneNum);
        if (SocialUserPhoneDo != null) {
            //用户为空，则代表就是这个用户，用户不为空才校验手机号是否已被使用
            throw new SocialBusinessException("手机号已被绑定，请使用手机号登录");
        }
    }

    public SocialUserPhoneDo checkLoginPhoneNumAndGetUser(String phoneNum) {
        //校验手机号格式
        SocialuniPhoneNumCheck.checkPhoneNum(phoneNum);
        //校验手机号状态是否可用
        SocialUserPhoneDo SocialUserPhoneDo = socialUserPhoneRedis.findByPhoneNum(phoneNum);
        return SocialUserPhoneDo;
    }

    public SocialUserPhoneDo checkLoginPhoneNumAllowCan(String phoneNum) {
        //校验手机号状态是否可用
        SocialUserPhoneDo SocialUserPhoneDo = this.checkLoginPhoneNumAndGetUser(phoneNum);
        if (SocialUserPhoneDo != null) {
            SocialuniUserDo phoneUser = SocialuniUserUtil.getUserNotNull(SocialUserPhoneDo.getUserId());
            if (!SocialuniUserSysConfig.bandAllowLogin) {
                //如果手机号违规，则返回手机号不可用
                if (phoneUser.getStatus().equals(SocialuniUserStatus.violation)) {
                    throw new SocialBusinessException("手机号不可用");
                }
            }
        }
        return SocialUserPhoneDo;
    }


    //创建手机号信息
    public SocialUserPhoneDo createUserPhoneNum(Long mineUserId, String phoneCountryCode, String phoneNum) {
        return createUserPhoneNum(mineUserId, phoneCountryCode, phoneNum, DevAccountFacade.getDevIdNullElseCenterDevId());
    }


    public SocialUserPhoneDo createUserPhoneNum(Long mineUserId, String phoneCountryCode, String phoneNum, Integer devId) {
        SocialUserPhoneDo SocialUserPhoneDo = new SocialUserPhoneDo(mineUserId, phoneCountryCode, phoneNum, devId);
        SocialUserPhoneDo = socialUserPhoneApi.savePut(SocialUserPhoneDo);
        return SocialUserPhoneDo;
    }
}
