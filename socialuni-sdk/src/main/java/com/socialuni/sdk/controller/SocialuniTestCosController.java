package com.socialuni.sdk.controller;

import com.socialuni.sdk.utils.SocialuniImgContentUtil;
import com.socialuni.social.web.sdk.model.ResultRO;
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
        SocialuniImgContentUtil.hasQrCodeByCloudAPI(imgKey);
        return new ResultRO<>();
    }
}
