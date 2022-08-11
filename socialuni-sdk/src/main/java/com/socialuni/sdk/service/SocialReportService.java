package com.socialuni.sdk.service;

import com.socialuni.sdk.domain.report.SoicialReportAddDomain;
import com.socialuni.sdk.model.QO.SocialReportAddQO;
import com.socialuni.social.api.model.ResultRO;
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