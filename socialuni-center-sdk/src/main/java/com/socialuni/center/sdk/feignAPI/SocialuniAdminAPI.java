package com.socialuni.center.sdk.feignAPI;

import com.socialuni.center.sdk.mode.SyncProdDevAccountQO;
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
@RequestMapping("admin")
@FeignClient(name = "admin", url = "${socialuni.admin.dev-server-url:https://devapi.qingchiapp.com}")
public interface SocialuniAdminAPI {
    //同步创建生产环境的开发者账号
    @PostMapping("syncProdDevAccount")
    ResultRO<Void> syncProdDevAccount(@RequestBody @Valid SyncProdDevAccountQO syncProdDevAccountQO);
}
