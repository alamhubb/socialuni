package com.socialuni.sdk.feignAPI;

import com.socialuni.sdk.model.RO.app.SocialDistrictRO;
import com.socialuni.social.web.sdk.model.ResultRO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:45
 */

@RequestMapping("socialuni/district")
@FeignClient(name = "district", url = "${socialuni.central-server-url:https://api.socialuni.cn}")
public interface SocialuniDistrictAPI {
    @PostMapping("queryHotDistricts")
    ResultRO<List<SocialDistrictRO>> queryHotDistricts();

    @PostMapping("queryDistricts")
    ResultRO<List<SocialDistrictRO>> queryDistricts();
}
