package com.socialuni.social.sdk.controller;

import com.socialuni.social.common.sdk.model.ResultRO;
import com.socialuni.social.sdk.constant.ViolateType;
import com.socialuni.social.sdk.feignAPI.community.SocialuniReportAPI;
import com.socialuni.social.sdk.logic.service.report.SocialuniReportService;
import com.socialuni.social.sdk.model.QO.SocialuniReportAddQO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("socialuni/report")
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
