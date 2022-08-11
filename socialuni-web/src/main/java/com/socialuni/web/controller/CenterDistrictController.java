package com.socialuni.web.controller;

import com.socialuni.sdk.feignAPI.SocialuniDistrictAPI;
import com.socialuni.sdk.constant.AppData;
import com.socialuni.sdk.model.RO.app.SocialDistrictRO;
import com.socialuni.social.api.model.ResultRO;
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
