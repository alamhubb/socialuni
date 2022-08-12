package com.socialuni.sdk.feignAPI.impl;

import com.socialuni.sdk.feignAPI.SocialuniReportAPI;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "report", url = "${socialuni.server-url:https://api.socialuni.cn}")
public interface SocialuniReportAPIFeign extends SocialuniReportAPI {
}