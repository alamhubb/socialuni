package com.socialuni.admin.web.controller;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author qinkaiyuan
 * @date 2019-02-14 22:03
 */
@Data
@NoArgsConstructor
public class LoginUserVO {
    @NotBlank
    @Length(min = 11, max = 11)
    private String phoneNum;
    //    private String phoneCountryCode;
    @NotBlank
    @Length(min = 4, max = 4)
    private String authCode;
}