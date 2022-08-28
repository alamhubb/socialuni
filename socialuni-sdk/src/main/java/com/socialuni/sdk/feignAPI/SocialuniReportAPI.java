package com.socialuni.sdk.feignAPI;

import com.socialuni.sdk.model.QO.CenterReportAddQO;
import com.socialuni.social.web.sdk.model.ResultRO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("socialuni/report")
@FeignClient(name = "report", url = "${socialuni.central-server-url:https://api.socialuni.cn}")
public interface SocialuniReportAPI {
    @PostMapping("addReport")
    ResultRO<String> addReport(@RequestBody @Valid CenterReportAddQO socialReportAddQO);

    @PostMapping("queryReportTypes")
    ResultRO<List<String>> queryReportTypes();
}