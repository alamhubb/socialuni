package com.socialuni.demo.controller.socialuni;

import com.socialuni.api.feignAPI.SocialuniDistrictAPI;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.RO.app.SocialDistrictRO;
import com.socialuni.social.sdk.constant.AppData;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:45
 */

@RestController
public class DistrictController implements SocialuniDistrictAPI {
    @Override
    public ResultRO<List<SocialDistrictRO>> queryHotDistricts() {
        return new ResultRO<>(AppData.getHotDistricts());
    }

    @Override
    public ResultRO<List<SocialDistrictRO>> queryDistricts() {
        return new ResultRO<>(AppData.getAllDistricts());
    }
}
