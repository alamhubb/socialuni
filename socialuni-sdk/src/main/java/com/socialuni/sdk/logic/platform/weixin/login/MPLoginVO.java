package com.socialuni.sdk.logic.platform.weixin.login;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author qinkaiyuan
 * @date 2019-02-14 22:03
 */
@Data
public class MPLoginVO {
    @NotBlank(message = "参数异常")
    private String code;
    @NotBlank(message = "参数异常")
    private String avatarUrl;
    @NotNull
    @Range(min = 0, max = 2, message = "参数异常")
    private Integer gender;
    @NotBlank(message = "参数异常")
    private String nickName;
    private String city;
    private String loginType;
    private String encryptedData;
    private String iv;

    public MPLoginVO() {
    }

    public MPLoginVO(MPBindPhoneVO bindPhoneVO) {
        this.code = bindPhoneVO.getCode();
        this.loginType = bindPhoneVO.getLoginType();
        this.encryptedData = bindPhoneVO.getEncryptedData();
        this.iv = bindPhoneVO.getIv();
    }
}
