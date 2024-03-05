package com.socialuni.social.sdk.feignAPI.openData;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.user.sdk.model.RO.SocialDistrictRO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:45
 */

//@RequestMapping("socialuni/district")
@FeignClient(name = "district", url = "${socialuni.central-server-url:https://api.socialuni.cn}", path = "socialuni/district")
@Tag(name = "开放数据/地理位置")
public interface SocialuniDistrictAPI {
    @GetMapping("queryHotDistricts")
    @Operation(summary = "查询热门城市")
    ResultRO<List<SocialDistrictRO>> queryHotDistricts();

    @GetMapping("queryDistricts")
    @Operation(summary = "查询全国城市列表")
    ResultRO<List<SocialDistrictRO>> queryDistricts();
}
