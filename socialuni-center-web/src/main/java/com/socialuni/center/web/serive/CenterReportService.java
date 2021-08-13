package com.socialuni.center.web.serive;

import com.socialuni.center.web.utils.CenterUserUtil;
import com.socialuni.social.sdk.domain.report.SoicialReportAddDomain;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.QO.ReportAddQO;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CenterReportService {
    @Resource
    SoicialReportAddDomain soicialReportAddDomain;

    public ResultRO<Void> addReport(ReportAddQO reportAddQO) {
        UserDO mineUser = CenterUserUtil.getMineUser();
        soicialReportAddDomain.addReport(mineUser, reportAddQO);
        return new ResultRO<>();
    }
}