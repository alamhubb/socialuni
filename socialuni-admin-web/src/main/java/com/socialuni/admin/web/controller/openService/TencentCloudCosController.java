package com.socialuni.admin.web.controller.openService;

import com.socialuni.admin.web.model.DO.TencentCosAuditRecordDO;
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
    TencentCloudCosService tencentCloudCosService;

    @PostMapping("contentAuditCallback")
    public ResultRO<Object> tencentCloudCosContentAuditCallback(@RequestBody TencentCosCallbackResultRO result) {
        TencentCosCallbackDataRO callbackDataRO = result.getData();
        TencentCosAuditRecordDO recordDO = new TencentCosAuditRecordDO();

        //设置result内容
        recordDO.setCode(result.getCode());
        recordDO.setMessage(result.getMessage());

        //设置data内容
        recordDO.setTraceId(callbackDataRO.getTrace_id());
        recordDO.setForbiddenStatus(callbackDataRO.getForbidden_status());
        recordDO.setEvent(callbackDataRO.getEvent());
        recordDO.setResult(callbackDataRO.getResult());
        recordDO.setUrl(callbackDataRO.getUrl());
        //pornInfo
        TencentCosCallbackDataPornInfo pornInfo = callbackDataRO.getPorn_info();
        TencentCosCallbackDataPornInfo adsInfo = callbackDataRO.getAds_info();
        if (pornInfo != null) {
            recordDO.setHitFlag(pornInfo.getHit_flag());
            recordDO.setScore(pornInfo.getScore());
            recordDO.setLabel(pornInfo.getLabel());
            //ads_info
        } else if (adsInfo != null) {
            recordDO.setHitFlag(adsInfo.getHit_flag());
            recordDO.setScore(adsInfo.getScore());
            recordDO.setLabel(adsInfo.getLabel());
        }
        tencentCloudCosService.setCosImgSocialInfo(recordDO);
        return ResultRO.success();
    }
}
