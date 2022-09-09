package com.socialuni.sdk.logic.service.user;

import com.socialuni.sdk.config.SocialAppConfig;
import com.socialuni.sdk.constant.ErrorMsg;
import com.socialuni.sdk.logic.domain.user.SocialAddUserImgDomain;
import com.socialuni.sdk.logic.domain.user.SocialDeleteUserImgDomain;
import com.socialuni.sdk.logic.domain.user.SocialEditUserDomain;
import com.socialuni.sdk.dao.DO.user.SocialUserIdentityAuthDO;
import com.socialuni.sdk.dao.DO.user.SocialUserIdentityAuthImgDO;
import com.socialuni.sdk.model.QO.user.SocialUserIdentityAuthQO;
import com.socialuni.sdk.model.RO.user.SocialUserIdentityAuthPreCheckRO;
import com.socialuni.sdk.logic.platform.tencent.TencentCloud;
import com.socialuni.sdk.dao.repository.user.identity.SocialUserIdentityAuthImgRepository;
import com.socialuni.sdk.dao.repository.user.identity.SocialUserIdentityAuthRepository;
import com.socialuni.sdk.utils.DateUtils;
import com.socialuni.sdk.utils.SocialuniUserUtil;
import com.socialuni.sdk.utils.common.BirthdayAgeUtil;
import com.socialuni.social.web.sdk.model.ResultRO;
import com.socialuni.sdk.constant.socialuni.UserIdentityAuthStatus;
import com.socialuni.social.web.sdk.exception.SocialBusinessException;
import com.socialuni.social.web.sdk.exception.SocialSystemException;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.ocr.v20181119.OcrClient;
import com.tencentcloudapi.ocr.v20181119.models.IDCardOCRRequest;
import com.tencentcloudapi.ocr.v20181119.models.IDCardOCRResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;

@Service
@Slf4j
public class SocialuniUserIdentityService {
    @Resource
    SocialEditUserDomain socialEditUserDomain;
    @Resource
    SocialAddUserImgDomain socialAddUserImgDomain;
    @Resource
    SocialDeleteUserImgDomain socialDeleteUserImgDomain;
    @Resource
    SocialUserIdentityAuthImgRepository socialUserIdentityAuthImgRepository;
    @Resource
    SocialUserIdentityAuthRepository socialUserIdentityAuthRepository;


    public ResultRO<SocialUserIdentityAuthPreCheckRO> userIdentityAuthPreCheck(SocialUserIdentityAuthQO socialUseIdentityAuthQO) {
        Integer resScore = TencentCloud.imgAuthGetScore(SocialAppConfig.getStaticResourceUrl() + socialUseIdentityAuthQO.getIdImgUrl(), SocialAppConfig.getStaticResourceUrl() + socialUseIdentityAuthQO.getSelfieImgUrl());
        if (resScore == 0) {
            throw new SocialBusinessException("预校验失败，请重试，" + ErrorMsg.CONTACT_SERVICE);
        }
        SocialUserIdentityAuthPreCheckRO checkRO = new SocialUserIdentityAuthPreCheckRO();
        checkRO.setAuthScore(resScore);
        checkRO.setAuthHint("认证分数仅供参考，如您上传的照片为真实信息，可点击认证");
//        else if (resScore < 40) {
//            checkRO.setAuthHint("认证分数偏低，请确认是否为真实信息，认证分数仅供参考，如果确认您上传的照片为真实信息，可点击认证");
//        }
        //认证分数仅供参考，如果您上传的照片为真实信息，可点击认证
        return ResultRO.success(checkRO);
    }

    public ResultRO<String> userIdentityAuth(SocialUserIdentityAuthQO socialUserIdentityAuthQO) {
        // 实例化要请求产品的client对象,clientProfile是可选的
        OcrClient client = TencentCloud.getOcrClient();
        // 实例化一个请求对象,每个接口都会对应一个request对象
        IDCardOCRRequest req = new IDCardOCRRequest();
        req.setImageUrl(SocialAppConfig.getStaticResourceUrl() + socialUserIdentityAuthQO.getIdImgUrl());
        // 返回的resp是一个IDCardOCRResponse的实例，与请求对象对应
        IDCardOCRResponse resp = null;
        try {
            resp = client.IDCardOCR(req);
        } catch (TencentCloudSDKException e) {
            throw new RuntimeException(e);
        }
        if (StringUtils.isEmpty(resp.getBirth())) {
            throw new SocialBusinessException("请上传真实的身份信息，" + ErrorMsg.CONTACT_SERVICE);
        }
        Integer resScore = TencentCloud.imgAuthGetScore(SocialAppConfig.getStaticResourceUrl() + socialUserIdentityAuthQO.getIdImgUrl(), SocialAppConfig.getStaticResourceUrl() + socialUserIdentityAuthQO.getSelfieImgUrl());
        if (resScore == 0) {
            throw new SocialBusinessException("认证失败，请重试，" + ErrorMsg.CONTACT_SERVICE);
        }
        Integer userId = SocialuniUserUtil.getMineUserIdNotNull();
        SocialUserIdentityAuthImgDO socialUserIdentityImgDO = new SocialUserIdentityAuthImgDO();
        socialUserIdentityImgDO.setUserId(userId);
        socialUserIdentityImgDO.setUserIdImgSrc(socialUserIdentityAuthQO.getIdImgUrl());
        socialUserIdentityImgDO.setUserSelfieImgSrc(socialUserIdentityAuthQO.getSelfieImgUrl());
        socialUserIdentityImgDO.setPreCheckScore(resScore);
        socialUserIdentityImgDO.setStatus(UserIdentityAuthStatus.audit);
        socialUserIdentityImgDO = socialUserIdentityAuthImgRepository.save(socialUserIdentityImgDO);

        SocialUserIdentityAuthDO socialUserIdentityDO = socialUserIdentityAuthRepository.findFirstByUserId(userId);
        if (socialUserIdentityDO != null) {
            String authStatus = socialUserIdentityDO.getStatus();
            if (Arrays.asList(UserIdentityAuthStatus.preAudit, UserIdentityAuthStatus.enable).contains(authStatus)) {
                throw new SocialBusinessException("您已认证成功，无需重复认证");
            }
            if (UserIdentityAuthStatus.audit.equals(authStatus)) {
                throw new SocialBusinessException("正在认证中，请您耐心等待");
            }
            if (!UserIdentityAuthStatus.fail.equals(authStatus)) {
                throw new SocialSystemException("用户认证状态异常");
            }
            socialUserIdentityDO.setAuthNum(socialUserIdentityDO.getAuthNum() + 1);
            socialUserIdentityDO.setStatus(UserIdentityAuthStatus.audit);
            socialUserIdentityDO.setUpdateTime(new Date());
        } else {
            socialUserIdentityDO = new SocialUserIdentityAuthDO();
            socialUserIdentityDO.setStatus(UserIdentityAuthStatus.preAudit);
        }
        socialUserIdentityDO.setUserIdentityImgId(socialUserIdentityImgDO.getId());
        String socialBirth = DateUtils.getBirthDateBySlashDateStr(resp.getBirth());
        Integer age = BirthdayAgeUtil.getAgeByBirth(socialBirth);
        socialUserIdentityDO.setUserId(userId);
        socialUserIdentityDO.setAge(age);
        socialUserIdentityDO.setBirth(resp.getBirth());
        socialUserIdentityDO.setName(resp.getName());
        socialUserIdentityDO.setSex(resp.getSex());
        socialUserIdentityDO.setNation(resp.getNation());
        socialUserIdentityAuthRepository.save(socialUserIdentityDO);
        System.out.println(resp.getBirth());
        System.out.println(DateUtils.getBirthDateBySlashDateStr(resp.getBirth()));
        // 输出json格式的字符串回包
        System.out.println(IDCardOCRResponse.toJsonString(resp));
        return ResultRO.success("认证信息已经提交，请您耐心等待审核结果，预计3个工作日内完成认证");
    }
}
