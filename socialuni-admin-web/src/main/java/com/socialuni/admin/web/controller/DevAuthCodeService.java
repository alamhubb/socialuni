package com.socialuni.admin.web.controller;


import com.socialuni.admin.web.repository.UserRepository;
import com.socialuni.entity.model.DevAuthenticationDO;
import com.socialuni.social.utils.AuthCodeUtil;
import com.socialuni.social.utils.IntegerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;

@Service
public class DevAuthCodeService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private DevAuthenticationRepository devAuthenticationRepository;
    @Resource
    private UserRepository userRepository;
    @Value("${config.qq.sms.appId}")
    private int appId;
    @Value("${config.qq.sms.appKey}")
    private String appKey;
    @Value("${config.qq.sms.templateId}")
    private int templateId;
    @Value("${config.qq.sms.smsSign}")
    private String smsSign;

    public DevAuthCodeService() {
    }

    public ResultRO<String> verifyUserAndPhoneNumMatch(String phoneNum, SocialUserDO user) {
        if (IntegerUtils.strHasNoNumber(phoneNum)) {
            return new ResultRO("请输入正确的手机号");
        } else {
            Optional<SocialUserDO> userDOOptional = this.userRepository.findFirstByPhoneNumOrderByIdAsc(phoneNum);
            if (userDOOptional.isPresent()) {
                SocialUserDO phoneUser = (SocialUserDO)userDOOptional.get();
                if (phoneUser.getStatus().equals("违规")) {
                    SocialUserDetailDO socialUserDetailDO = UserUtils.getUserDetail(user.getId());
                    return new ResultRO(ErrorMsgUtil.getErrorCode605ContactServiceValue(socialUserDetailDO.getViolationEndTime()));
                }
            }

            if (user != null) {
                if (StringUtils.isNotEmpty(user.getPhoneNum())) {
                    UserLogStoreUtils.save(new UserLogDO("您已绑定手机号，不可重复绑定", user, phoneNum));
                    this.logger.warn("您已绑定手机号，不可重复绑定：{}", user.getId());
                    return new ResultRO("您已绑定手机号，不可重复绑定");
                }

                if (userDOOptional.isPresent()) {
                    UserLogStoreUtils.save(new UserLogDO("此手机号已被绑定，请更换其他手机号", user, phoneNum));
                    return new ResultRO("此手机号已被绑定，请更换其他手机号，有疑问请联系客服qq：491369310");
                }
            }

            return new ResultRO();
        }
    }

    public ResultRO<String> verifyAuthCode(String phoneNum, String authCode, SocialUserDO user) {
        if (!StringUtils.isEmpty(authCode) && !IntegerUtils.strHasNoNumber(authCode) && authCode.length() == 4) {
            Optional<DevAuthenticationDO> authenticationDOOptional = this.devAuthenticationRepository.findFirstByPhoneNumOrderByCreateTimeDescIdAsc(phoneNum);
            if (authenticationDOOptional.isPresent()) {
                DevAuthenticationDO authenticationDO = (DevAuthenticationDO)authenticationDOOptional.get();
                if (!authCode.equals(authenticationDO.getAuthCode())) {
                    this.logger.debug("验证码错误");
                    return new ResultRO("验证码错误");
                } else {
                    Integer authCodeValidMinute = (Integer) AppConfigConst.appConfigMap.get("验证码有效时间多少分");
                    long canTime = authenticationDO.getCreateTime().getTime() + (long)authCodeValidMinute * (long) CommonConst.minute;
                    long curTime = (new Date()).getTime();
                    if (curTime > canTime) {
                        this.logger.warn("验证码超时：{}", authCode);
                        UserLogStoreUtils.save(new UserLogDO("验证码超时", user, phoneNum, authCode));
                        return new ResultRO("验证码超时，请重新获取");
                    } else {
                        return new ResultRO();
                    }
                }
            } else {
                this.logger.debug("没有验证码记录，未发送过验证码，此手机号没有对应的验证码记录");
                return new ResultRO("此手机号未发送过验证码");
            }
        } else {
            this.logger.error("有人跳过前端，直接访问后台，错误的验证码");
            return new ResultRO("请输入正确的验证码");
        }
    }

    public ResultRO<String> sendAuthCodeHandle(String phoneNum, SocialUserDO user, HttpServletRequest request) {
        String userIp = IpUtil.getIpAddr(request);
        if (StringUtils.isEmpty(userIp)) {
            this.logger.error("获取不到ip信息");
            UserLogStoreUtils.save(new UserLogDO("获取不到用户ip信息", user, phoneNum));
            return new ResultRO("用户信息错误，无法发送验证码");
        } else {
            ResultRO<String> resultRO = this.verifyUserAndPhoneNumMatch(phoneNum, user);
            if (resultRO.hasError()) {
                return resultRO;
            } else {
                Optional<DevAuthenticationDO> authenticationDOOptional = this.devAuthenticationRepository.findFirstByPhoneNumOrderByCreateTimeDescIdAsc(phoneNum);
                if (authenticationDOOptional.isPresent()) {
                    DevAuthenticationDO authenticationDO = (DevAuthenticationDO)authenticationDOOptional.get();
                    Date lastDate = authenticationDO.getCreateTime();
                    long canDate = lastDate.getTime() + 30L * (long)CommonConst.second;
                    long curDate = (new Date()).getTime();
                    if (curDate < canDate) {
                        return new ResultRO("获取验证码过于频繁，请稍候重试");
                    }
                }

                return this.cosSendAuthCode(phoneNum, user, userIp);
            }
        }
    }

    private ResultRO<String> cosSendAuthCode(String phoneNum, SocialUserDO user, String userIp) {
        String authCode = AuthCodeUtil.getAuthCode();
        System.out.println("发送验证码+：" + authCode);
        DevAuthenticationDO authenticationDO = new DevAuthenticationDO(user, phoneNum, authCode, userIp);
        authenticationDO.setStatus("成功");
        this.devAuthenticationRepository.save(authenticationDO);
        return new ResultRO();
    }
}
