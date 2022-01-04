package com.socialuni.admin.web.controller;

import com.socialuni.admin.web.constant.TencentCosAuditStatus;
import com.socialuni.admin.web.model.DO.TencentCosAuditRecordDO;
import com.socialuni.admin.web.model.RO.TencentCosAuditRecordRO;
import com.socialuni.admin.web.repository.TencentCosAuditRecordRepository;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.constant.CommonStatus;
import com.socialuni.social.model.model.QO.SocialIntIdQO;
import com.socialuni.social.sdk.factory.ListConvertUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("audit")
public class TencentImgAuditRecordController {
    @Resource
    TencentCosAuditRecordRepository tencentCosAuditRecordRepository;

    /**
     * 查询cos图片审核历史详情
     */
    @PostMapping("queryImgAuditHistory")
    @ResponseBody
    public ResultRO<List<TencentCosAuditRecordRO>> queryImgAuditHistory() {
        List<TencentCosAuditRecordDO> list = tencentCosAuditRecordRepository.findAllByStatusNotAndForbiddenStatusOrderByIdDesc(CommonStatus.delete, TencentCosAuditStatus.enable);
        List<TencentCosAuditRecordRO> listRo = ListConvertUtil.toList(TencentCosAuditRecordRO::new, list);
        return ResultRO.success(listRo);
    }

    /**
     * 查询cos图片审核历史详情
     */
    @PostMapping("reviewAuditHistory")
    @ResponseBody
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
}
