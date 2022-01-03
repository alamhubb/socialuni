package com.socialuni.admin.web.controller;

import com.socialuni.admin.web.model.DO.TencentCosAuditRecordDO;
import com.socialuni.admin.web.repository.TencentCosAuditRecordRepository;
import com.socialuni.admin.web.service.AdminPhoneService;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.RO.user.phone.SocialSendAuthCodeQO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
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
    public ResultRO<List<TencentCosAuditRecordDO>> queryImgAuditHistory() {
        List<TencentCosAuditRecordDO> list = tencentCosAuditRecordRepository.findAll();
        return ResultRO.success(list);
    }
}
