package com.socialuni.center.web.serive;

import com.socialuni.center.web.model.QO.CenterReportAddQO;
import com.socialuni.center.web.utils.CenterUserUtil;
import com.socialuni.center.web.utils.UnionIdDbUtil;
import com.socialuni.center.web.domain.report.SoicialReportAddDomain;
import com.socialuni.center.web.model.DO.user.SocialUserDO;
import com.socialuni.center.web.model.QO.SocialReportAddQO;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CenterReportService {
    @Resource
    SoicialReportAddDomain soicialReportAddDomain;

    public ResultRO<String> addReport(CenterReportAddQO centerReportAddQO) {
        SocialUserDO mineUser = CenterUserUtil.getMineUserNotNull();

        Integer contentId = UnionIdDbUtil.getResultByUnionId(centerReportAddQO.getReportContentType(), centerReportAddQO.getContentId());

        SocialReportAddQO socialReportAddQO = centerReportAddQO.toSocialQO(contentId);

        return soicialReportAddDomain.addReport(mineUser, socialReportAddQO);
    }
}