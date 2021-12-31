package com.socialuni.admin.web.controller.openService;

import com.socialuni.social.api.model.ResultRO;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("openService/tencent/cos")
public class TencentCloudCosController {
    @PostMapping("contentAuditCallback")
    public ResultRO<Object> tencentCloudCosContentAuditCallback(@RequestBody TencentCosCallbackResult result) {
        System.out.println(result);
        return ResultRO.success();
    }
}
