package com.socialuni.social.sdk.model.QO;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author qinkaiyuan
 * @date 2019-02-14 22:03
 */
@Data
public class SocialBindWxPhoneNumQO {
    @NotBlank
    private String code;
    //微信mp获取unionId使用，
    //百度小程序使用
    @NotBlank
    private String encryptedData;
    @NotBlank
    private String iv;
}
