package com.socialuni.social.common.sdk.model.RO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OAuthGetUserPhoneNumRO {
    private String phoneNum;

   /* public OAuthUserPhoneNumRO(String phoneNum, String devKey) {
        if (StringUtils.isNotEmpty(devKey)) {
            // 秘钥不为空才返回唯一手机号，因为非开放数据
            this.phoneNum = phoneNum;
        }
    }*/
}
