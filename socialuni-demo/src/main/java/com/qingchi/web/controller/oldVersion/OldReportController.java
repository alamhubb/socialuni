package com.qingchi.web.controller.oldVersion;

import com.qingchi.web.model.old.ReportAddQO;
import com.socialuni.api.feignAPI.SocialuniReportAPI;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.sdk.constant.ErrorMsg;
import com.socialuni.social.sdk.constant.ViolateType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("report")
public class OldReportController {
    //    implements SocialuniReportAPI
    @Resource
    SocialuniReportAPI socialuniReportAPI;

    @PostMapping("addReport")
    public ResultRO<String> addReport(@RequestBody @Valid ReportAddQO reportAddQO) {

        socialuniReportAPI.addReport(reportAddQO.toCenterReportQO());

        return new ResultRO<>(ErrorMsg.reportSubmitHide);
    }

    @PostMapping("queryReportTypes")
    public ResultRO<List<String>> queryReportTypes() {
        return new ResultRO<>(ViolateType.frontShowReportTypes);
    }
}
