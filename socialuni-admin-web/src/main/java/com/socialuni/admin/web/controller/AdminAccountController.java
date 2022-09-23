package com.socialuni.admin.web.controller;


import com.socialuni.admin.web.service.AdminAccountService;
import com.socialuni.sdk.dao.DO.dev.DevAccountDO;
import com.socialuni.sdk.model.QO.dev.SyncProdDevAccountQO;
import com.socialuni.sdk.dao.redis.DevAccountRedis;
import com.socialuni.sdk.dao.repository.dev.DevAccountProviderRepository;
import com.socialuni.sdk.dao.repository.dev.DevAccountRepository;
import com.socialuni.sdk.utils.DevAccountUtils;
import com.socialuni.social.common.model.ResultRO;
import com.socialuni.sdk.dao.repository.community.TagRepository;
import com.socialuni.sdk.utils.UUIDUtil;
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
    private DevAccountRepository devAccountRepository;
    @Resource
    private DevAccountProviderRepository devAccountProviderRepository;
    @Resource
    private TagRepository tagRepository;
    @Resource
    private AdminAccountService adminAccountService;

    @PostMapping("getUser")
    public ResultRO<DevAccountRO> getUser() {
        DevAccountDO user = DevAccountUtils.getAdminDevAccountNotNull();
        DevAccountRO devAccountRO = new DevAccountRO(user);
        //则更新用户手机号
        return new ResultRO<>(devAccountRO);
    }

    @PostMapping("resetSecretKey")
    public ResultRO<String> resetSecretKey() {
        DevAccountDO devAccount = DevAccountUtils.getAdminDevAccountNotNull();
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
