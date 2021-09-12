package com.socialuni.demo.socialuni;

import com.socialuni.api.feignAPI.SocialuniReportAPI;
import com.socialuni.api.model.QO.CenterReportAddQO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.sdk.constant.ViolateType;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class ReportController implements SocialuniReportAPI {
    @Resource
    SocialuniReportAPI socialuniReportAPI;

    @Override
    public ResultRO<Void> addReport(CenterReportAddQO centerReportAddQO) {
        return socialuniReportAPI.addReport(centerReportAddQO);
    }

    @Override
    public ResultRO<List<String>> queryReportTypes() {
        return new ResultRO<>(ViolateType.frontShowReportTypes);
    }
}
