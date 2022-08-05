package com.socialuni.center.web.feignAPI;

import com.socialuni.social.api.model.ResultRO;
import com.socialuni.center.web.model.RO.app.SocialDistrictRO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:45
 */

@RequestMapping("district")
@FeignClient(name = "district", url = "${socialuni.server-url:https://api.socialuni.cn}")
public interface SocialuniDistrictAPI {
    @PostMapping("queryHotDistricts")
    ResultRO<List<SocialDistrictRO>> queryHotDistricts();

    @PostMapping("queryDistricts")
    ResultRO<List<SocialDistrictRO>> queryDistricts();
}
