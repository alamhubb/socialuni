package com.socialuni.social.im.model;

import lombok.Data;

import java.util.Date;

/**
 * @author wulinghui
 * @version 1.0
 * @module Socialuni
 * @date 2022/11/4 14:44
 * @since 1.0
 */
@Data
public class ImUserModel implements ImInterface {
   Date birth;  // 用户生日
   Date createTime; // 创建时间
    String email;  // 用户邮箱地址
    String ex;     // 用户信息扩展字段对象Json字符串
    /**
     * 用户头像
     */
    String faceURL;  //'
    // 用户性别 0:未知 1:女 2:男
    int  gender;
    /**
     * 用户昵称
     */
    String  nickname ;
    /**
     * 用户手机号
     */
    String  phoneNumber;
    /**
     *  用户id
     */
    String userID;
}
