package com.socialuni.center.controller.socialuni;

import com.socialuni.sdk.config.SocialAppConfig;
import com.socialuni.sdk.constant.AppData;
import com.socialuni.sdk.feignAPI.SocialuniDistrictAPI;
import com.socialuni.sdk.model.RO.app.SocialDistrictRO;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:45
 */

@RestController
@RequestMapping("district")
public class OldCenterDistrictController {
    @Resource
    SocialuniDistrictAPI socialuniDistrictAPI;
    @PostMapping("queryHotDistricts")
    public ResultRO<List<SocialDistrictRO>> queryHotDistricts() {
        //如果应用，则调用中心
        if (SocialAppConfig.serverIsChild()) {
            return socialuniDistrictAPI.queryHotDistricts();
        }
        return new ResultRO<>(AppData.getHotDistricts());
    }

    @PostMapping("queryDistricts")
    public ResultRO<List<SocialDistrictRO>> queryDistricts() {
        //如果应用，则调用中心
        if (SocialAppConfig.serverIsChild()) {
            return socialuniDistrictAPI.queryDistricts();
        }
        return new ResultRO<>(AppData.getAllDistricts());
    }
}
