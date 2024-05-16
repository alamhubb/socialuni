package com.socialuni.social.recharge.logic.domain;

import com.socialuni.social.recharge.constant.SocialuniCoinOrderType;
import com.socialuni.social.recharge.logic.entity.SocialuniCreateCoinOrderEneity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialuniSystemGiveCoinDomain {
    @Resource
    SocialuniCreateCoinOrderEneity socialuniCreateCoinOrderEneity;

    public void giveCoin(Integer userId, Integer coinNum) {
        socialuniCreateCoinOrderEneity.createCoinOrderByOrderType(userId, coinNum, SocialuniCoinOrderType.sysGive, null);
    }
}
