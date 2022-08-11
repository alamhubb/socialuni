package com.socialuni.sdk.model.QO.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2022-04-03 23:34
 */
@Data
public class SocialUserIdImgCheckQO {
    @NotBlank(message = "请上传身份证照片")
    private String idImgUrl;
}
