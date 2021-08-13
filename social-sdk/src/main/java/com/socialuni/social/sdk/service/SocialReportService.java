package com.socialuni.social.sdk.service;

import com.socialuni.social.sdk.domain.report.SoicialReportAddDomain;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import com.socialuni.social.model.model.QO.ReportAddQO;
import com.socialuni.social.api.model.model.ResultRO;
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