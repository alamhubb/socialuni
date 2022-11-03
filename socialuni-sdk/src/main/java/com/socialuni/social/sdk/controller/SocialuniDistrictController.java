package com.socialuni.social.sdk.controller;

import com.socialuni.social.common.sdk.model.ResultRO;
import com.socialuni.social.sdk.constant.AppData;
import com.socialuni.social.sdk.feignAPI.openData.SocialuniDistrictAPI;
import com.socialuni.social.sdk.model.RO.app.SocialDistrictRO;
import com.socialuni.social.tance.sdk.enumeration.SocialuniSystemConst;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:45
 */

@RestController
@RequestMapping("socialuni/district")
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
