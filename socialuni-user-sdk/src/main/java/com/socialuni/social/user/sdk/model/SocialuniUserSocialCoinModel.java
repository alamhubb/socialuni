package com.socialuni.social.user.sdk.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//用户扩展类
@Data
@NoArgsConstructor
public class SocialuniUserSocialCoinModel extends SocialuniUserInfoBaseModel {
    private Integer socialCoin;
}