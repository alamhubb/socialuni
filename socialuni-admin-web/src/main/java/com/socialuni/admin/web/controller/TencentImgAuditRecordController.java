package com.socialuni.admin.web.controller;

import com.socialuni.admin.web.constant.ImgThumbnailType;
import com.socialuni.admin.web.constant.TencentCosAuditStatus;
import com.socialuni.admin.web.model.DO.TencentCosAuditRecordDO;
import com.socialuni.admin.web.model.QO.ImgAuditQO;
import com.socialuni.admin.web.model.RO.TencentCosAuditRecordRO;
import com.socialuni.admin.web.repository.TencentCosAuditRecordRepository;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.constant.CommonStatus;
import com.socialuni.social.model.model.QO.SocialIntIdQO;
import com.socialuni.social.model.model.QO.SocialIntQO;
import com.socialuni.social.sdk.factory.ListConvertUtil;
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

    /**
     * 查询cos图片审核历史详情
     */
    @PostMapping("queryImgAuditHistory")
    public ResultRO<List<TencentCosAuditRecordRO>> queryImgAuditHistory(@RequestBody SocialIntQO intQO) {
        List<TencentCosAuditRecordDO> list = tencentCosAuditRecordRepository.findTop200ByStatusNotAndScoreGreaterThanEqualAndForbiddenStatusAndImgThumbTypeAndUserIdNotNullOrderByIdDesc(CommonStatus.delete, intQO.getNumber(), TencentCosAuditStatus.enable, ImgThumbnailType.thumbnail);
        List<TencentCosAuditRecordRO> listRo = ListConvertUtil.toList(TencentCosAuditRecordRO::new, list);
        return ResultRO.success(listRo);
    }

    /**
     * 查询cos图片审核历史详情
     */
    @PostMapping("reviewAuditHistory")
    public ResultRO<Object> queryImgAuditHistory(@RequestBody List<SocialIntIdQO> idQOList) {
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
        ResultRO<String> resultRO = new ResultRO<>();
        /*for (ReportVO auditVO : auditVOS) {
            //首先校验 reportid是否存在
            ResultRO<String> methodResult = adminReportService.getStringResultVO(auditVO);
            if (methodResult != null) {
                return methodResult;
            }
        }*/
        resultRO.setData("审核成功");
        return resultRO;
    }

    public ResultRO<String> reportAudit(ImgAuditQO auditQO) {
        return null;
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
