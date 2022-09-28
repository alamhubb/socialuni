package com.socialuni.social.tance.controller;

import com.socialuni.social.common.model.ResultRO;
import com.socialuni.social.tance.entity.DevAccountDO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

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

    @Data
    @NoArgsConstructor
    public class DevAccountQueryQO {
        @NotBlank
        private String secretKey;

        public DevAccountQueryQO(String secretKey) {
            this.secretKey = secretKey;
        }
    }
}
