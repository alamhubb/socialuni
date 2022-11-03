package com.socialuni.admin.web.controller;

import com.socialuni.admin.web.constant.AdminAuditResultType;
import com.socialuni.admin.web.model.QO.ImgAuditQO;
import com.socialuni.admin.web.model.RO.TencentCosAuditRecordRO;
import com.socialuni.admin.web.service.ViolationService;
import com.socialuni.admin.web.utils.CheckIsAdminUtil;
import com.socialuni.social.common.sdk.enumeration.CommonStatus;
import com.socialuni.social.tance.sdk.enumeration.SocialuniContentType;
import com.socialuni.social.sdk.dao.DO.TencentCosAuditRecordDO;
import com.socialuni.social.community.sdk.model.SocialuniTalkDO;
import com.socialuni.social.sdk.dao.utils.content.SocialuniTalkDOUtil;
import com.socialuni.social.sdk.logic.factory.ListConvertUtil;
import com.socialuni.social.sdk.model.QO.SocialIntIdQO;
import com.socialuni.social.sdk.model.QO.SocialIntQO;
import com.socialuni.social.sdk.openService.ImgThumbnailType;
import com.socialuni.social.sdk.openService.TencentCosAuditRecordRepository;
import com.socialuni.social.sdk.openService.TencentCosAuditStatus;
import com.socialuni.social.common.sdk.model.ResultRO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("audit")
@Slf4j
public class TencentImgAuditRecordController {
    @Resource
    TencentCosAuditRecordRepository tencentCosAuditRecordRepository;
    @Resource
    ViolationService violationService;

    /**
     * 查询cos图片审核历史详情
     */
    @PostMapping("queryImgAuditHistory")
    public ResultRO<List<TencentCosAuditRecordRO>> queryImgAuditHistory(@RequestBody SocialIntQO intQO) {
        CheckIsAdminUtil.checkAdmin();
        List<TencentCosAuditRecordDO> list = tencentCosAuditRecordRepository.findTop20ByStatusNotAndScoreGreaterThanEqualAndForbiddenStatusAndImgThumbTypeAndUserIdNotNullAndContentTypeOrderByIdDesc(CommonStatus.delete, intQO.getNumber(), TencentCosAuditStatus.enable, ImgThumbnailType.thumbnail, SocialuniContentType.talk);
        List<TencentCosAuditRecordRO> listRo = ListConvertUtil.toList(TencentCosAuditRecordRO::new, list);
        return ResultRO.success(listRo);
    }

    /**
     * 查询cos图片审核历史详情
     */
    @PostMapping("reviewAuditHistory")
    public ResultRO<Object> queryImgAuditHistory(@RequestBody List<SocialIntIdQO> idQOList) {
        CheckIsAdminUtil.checkAdmin();
        List<TencentCosAuditRecordDO> list = new ArrayList<>();
        for (SocialIntIdQO socialIntIdQO : idQOList) {
            TencentCosAuditRecordDO tencentCosAuditRecordDO = tencentCosAuditRecordRepository.getOne(socialIntIdQO.getId());
            tencentCosAuditRecordDO.setStatus(CommonStatus.delete);
            tencentCosAuditRecordDO.setUpdateTime(new Date());
            list.add(tencentCosAuditRecordDO);
        }
        tencentCosAuditRecordRepository.saveAll(list);
        return ResultRO.success();
    }

    @PostMapping("auditImgList")
    public ResultRO<String> reportAuditList(@RequestBody @NotNull List<ImgAuditQO> auditQOS) {
        CheckIsAdminUtil.checkAdmin();
        ResultRO<String> resultRO = new ResultRO<>();
        for (ImgAuditQO auditQO : auditQOS) {
            this.reportAudit(auditQO);
        }
        resultRO.setData("审核成功");
        return resultRO;
    }

    public void reportAudit(ImgAuditQO auditQO) {
        TencentCosAuditRecordDO tencentCosAuditRecordDO = tencentCosAuditRecordRepository.getOne(auditQO.getId());
        tencentCosAuditRecordDO.setStatus(CommonStatus.delete);
        if (!AdminAuditResultType.noViolation.equals(auditQO.getViolateType())) {
            SocialuniTalkDO talkDO = SocialuniTalkDOUtil.getTalkNotNull(tencentCosAuditRecordDO.getImgParentContentId());
            violationService.modelContentViolation(talkDO, auditQO.getViolateType());
        }
        tencentCosAuditRecordRepository.save(tencentCosAuditRecordDO);
    }

    /*@PostMapping("test")
    public ResultRO<Object> test() {
        List<TencentCosAuditRecordDO> list = tencentCosAuditRecordRepository.findAll();
        for (TencentCosAuditRecordDO tencentCosAuditRecordDO : list) {
            log.info(tencentCosAuditRecordDO.getId().toString());
            tencentCloudCosService.setCosImgSocialInfo(tencentCosAuditRecordDO);
        }
        return ResultRO.success();
    }*/
}
