package com.socialuni.sdk.controller;

import com.socialuni.sdk.config.SocialuniSystemConst;
import com.socialuni.sdk.logic.platform.tencent.TencentCloudAPI;
import com.socialuni.sdk.logic.platform.weixin.WxConst;
import com.socialuni.sdk.utils.ImgContentUtil;
import com.socialuni.sdk.utils.common.RestUtil;
import com.socialuni.social.web.sdk.model.ResultRO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:45
 */

@RestController
@RequestMapping("test")
public class SocialuniTestCosController {

    @GetMapping("test1")
    public ResultRO testCos(String imgKey) {
        return new ResultRO<>(ImgContentUtil.getImgTextContent(imgKey));
    }
}
