package com.socialuni.social.user.sdk.model.QO;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2022-04-03 23:34
 */
@Data
public class SocialUserIdentityAuthQO {
    @NotBlank(message = "请上传身份证照片")
    private String idImgUrl;
    @NotBlank(message = "请上传自拍")
    private String selfieImgUrl;
}
