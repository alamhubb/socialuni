package com.socialuni.sdk.logic.service.report;

import com.socialuni.sdk.logic.domain.report.SoicialReportAddDomain;
import com.socialuni.sdk.model.QO.SocialReportAddQO;
import com.socialuni.social.web.sdk.model.ResultRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialReportService {
    @Resource
    SoicialReportAddDomain soicialReportAddDomain;

    public ResultRO<String> addReport(SocialReportAddQO socialReportAddQO) {
        return soicialReportAddDomain.addReport(socialReportAddQO);
    }
}