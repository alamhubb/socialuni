package com.socialuni.social.likee.config;

import com.socialuni.social.likee.model.SocialuniLikeAllConfigBO;

public class SocialuniLikeAllConfig {
    public static final Integer sendLikeMsgNeedPayCoinNum = 10;

    //需要一个BO,r
    public static SocialuniLikeAllConfigBO getLikeAllConfigBO() {
        SocialuniLikeAllConfigBO socialuniLikeAllConfigBO = new SocialuniLikeAllConfigBO();
        socialuniLikeAllConfigBO.setSendLikeMsgNeedPayCoinNum(sendLikeMsgNeedPayCoinNum);
        return socialuniLikeAllConfigBO;
    }
}
