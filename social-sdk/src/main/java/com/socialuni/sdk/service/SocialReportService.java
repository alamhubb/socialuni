package com.socialuni.sdk.service;

import com.socialuni.sdk.domain.report.SoicialReportAddDomain;
import com.socialuni.sdk.model.DO.user.UserDO;
import com.socialuni.sdk.utils.SocialUserUtil;
import com.socialuni.social.model.model.QO.ReportAddQO;
import com.socialuni.social.model.model.RO.ResultRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialReportService {
    @Resource
    SoicialReportAddDomain soicialReportAddDomain;

    public ResultRO<Void> addReport(ReportAddQO reportAddQO) {
        UserDO mineUser = SocialUserUtil.getMineUser();
        soicialReportAddDomain.addReport(mineUser, reportAddQO);
        return new ResultRO<>();
    }
}