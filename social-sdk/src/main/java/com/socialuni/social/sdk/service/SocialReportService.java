package com.socialuni.social.sdk.service;

import com.socialuni.social.sdk.domain.report.SoicialReportAddDomain;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import com.socialuni.social.model.model.QO.SocialReportAddQO;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialReportService {
    @Resource
    SoicialReportAddDomain soicialReportAddDomain;

    public ResultRO<String> addReport(SocialReportAddQO socialReportAddQO) {
        UserDO mineUser = SocialUserUtil.getMineUserAllowNull();
        soicialReportAddDomain.addReport(mineUser, socialReportAddQO);
        return soicialReportAddDomain.addReport(mineUser, socialReportAddQO);
    }
}