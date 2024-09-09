package com.socialuni.social.im.api.model.DTO;

import com.socialuni.social.common.api.model.user.SocialuniUserRO;

//用于联盟之间项目传输通知
public class SocialuniNotifyDTO<T> {

    SocialuniUserRO user;
    T data;

}
