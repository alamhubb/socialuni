package com.socialuni.center.web.controller;

import com.socialuni.center.web.feignAPI.SocialuniReportAPI;
import com.socialuni.center.web.model.QO.CenterReportAddQO;
import com.socialuni.center.web.serive.CenterReportService;
import com.socialuni.center.web.constant.ViolateType;
import com.socialuni.center.web.model.QO.SocialReportAddQO;
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
