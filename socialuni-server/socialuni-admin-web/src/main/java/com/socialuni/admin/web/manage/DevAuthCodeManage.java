package com.socialuni.admin.web.manage;

import com.socialuni.admin.web.repository.DevAuthCodeRepository;
import com.socialuni.social.sdk.dao.DO.dev.DevAuthCodeDO;
import com.socialuni.social.common.api.constant.DateTimeType;
import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.api.utils.IntegerUtils;
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
public class DevAuthCodeManage {
    @Resource
    private DevAuthCodeRepository devAuthCodeRepository;

    public void checkAuthCode(String phoneNum, String authCode) {
        //1.为空 2. 包含非数字 3.不为4位 ，返回
        if (StringUtils.isEmpty(authCode) || IntegerUtils.strHasNoNumber(authCode) || authCode.length() != 4) {
            log.error("有人跳过前端，直接访问后台，错误的验证码");
            throw new SocialBusinessException("请输入正确的验证码");
        }
        //获取数据库的认证信息的验证码
        DevAuthCodeDO authenticationDO = devAuthCodeRepository.findFirstByPhoneNumOrderByCreateTimeDescIdAsc(phoneNum);
        if (authenticationDO == null) {
            throw new SocialBusinessException("请先发送验证码");
        }
        //如果发送过验证码
        //校验验证码是否过期，如果当前时间晚于30分钟后则验证码失效
        Integer authCodeValidMinute = 30;
        long canTime = authenticationDO.getCreateTime().getTime() + authCodeValidMinute * DateTimeType.minute;
        long curTime = new Date().getTime();
        if (curTime > canTime) {
//            UserLogStoreUtils.save(new UserLogDO("验证码超时", user, phoneNum, authCode));
            throw new SocialBusinessException("验证码超时，请重新获取");
        }
        //用户输入的验证码和数据库的一致
        if (!authCode.equals(authenticationDO.getAuthCode())) {
            //验证码错误，提示
            log.debug("验证码错误");
            throw new SocialBusinessException("验证码错误");
        }
    }

    //发送验证码和绑定手机号，会调用这里，为什么发送验证码之后会调用这里呢，因为绑定手机号，和登陆调用的都是这个

    //用户手机号登陆，和非微信小程序绑定手机号，发送验证码时候，都会触发这里
    //用户未登录，校验手机号格式 校验手机号是否合理，是否为已封禁手机号
    //用户已登录 校验用户不能已绑定手机号 ，校验手机号和用户匹配，验手机号和用户的关系，判断手机号是否已绑定，
   /* public ResultRO<String> verifyUserAndPhoneNumMatch(String phoneNum, SocialUserDO user) {
        if (IntegerUtils.strHasNoNumber(phoneNum)) {
            return new ResultRO<>("请输入正确的手机号");
        }
        //校验手机号是否已被使用
        SocialUserPhoneDO socialUserPhoneDO = socialUserPhoneStore.findByPhoneNum(phoneNum);
        //如果手机号已使用
        if (socialUserPhoneDO != null) {
            //用户为空的时候，代表没登陆，所以已绑定的手机号也是可以通过的，因为可能在自己在登陆

            //如果已被使用的手机号已违规，就不能登陆了，违规了，登陆自己的也没意义，不能操作
            SocialUserDO phoneUser = UserUtils.get(socialUserPhoneDO.getUserId());
            if (phoneUser.getStatus().equals(UserStatus.violation)) {
                UserDetailDO userDetailDO = CenterUserUtil.getUserDetail(user.getId());
                return new ResultRO<>(ErrorMsgUtil.getErrorCode605ContactServiceValue(userDetailDO.getViolationEndTime()));
            }
        }
        //用户不为空的情况
        if (user != null) {
            //判断用户是否已绑定手机号
            //校验手机号是否已被使用
            SocialUserPhoneDO userPhoneDO = socialUserPhoneStore.findByUserId(user.getId());
            if (userPhoneDO != null) {
                UserLogStoreUtils.save(new UserLogDO("您已绑定手机号，不可重复绑定", user, phoneNum));
                log.warn("您已绑定手机号，不可重复绑定：{}", user.getId());
                return new ResultRO<>("您已绑定手机号，不可重复绑定");
            }
            //用户已注册，未绑定，只有用户的状态下，才校验这个手机号是否已经被绑定了
            if (socialUserPhoneDO != null) {
                //提示手机号已被使用，不可再被绑定
                UserLogStoreUtils.save(new UserLogDO("此手机号已被绑定，请更换其他手机号", user, phoneNum));
                return new ResultRO<>("此手机号已被绑定，请更换其他手机号，" + ErrorMsg.CONTACT_SERVICE);
            }
        }
        return new ResultRO<>();
    }*/


}
