package com.socialuni.admin.web.controller;


import com.socialuni.admin.web.service.AdminAccountService;
import com.socialuni.social.tance.sdk.api.DevAccountProviderApi;
import com.socialuni.social.tance.sdk.model.DevAccountModel;
import com.socialuni.social.sdk.model.QO.dev.SyncProdDevAccountQO;
import com.socialuni.social.sdk.dao.redis.DevAccountRedis;
import com.socialuni.social.tance.sdk.api.DevAccountApi;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import com.socialuni.social.common.model.ResultRO;
import com.socialuni.social.community.sdk.api.TagApi;
import com.socialuni.social.common.utils.UUIDUtil;
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
    private DevAccountRedis devAccountRedis;
    @Resource
    private DevAccountApi devAccountApi;
    @Resource
    private DevAccountProviderApi devAccountProviderApi;
    @Resource
    private TagApi tagApi;
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
