package com.socialuni.center.web.url;

import com.socialuni.social.model.model.QO.SocialReportAddQO;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("report")
public interface SocialuniReportUrl {
    @PostMapping("addReport")
    ResultRO<String> addReport(@RequestBody @Valid SocialReportAddQO socialReportAddQO);

    @PostMapping("queryReportTypes")
    ResultRO<List<String>> queryReportTypes();
}