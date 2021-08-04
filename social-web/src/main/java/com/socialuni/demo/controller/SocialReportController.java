package com.socialuni.demo.controller;

import com.socialuni.sdk.service.SocialReportService;
import com.socialuni.sdk.constant.ViolateType;
import com.socialuni.social.model.model.QO.ReportAddQO;
import com.socialuni.social.model.model.RO.ResultRO;
import com.socialuni.sdk.url.SocialuniReportUrl;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class SocialReportController implements SocialuniReportUrl {
    @Resource
    SocialReportService socialReportService;

    @Override
    public ResultRO<Void> addReport(ReportAddQO reportAddQO) {
        return socialReportService.addReport(reportAddQO);
    }

    @Override
    public ResultRO<List<String>> queryReportTypes() {
        return new ResultRO<>(ViolateType.frontShowReportTypes);
    }
}
