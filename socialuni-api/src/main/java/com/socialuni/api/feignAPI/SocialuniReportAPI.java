package com.socialuni.api.feignAPI;

import com.socialuni.api.model.QO.CenterReportAddQO;
import com.socialuni.social.model.model.QO.SocialReportAddQO;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("report")
@FeignClient(name = "report", url = "${socialuni.server-url:https://api.socialuni.com}")
public interface SocialuniReportAPI {
    @PostMapping("addReport")
    ResultRO<Void> addReport(@RequestBody @Valid CenterReportAddQO socialReportAddQO);

    @PostMapping("queryReportTypes")
    ResultRO<List<String>> queryReportTypes();
}