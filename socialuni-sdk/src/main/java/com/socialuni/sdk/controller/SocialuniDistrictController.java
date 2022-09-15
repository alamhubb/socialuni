package com.socialuni.sdk.controller;

import com.socialuni.sdk.config.SocialuniSystemConst;
import com.socialuni.sdk.feignAPI.SocialuniDistrictAPI;
import com.socialuni.sdk.constant.AppData;
import com.socialuni.sdk.model.RO.app.SocialDistrictRO;
import com.socialuni.social.web.sdk.model.ResultRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:45
 */

@RestController
public class SocialuniDistrictController implements SocialuniDistrictAPI {
    @Resource
    SocialuniDistrictAPI socialuniDistrictAPI;
    @Override
    public ResultRO<List<SocialDistrictRO>> queryHotDistricts() {
        //如果应用，则调用中心
        if (SocialuniSystemConst.serverIsChild()) {
            return socialuniDistrictAPI.queryHotDistricts();
        }
        return new ResultRO<>(AppData.getHotDistricts());
    }

    @Override
    public ResultRO<List<SocialDistrictRO>> queryDistricts() {
        //如果应用，则调用中心
        if (SocialuniSystemConst.serverIsChild()) {
            return socialuniDistrictAPI.queryDistricts();
        }
        return new ResultRO<>(AppData.getAllDistricts());
    }
}
