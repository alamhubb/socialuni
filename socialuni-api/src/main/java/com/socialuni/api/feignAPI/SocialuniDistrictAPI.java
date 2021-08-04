package com.socialuni.api.feignAPI;

import com.socialuni.social.model.model.RO.ResultRO;
import com.socialuni.social.model.model.RO.app.SocialDistrictRO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:45
 */

@RequestMapping("district")
@FeignClient(name = "district", url = "${socialuni.server-url:https://api.socialuni.com}")
public interface SocialuniDistrictAPI {
    @PostMapping("queryHotDistricts")
    ResultRO<List<SocialDistrictRO>> queryHotDistricts();

    @PostMapping("queryDistricts")
    ResultRO<List<SocialDistrictRO>> queryDistricts();
}
