package com.socialuni.admin.web.controller;


import com.socialuni.admin.web.service.AdminAccountService;
import com.socialuni.social.common.sdk.model.ResultRO;
import com.socialuni.social.common.sdk.utils.UUIDUtil;
import com.socialuni.social.sdk.model.QO.dev.SyncProdDevAccountQO;
import com.socialuni.social.tance.sdk.api.DevAccountRedisInterface;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import com.socialuni.social.tance.sdk.model.DevAccountModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("user")
public class AdminAccountController {
    @Resource
    private DevAccountRedisInterface devAccountRedis;
    @Resource
    private AdminAccountService adminAccountService;

    @PostMapping("getUser")
    public ResultRO<DevAccountRO> getUser() {
        DevAccountModel user = DevAccountFacade.getAdminDevAccountNotNull();
        DevAccountRO devAccountRO = new DevAccountRO(user);
        //则更新用户手机号
        return new ResultRO<>(devAccountRO);
    }

    @PostMapping("resetSecretKey")
    public ResultRO<String> resetSecretKey() {
        DevAccountModel devAccount = DevAccountFacade.getAdminDevAccountNotNull();
        String secretKey = UUIDUtil.getUUID();
        devAccount.setSecretKey(secretKey);

        //新建和修改，数量必然大于0，代表需要向开发环境同步
        SyncProdDevAccountQO syncProdDevAccountQO = new SyncProdDevAccountQO(devAccount, new ArrayList<>());
        //调用api的时候，要区分出来是更新还是新增，尽早区分
//        socialuniAdminAPI.syncProdDevAccount(syncProdDevAccountQO);

        devAccountRedis.saveDevAccount(devAccount);
        ResultRO<String> ResultRO = new ResultRO<>();
        ResultRO.setData(secretKey);
        //则更新用户手机号
        return ResultRO;
    }

    @PostMapping("updateDevAccount")
    public ResultRO<DevAccountRO> updateDevAccount(@RequestBody @Valid DevAccountUpdateQO devAccountQO) {
        //则更新用户手机号
        return adminAccountService.updateDevAccount(devAccountQO);
    }
}
