package com.socialuni.api.feignAPI;

import com.socialuni.social.model.model.QO.SocialBindWxPhoneNumQO;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * @author qinkaiyuan
 * @date 2021-07-28 11:09
 * 前端初始化内容
 */
@RequestMapping("prod")
@FeignClient(name = "prod", url = "https://devapi.qingchiapp.com")
public interface SocialuniProdAPI {
    //同步生产环境的开发者账号信息
    @PostMapping("syncProdDevAccount")
    ResultRO<Void> syncProdDevAccount(@RequestBody @Valid SocialBindWxPhoneNumQO bindWxPhoneNumQO);
}
