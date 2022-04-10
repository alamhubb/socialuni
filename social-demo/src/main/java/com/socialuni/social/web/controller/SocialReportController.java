package com.socialuni.social.web.controller;

import com.socialuni.social.sdk.service.SocialReportService;
import com.socialuni.social.sdk.constant.ViolateType;
import com.socialuni.social.model.model.QO.SocialReportAddQO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.sdk.url.SocialuniReportUrl;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class SocialReportController implements SocialuniReportUrl {
    @Resource
    SocialReportService socialReportService;

    @Override
    public ResultRO<String> addReport(SocialReportAddQO socialReportAddQO) {
        return socialReportService.addReport(socialReportAddQO);
    }

    @Override
    public ResultRO<List<String>> queryReportTypes() {
        return new ResultRO<>(ViolateType.frontShowReportTypes);
    }
}
