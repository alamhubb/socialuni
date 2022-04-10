package com.socialuni.center.web.serive;

import com.socialuni.api.model.QO.CenterReportAddQO;
import com.socialuni.center.web.utils.CenterUserUtil;
import com.socialuni.center.web.utils.UnionIdDbUtil;
import com.socialuni.social.sdk.domain.report.SoicialReportAddDomain;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.QO.SocialReportAddQO;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CenterReportService {
    @Resource
    SoicialReportAddDomain soicialReportAddDomain;

    public ResultRO<Void> addReport(CenterReportAddQO centerReportAddQO) {
        UserDO mineUser = CenterUserUtil.getMineUserNotNull();

        Integer contentId = UnionIdDbUtil.getResultByUnionId(centerReportAddQO.getReportContentType(), centerReportAddQO.getContentId());

        SocialReportAddQO socialReportAddQO = centerReportAddQO.toSocialQO(contentId);

        soicialReportAddDomain.addReport(mineUser, socialReportAddQO);
        return new ResultRO<>();
    }
}