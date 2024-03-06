package com.socialuni.social.admin.controller;

import com.socialuni.social.admin.constant.AdminAuditResultType;
import com.socialuni.social.admin.model.QO.ImgAuditQO;
import com.socialuni.social.admin.model.RO.TencentCosAuditRecordRO;
import com.socialuni.social.admin.service.ViolationService;
import com.socialuni.social.admin.utils.CheckIsAdminUtil;
import com.socialuni.social.common.api.constant.SocialuniContentType;
import com.socialuni.social.common.api.enumeration.SocialuniCommonStatus;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.sdk.utils.ListConvertUtil;
import com.socialuni.social.community.sdk.dao.DO.SocialuniTalkDO;
import com.socialuni.social.sdk.dao.DO.TencentCosAuditRecordDO;
import com.socialuni.social.community.sdk.dao.SocialuniTalkDOUtil;
import com.socialuni.social.common.sdk.model.QO.SocialIntIdQO;
import com.socialuni.social.common.sdk.model.QO.SocialIntQO;
import com.socialuni.social.sdk.openService.ImgThumbnailType;
import com.socialuni.social.sdk.openService.TencentCosAuditRecordRepository;
import com.socialuni.social.sdk.openService.TencentCosAuditStatus;
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
        List<TencentCosAuditRecordDO> list = tencentCosAuditRecordRepository.findTop20ByStatusNotAndScoreGreaterThanEqualAndForbiddenStatusAndImgThumbTypeAndUserIdNotNullAndContentTypeOrderByIdDesc(SocialuniCommonStatus.delete, intQO.getNumber(), TencentCosAuditStatus.enable, ImgThumbnailType.thumbnail, SocialuniContentType.talk);
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
            tencentCosAuditRecordDO.setStatus(SocialuniCommonStatus.delete);
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
        tencentCosAuditRecordDO.setStatus(SocialuniCommonStatus.delete);
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
