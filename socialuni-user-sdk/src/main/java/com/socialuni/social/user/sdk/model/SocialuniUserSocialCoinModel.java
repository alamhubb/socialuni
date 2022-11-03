package com.socialuni.social.user.sdk.model;

import com.socialuni.social.common.sdk.model.SocialuniContentBaseModel;
import lombok.Data;
import lombok.NoArgsConstructor;

//用户扩展类
@Data
@NoArgsConstructor
public class SocialuniUserSocialCoinModel extends SocialuniContentBaseModel {
    private Integer socialCoin;
}