package com.socialuni.admin.web.controller;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class DevAccountUpdateQO {
    private String type;
    private String realName;

    @NotBlank
    @Length(min = 2, max = 6)
    private String appName;
    private String wxMpAppName;
    private String wxMpAppId;
    private String qqMpAppName;
    private String qqMpAppId;
}
