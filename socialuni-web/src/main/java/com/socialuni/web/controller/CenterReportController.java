package com.socialuni.web.controller;

import com.socialuni.sdk.feignAPI.SocialuniReportAPI;
import com.socialuni.sdk.model.QO.CenterReportAddQO;
import com.socialuni.sdk.serive.CenterReportService;
import com.socialuni.sdk.constant.ViolateType;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class CenterReportController implements SocialuniReportAPI {
    @Resource
    CenterReportService centerReportService;

    @Override
    public ResultRO<String> addReport(CenterReportAddQO socialReportAddQO) {
        return centerReportService.addReport(socialReportAddQO);
    }

    @Override
    public ResultRO<List<String>> queryReportTypes() {
        return new ResultRO<>(ViolateType.frontShowReportTypes);
    }
}