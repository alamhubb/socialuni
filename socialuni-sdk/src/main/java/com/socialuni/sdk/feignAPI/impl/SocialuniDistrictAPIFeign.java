package com.socialuni.sdk.feignAPI.impl;

import com.socialuni.sdk.feignAPI.SocialuniDistrictAPI;
import com.socialuni.sdk.model.RO.app.SocialDistrictRO;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:45
 */

@FeignClient(name = "district", url = "${socialuni.server-url:https://api.socialuni.cn}")
public interface SocialuniDistrictAPIFeign extends SocialuniDistrictAPI {
}
