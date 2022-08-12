package com.socialuni.sdk.api;

import com.socialuni.sdk.constant.ViolateType;
import com.socialuni.sdk.feignAPI.SocialuniReportAPI;
import com.socialuni.sdk.model.QO.CenterReportAddQO;
import com.socialuni.sdk.serive.CenterReportService;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SocialuniReportAPIImpl implements SocialuniReportAPI {
    @Resource
    CenterReportService centerReportService;

    @Override
    public ResultRO<String> addReport(CenterReportAddQO socialReportAddQO) {
        return centerReportService.addReport(socialReportAddQO);
    }

    @Override
    public ResultRO<List<String>> queryReportTypes() {
        return new ResultRO<>(ViolateType.frontShowReportTypes);
    }
}
