package com.socialuni.sdk.feignAPI;

import com.socialuni.sdk.model.RO.app.SocialDistrictRO;
import com.socialuni.social.web.sdk.model.ResultRO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:45
 */

@RequestMapping("socialuni/district")
@FeignClient(name = "district", url = "${socialuni.central-server-url:https://api.socialuni.cn}")
@Tag(name = "开放数据/地理位置")
public interface SocialuniDistrictAPI {
    @GetMapping("queryHotDistricts")
    @Operation(summary = "查询热门城市")
    ResultRO<List<SocialDistrictRO>> queryHotDistricts();

    @GetMapping("queryDistricts")
    @Operation(summary = "查询全国城市列表")
    ResultRO<List<SocialDistrictRO>> queryDistricts();
}
