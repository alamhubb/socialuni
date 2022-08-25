package com.socialuni.sdk.controller;

import com.socialuni.sdk.constant.ViolateType;
import com.socialuni.sdk.feignAPI.SocialuniReportAPI;
import com.socialuni.sdk.model.QO.CenterReportAddQO;
import com.socialuni.sdk.serive.CenterReportService;
import com.socialuni.social.web.sdk.model.ResultRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class CenterReportController implements SocialuniReportAPI {
    @Resource
    CenterReportService centerReportAPIImpl;

    @Override
    public ResultRO<String> addReport(CenterReportAddQO socialReportAddQO) {
        ResultRO<String> resultRO = centerReportAPIImpl.addReport(socialReportAddQO);
        return resultRO;
    }

    @Override
    public ResultRO<List<String>> queryReportTypes() {
        return new ResultRO<>(ViolateType.frontShowReportTypes);
    }
}
