package com.socialuni.social.sdk.feignAPI.community;

import com.socialuni.social.common.model.ResultRO;
import com.socialuni.social.sdk.model.QO.SocialuniReportAddQO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

//@RequestMapping("socialuni/report")
@FeignClient(name = "report", url = "${socialuni.central-server-url:https://api.socialuni.cn}", path = "socialuni/report")
@Tag(name = "社区模块/举报模块")
public interface SocialuniReportAPI {
    @PostMapping("addReport")
    @Operation(summary = "举报")
    ResultRO<String> addReport(@RequestBody @Valid SocialuniReportAddQO socialReportAddQO);

    @GetMapping("queryReportTypes")
    @Operation(summary = "查询举报类型列表")
    ResultRO<List<String>> queryReportTypes();
}