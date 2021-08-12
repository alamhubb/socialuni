package com.qingchi.social.web.controller;

import lombok.Data;

/**
 * @author qinkaiyuan
 * @date 2019-02-14 22:03
 */
@Data
public class LoginUserVO {
    //手机号登录使用
    private String phoneNum;
    private String authCode;
}
