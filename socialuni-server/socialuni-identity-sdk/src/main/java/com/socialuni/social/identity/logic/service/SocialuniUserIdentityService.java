package com.socialuni.social.identity.logic.service;

import com.socialuni.social.common.api.constant.SocialSystemConst;
import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.api.exception.exception.SocialSystemException;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.sdk.constant.ErrorMsg;
import com.socialuni.social.user.sdk.constant.UserIdentityAuthStatus;
import com.socialuni.social.identity.dao.DO.SocialUserIdentityAuthDO;
import com.socialuni.social.identity.dao.DO.SocialUserIdentityAuthImgDO;
import com.socialuni.social.identity.dao.repository.SocialUserIdentityAuthImgRepository;
import com.socialuni.social.identity.dao.repository.SocialUserIdentityAuthRepository;
import com.socialuni.social.content.tencent.TencentCloudAPI;
import com.socialuni.social.user.sdk.model.QO.SocialUserIdentityAuthQO;
import com.socialuni.social.user.sdk.model.RO.SocialUserIdentityAuthPreCheckRO;
import com.socialuni.social.common.sdk.utils.SocialuniDateUtils;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.content.utils.BirthdayAgeUtil;
import com.socialuni.social.common.api.constant.SocialuniSystemConst;
import com.socialuni.social.tance.sdk.facade.ConfigFacade;
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
    SocialUserIdentityAuthImgRepository socialUserIdentityAuthImgRepository;
    @Resource
    SocialUserIdentityAuthRepository socialUserIdentityAuthRepository;


    public ResultRO<SocialUserIdentityAuthPreCheckRO> userIdentityAuthPreCheck(SocialUserIdentityAuthQO socialUseIdentityAuthQO) {
        Integer resScore = TencentCloudAPI.imgAuthGetScore(SocialuniSystemConst.getStaticResourceUrl() + socialUseIdentityAuthQO.getIdImgUrl(), SocialuniSystemConst.getStaticResourceUrl() + socialUseIdentityAuthQO.getSelfieImgUrl());
        if (resScore == 0) {
            ConfigFacade.throwBusinessException("预校验失败，请重试，" + ErrorMsg.CONTACT_SERVICE, SocialSystemConst.CONFIGS_KEY_QQ_ACCOUNT);
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
        OcrClient client = TencentCloudAPI.getOcrClient();
        // 实例化一个请求对象,每个接口都会对应一个request对象
        IDCardOCRRequest req = new IDCardOCRRequest();
        req.setImageUrl(SocialuniSystemConst.getStaticResourceUrl() + socialUserIdentityAuthQO.getIdImgUrl());
        // 返回的resp是一个IDCardOCRResponse的实例，与请求对象对应
        IDCardOCRResponse resp = null;
        try {
            resp = client.IDCardOCR(req);
        } catch (TencentCloudSDKException e) {
            throw new RuntimeException(e);
        }
        if (StringUtils.isEmpty(resp.getBirth())) {
            ConfigFacade.throwBusinessException("请上传真实的身份信息，" + ErrorMsg.CONTACT_SERVICE, SocialSystemConst.CONFIGS_KEY_QQ_ACCOUNT);
        }
        Integer resScore = TencentCloudAPI.imgAuthGetScore(SocialuniSystemConst.getStaticResourceUrl() + socialUserIdentityAuthQO.getIdImgUrl(), SocialuniSystemConst.getStaticResourceUrl() + socialUserIdentityAuthQO.getSelfieImgUrl());
        if (resScore == 0) {
            ConfigFacade.throwBusinessException("认证失败，请重试，" + ErrorMsg.CONTACT_SERVICE, SocialSystemConst.CONFIGS_KEY_QQ_ACCOUNT);
        }
        Long userId = SocialuniUserUtil.getMineUserIdNotNull();
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
        String socialBirth = SocialuniDateUtils.getBirthDateBySlashDateStr(resp.getBirth());
        Integer age = BirthdayAgeUtil.getAgeByBirth(socialBirth);
        socialUserIdentityDO.setUserId(userId);
        socialUserIdentityDO.setAge(age);
        socialUserIdentityDO.setBirth(resp.getBirth());
        socialUserIdentityDO.setName(resp.getName());
        socialUserIdentityDO.setSex(resp.getSex());
        socialUserIdentityDO.setNation(resp.getNation());
        socialUserIdentityAuthRepository.save(socialUserIdentityDO);
        // 输出json格式的字符串回包
        return ResultRO.success("认证信息已经提交，请您耐心等待审核结果，预计3个工作日内完成认证");
    }
}
