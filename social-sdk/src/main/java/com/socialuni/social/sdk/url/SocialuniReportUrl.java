package com.socialuni.social.sdk.url;

import com.socialuni.social.model.model.QO.ReportAddQO;
import com.socialuni.social.api.model.model.ResultRO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("report")
public interface SocialuniReportUrl {
    @PostMapping("addReport")
    ResultRO<Void> addReport(@RequestBody @Valid ReportAddQO reportAddQO);

    @PostMapping("queryReportTypes")
    ResultRO<List<String>> queryReportTypes();
}