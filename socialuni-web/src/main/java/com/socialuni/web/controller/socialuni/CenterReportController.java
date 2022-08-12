package com.socialuni.web.controller.socialuni;

import com.socialuni.sdk.api.SocialuniReportAPIImpl;
import com.socialuni.sdk.constant.ViolateType;
import com.socialuni.sdk.feignAPI.SocialuniReportAPI;
import com.socialuni.sdk.model.QO.CenterReportAddQO;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class CenterReportController implements SocialuniReportAPI {
    @Resource
    SocialuniReportAPIImpl centerReportAPIImpl;

    @Override
    public ResultRO<String> addReport(CenterReportAddQO socialReportAddQO) {
        return centerReportAPIImpl.addReport(socialReportAddQO);
    }

    @Override
    public ResultRO<List<String>> queryReportTypes() {
        return new ResultRO<>(ViolateType.frontShowReportTypes);
    }
}
