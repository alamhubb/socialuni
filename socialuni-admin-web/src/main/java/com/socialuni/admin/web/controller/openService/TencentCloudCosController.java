package com.socialuni.admin.web.controller.openService;

import com.socialuni.admin.web.model.DO.TencentCosAuditRecordDO;
import com.socialuni.admin.web.repository.TencentCosAuditRecordRepository;
import com.socialuni.social.api.model.ResultRO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("openService/tencent/cos")
public class TencentCloudCosController {
    @Resource
    TencentCosAuditRecordRepository tencentCosAuditRecordRepository;

    @PostMapping("contentAuditCallback")
    public ResultRO<Object> tencentCloudCosContentAuditCallback(@RequestBody TencentCosCallbackResultRO result) {
        System.out.println(result);
        TencentCosCallbackDataRO callbackDataRO = result.getData();
        TencentCosAuditRecordDO recordDO = new TencentCosAuditRecordDO();

        //设置result内容
        recordDO.setCode(result.getCode());
        recordDO.setMessage(result.getMessage());

        //设置data内容
        recordDO.setTrace_id(callbackDataRO.getTrace_id());
        recordDO.setForbidden_status(callbackDataRO.getForbidden_status());
        recordDO.setEvent(callbackDataRO.getEvent());
        recordDO.setResult(callbackDataRO.getResult());
        recordDO.setUrl(callbackDataRO.getUrl());
        //pornInfo
        TencentCosCallbackDataPornInfo pornInfo = callbackDataRO.getPorn_info();
        recordDO.setHit_flag(pornInfo.getHit_flag());
        recordDO.setScore(pornInfo.getScore());
        recordDO.setLabel(pornInfo.getLabel());

        tencentCosAuditRecordRepository.save(recordDO);
        return ResultRO.success();
    }
}
