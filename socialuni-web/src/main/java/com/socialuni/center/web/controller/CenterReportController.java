package com.socialuni.center.web.controller;

import com.socialuni.api.feignAPI.SocialuniReportAPI;
import com.socialuni.center.web.serive.CenterReportService;
import com.socialuni.sdk.constant.ViolateType;
import com.socialuni.social.model.model.QO.ReportAddQO;
import com.socialuni.social.model.model.RO.ResultRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class CenterReportController implements SocialuniReportAPI {
    @Resource
    CenterReportService centerReportService;

    @Override
    public ResultRO<Void> addReport(ReportAddQO reportAddQO) {
        return centerReportService.addReport(reportAddQO);
    }

    @Override
    public ResultRO<List<String>> queryReportTypes() {
        return new ResultRO<>(ViolateType.frontShowReportTypes);
    }
}
