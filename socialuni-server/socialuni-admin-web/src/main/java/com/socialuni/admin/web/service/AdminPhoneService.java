package com.socialuni.admin.web.service;


import com.socialuni.admin.web.config.AppStaticData;
import com.socialuni.admin.web.repository.DevAuthCodeRepository;
import com.socialuni.social.sdk.dao.DO.dev.DevAuthCodeDO;
import com.socialuni.social.user.sdk.utils.TencentSmsServe;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.sdk.constant.StatusConst;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.user.sdk.model.QO.phone.SocialSendAuthCodeQO;
import com.socialuni.social.user.sdk.utils.PhoneNumUtil;
import com.socialuni.social.common.api.utils.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class AdminPhoneService {
    @Resource
    private DevAuthCodeRepository devAuthCodeRepository;

    //发送验证码
    public ResultRO<Void> sendAuthCode(SocialSendAuthCodeQO socialSendAuthCodeQO) {
        String userIp = IpUtil.getIpAddr();
        //h5登录也需要防止
        if (StringUtils.isEmpty(userIp)) {
            log.error("获取不到ip信息");
//            UserLogStoreUtils.save(new UserLogDO("获取不到用户ip信息", user, phoneNum));
            throw new SocialParamsException("用户信息错误，无法发送验证码");
        }
        Integer ipCount = AppStaticData.getIpCount(userIp);
        //获取验证码超过10次，则联系客服
        if (ipCount > 10) {
            throw new SocialParamsException("访问频繁，请联系客服处理");
        }
        AppStaticData.ipCountPlus(userIp);
        String phoneNum = socialSendAuthCodeQO.getPhoneNum();
        //校验用户是否已经绑定手机号，手机号是否已被绑定
        //user为null则是登录
        //校验手机号是否可用
        //校验手机号格式
        PhoneNumUtil.checkPhoneNum(phoneNum);
        //校验手机号状态是否可用
//        DevAccountDO devAccountDO = devAccountRepository.findFirstByPhoneNumOrderByIdAsc(phoneNum);

        DevAuthCodeDO devAuthCodeDO = new DevAuthCodeDO(phoneNum, null, userIp);
        devAuthCodeDO.setStatus(StatusConst.fail);
        devAuthCodeRepository.save(devAuthCodeDO);

        String authCode = TencentSmsServe.sendAuthCode(phoneNum);

        devAuthCodeDO.setAuthCode(authCode);
        devAuthCodeDO.setStatus(StatusConst.success);
        devAuthCodeRepository.save(devAuthCodeDO);

        //然后查ip总次数，大于2也不行
        //然后查userId 大于2也不行
        //时间，必须大于30秒，同一个手机号
        //获取数据库的认证信息的验证码,校验时间10分钟可以发一次验证码
        /*AuthenticationDO authenticationDO = authRepository.findFirstByPhoneNumOrderByCreateTimeDescIdAsc(phoneNum);
        if (authenticationDO != null) {
            Date lastDate = authenticationDO.getCreateTime();
            Integer authCodeInterval = (Integer) AdminAppConfigConst.appConfigMap.get(AdminAppConfigConst.authCodeIntervalKey);
            long canDate = lastDate.getTime() + authCodeInterval * DateTimeType.second;
            long curDate = new Date().getTime();
            if (curDate < canDate) {
                throw new SocialBusinessException("获取验证码过于频繁，请稍候重试");
            }
        }
        final Integer userLimitCount = (Integer) AdminAppConfigConst.appConfigMap.get(AdminAppConfigConst.authCodeCountKey);
        final Integer ipLimitCount = (Integer) AdminAppConfigConst.appConfigMap.get(AdminAppConfigConst.authCodeIpCountKey);
        final Integer phoneLimitCount = (Integer) AdminAppConfigConst.appConfigMap.get(AdminAppConfigConst.authCodePhoneCountKey);
        //首先查手机号总次数，如果大于1，则不行
        Integer phoneNumCount = authRepository.countByPhoneNum(phoneNum);*/

        /*Integer ipCount = authRepository.countByIp(userIp);
        if (phoneNumCount >= phoneLimitCount || ipCount >= ipLimitCount) {
            *//*if (phoneNumCount >= phoneLimitCount) {
                UserLogStoreUtils.save(new UserLogDO("手机号获取达到获取验证码次数上限", user, phoneNum));
            } else {
                UserLogStoreUtils.save(new UserLogDO("用户IP获取达到获取验证码次数上限", user, phoneNum));
            }
            return new ResultRO<>("获取验证码次数已达到上线，" + ErrorMsg.CONTACT_SERVICE);*//*
            throw new SocialBusinessException("获取验证码次数已达到上线，" + ErrorMsg.CONTACT_SERVICE);
        }*/
        return new ResultRO<>();
    }
}
