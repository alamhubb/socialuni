package com.socialuni.admin.web.controller.openService;

import com.socialuni.admin.web.model.DO.TencentCosAuditRecordDO;
import com.socialuni.social.api.model.ResultRO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("openService/tencent/cos")
public class TencentCloudCosController {
    @PostMapping("contentAuditCallback")
    public ResultRO<Object> tencentCloudCosContentAuditCallback(@RequestBody TencentCosCallbackResultRO result) {
        System.out.println(result);

        TencentCosCallbackDataRO callbackDataRO = result.getData();


        TencentCosAuditRecordDO recordDO = new TencentCosAuditRecordDO();
        recordDO.setCode(callbackDataRO.get);
        return ResultRO.success();
    }
}
