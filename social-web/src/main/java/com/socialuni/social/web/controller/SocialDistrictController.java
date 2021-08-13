package com.socialuni.social.web.controller;

import com.socialuni.social.sdk.constant.AppData;
import com.socialuni.social.model.model.RO.app.SocialDistrictRO;
import com.socialuni.social.api.model.model.ResultRO;
import com.socialuni.social.sdk.url.community.SocialuniDistrictUrl;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:45
 */

@RestController
public class SocialDistrictController implements SocialuniDistrictUrl {
    @Override
    public ResultRO<List<SocialDistrictRO>> queryHotDistricts() {
        return new ResultRO<>(AppData.getHotDistricts());
    }

    @Override
    public ResultRO<List<SocialDistrictRO>> queryDistricts() {
        return new ResultRO<>(AppData.getAllDistricts());
    }
}
