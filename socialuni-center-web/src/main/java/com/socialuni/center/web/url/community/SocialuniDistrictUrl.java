package com.socialuni.center.web.url.community;

import com.socialuni.center.web.model.RO.app.SocialDistrictRO;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:45
 */

@RequestMapping("district")
public interface SocialuniDistrictUrl {
    @PostMapping("queryHotDistricts")
    ResultRO<List<SocialDistrictRO>> queryHotDistricts();

    @PostMapping("queryDistricts")
    ResultRO<List<SocialDistrictRO>> queryDistricts();
}
