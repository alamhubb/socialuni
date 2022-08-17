package com.socialuni.center.controller.socialuni;

import com.socialuni.sdk.constant.ViolateType;
import com.socialuni.sdk.model.QO.CenterReportAddQO;
import com.socialuni.sdk.serive.CenterReportService;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
public class CenterReportController {
    @Resource
    CenterReportService centerReportAPIImpl;

    @PostMapping("addReport")
    public ResultRO<String> addReport(@RequestBody @Valid CenterReportAddQO socialReportAddQO) {
        ResultRO<String> resultRO = centerReportAPIImpl.addReport(socialReportAddQO);
        return resultRO;
    }

    @PostMapping("queryReportTypes")
    public ResultRO<List<String>> queryReportTypes() {
        return new ResultRO<>(ViolateType.frontShowReportTypes);
    }
}
