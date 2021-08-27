package com.socialuni.social.sdk.domain.phone;

import com.socialuni.cloud.tencent.TencentSmsServe;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.constant.DateTimeType;
import com.socialuni.social.constant.StatusConst;
import com.socialuni.social.entity.model.DO.AuthenticationDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.exception.SocialBusinessException;
import com.socialuni.social.exception.SocialParamsException;
import com.socialuni.social.model.model.RO.user.phone.SocialSendAuthCodeQO;
import com.socialuni.social.sdk.constant.AppConfigConst;
import com.socialuni.social.sdk.constant.ErrorMsg;
import com.socialuni.social.sdk.manage.phone.SocialUserPhoneManage;
import com.socialuni.social.sdk.repository.AuthenticationRepository;
import com.socialuni.social.sdk.repository.UserRepository;
import com.socialuni.social.sdk.redis.SocialUserPhoneRedis;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import com.socialuni.social.web.sdk.utils.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2019-02-17 14:14
 */
@Service
@Slf4j
public class SocailSendAuthCodeDomain {
    @Resource
    private AuthenticationRepository authRepository;
    @Resource
    private UserRepository userRepository;
    @Resource
    private SocialUserPhoneRedis socialUserPhoneRedis;
    @Resource
    SocialUserPhoneManage socialUserPhoneManage;

    //发送验证码
    private void sendAuthCodeCheck(String phoneNum, UserDO mineUser, String userIp) {

        //h5登录也需要防止
        if (StringUtils.isEmpty(userIp)) {
            log.error("获取不到ip信息");
//            UserLogStoreUtils.save(new UserLogDO("获取不到用户ip信息", user, phoneNum));
            throw new SocialParamsException("用户信息错误，无法发送验证码");
        }

        //user为null则是登录
        if (mineUser == null) {
            //校验手机号是否可用
            socialUserPhoneManage.checkLoginPhoneNum(phoneNum);
        } else {
            //校验手机号是否可用
            socialUserPhoneManage.checkBindPhoneNum(phoneNum, mineUser);
        }

        //然后查ip总次数，大于2也不行
        //然后查userId 大于2也不行
        //时间，必须大于30秒，同一个手机号
        //获取数据库的认证信息的验证码,校验时间10分钟可以发一次验证码
        AuthenticationDO authenticationDO = authRepository.findFirstByPhoneNumOrderByCreateTimeDescIdAsc(phoneNum);
        if (authenticationDO != null) {
            Date lastDate = authenticationDO.getCreateTime();
            Integer authCodeInterval = (Integer) AppConfigConst.appConfigMap.get(AppConfigConst.authCodeIntervalKey);
            long canDate = lastDate.getTime() + authCodeInterval * DateTimeType.second;
            long curDate = new Date().getTime();
            if (curDate < canDate) {
                throw new SocialBusinessException("获取验证码过于频繁，请稍候重试");
            }
        }
        //都是默认30次
        final Integer userLimitCount = (Integer) AppConfigConst.appConfigMap.get(AppConfigConst.authCodeCountKey);
//        final Integer ipLimitCount = (Integer) AppConfigConst.appConfigMap.get(AppConfigConst.authCodeIpCountKey);
        final Integer phoneLimitCount = (Integer) AppConfigConst.appConfigMap.get(AppConfigConst.authCodePhoneCountKey);
        //首先查手机号总次数，如果大于1，则不行
        Integer phoneNumCount = authRepository.countByPhoneNum(phoneNum);

        if (mineUser != null) {
            Integer userCount = authRepository.countByUserId(mineUser.getId());
            if (userCount >= userLimitCount) {
//                UserLogStoreUtils.save(new UserLogDO("用户获取达到获取验证码次数上限", user, phoneNum));
//                return new ResultRO<>("获取验证码次数已达到上线，" + ErrorMsg.CONTACT_SERVICE);
                throw new SocialBusinessException("获取验证码次数已达到上线，" + ErrorMsg.CONTACT_SERVICE);
            }
        }
//        Integer ipCount = authRepository.countByIp(userIp);
        if (phoneNumCount >= phoneLimitCount) {
//        if (phoneNumCount >= phoneLimitCount || ipCount >= ipLimitCount) {
            /*if (phoneNumCount >= phoneLimitCount) {
                UserLogStoreUtils.save(new UserLogDO("手机号获取达到获取验证码次数上限", user, phoneNum));
            } else {
                UserLogStoreUtils.save(new UserLogDO("用户IP获取达到获取验证码次数上限", user, phoneNum));
            }
            return new ResultRO<>("获取验证码次数已达到上线，" + ErrorMsg.CONTACT_SERVICE);*/
            throw new SocialBusinessException("获取验证码次数已达到上线，" + ErrorMsg.CONTACT_SERVICE);
        }
    }

    public ResultRO<Void> sendAuthCode(SocialSendAuthCodeQO authCodeQO, UserDO mineUser) {
        //要防的是同1个ip无线刷验证码
        //发送验证码时要记录ip，记录用户id，记录请求内容
        //限制手机号，同1手机号做多2条，
        String userIp = IpUtil.getIpAddr();

        String phoneNum = authCodeQO.getPhoneNum();
        this.sendAuthCodeCheck(phoneNum, mineUser, userIp);

        AuthenticationDO authenticationDO = new AuthenticationDO(SocialUserUtil.getMineUserIdAllowNull(mineUser), phoneNum, null, userIp);
        authenticationDO.setStatus(StatusConst.fail);
        authRepository.save(authenticationDO);

        String authCode = TencentSmsServe.sendAuthCode(phoneNum);

        authenticationDO.setAuthCode(authCode);
        authenticationDO.setStatus(StatusConst.success);
        authRepository.save(authenticationDO);

        return new ResultRO<>();
    }
}
