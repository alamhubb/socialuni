package com.socialuni.sdk.controller;

import com.socialuni.sdk.constant.ViolateType;
import com.socialuni.sdk.feignAPI.community.SocialuniReportAPI;
import com.socialuni.sdk.logic.service.report.SocialuniReportService;
import com.socialuni.sdk.model.QO.SocialuniReportAddQO;
import com.socialuni.social.common.model.ResultRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class SocialuniReportController implements SocialuniReportAPI {
    @Resource
    SocialuniReportService centerReportAPIImpl;

    @Override
    public ResultRO<String> addReport(SocialuniReportAddQO socialReportAddQO) {
        ResultRO<String> resultRO = centerReportAPIImpl.addReport(socialReportAddQO);
        return resultRO;
    }

    @Override
    public ResultRO<List<String>> queryReportTypes() {
        return new ResultRO<>(ViolateType.frontShowReportTypes);
    }
}
