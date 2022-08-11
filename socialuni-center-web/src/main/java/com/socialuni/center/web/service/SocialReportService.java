package com.socialuni.center.web.service;

import com.socialuni.center.web.domain.report.SoicialReportAddDomain;
import com.socialuni.center.web.model.QO.SocialReportAddQO;
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