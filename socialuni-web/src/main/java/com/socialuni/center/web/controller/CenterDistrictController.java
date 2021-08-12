package com.socialuni.center.web.controller;

import com.socialuni.api.feignAPI.SocialuniDistrictAPI;
import com.socialuni.sdk.constant.AppData;
import com.socialuni.social.model.model.RO.ResultRO;
import com.socialuni.social.model.model.RO.app.SocialDistrictRO;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:45
 */

@RestController
public class CenterDistrictController implements SocialuniDistrictAPI {
    @Override
    public ResultRO<List<SocialDistrictRO>> queryHotDistricts() {
        return new ResultRO<>(AppData.getHotDistricts());
    }

    @Override
    public ResultRO<List<SocialDistrictRO>> queryDistricts() {
        return new ResultRO<>(AppData.getAllDistricts());
    }
}
