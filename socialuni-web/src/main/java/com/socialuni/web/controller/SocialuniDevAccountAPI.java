package com.socialuni.web.controller;

import com.socialuni.sdk.dao.DO.dev.DevAccountDO;
import com.socialuni.sdk.model.QO.dev.DevAccountQueryQO;
import com.socialuni.social.common.model.ResultRO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * @author qinkaiyuan
 * @date 2021-07-28 11:09
 * 前端初始化内容
 */
@RequestMapping("devAccount")
public interface SocialuniDevAccountAPI {
    //同步创建生产环境的开发者账号
    @PostMapping("queryDevAccount")
    ResultRO<DevAccountDO> queryDevAccount(@RequestBody @Valid DevAccountQueryQO devAccountQueryQO);

    @PostMapping("queryDevAccountProvider")
    ResultRO<DevAccountDO> queryDevAccountProvider(@RequestBody @Valid DevAccountQueryQO devAccountQueryQO);
}
