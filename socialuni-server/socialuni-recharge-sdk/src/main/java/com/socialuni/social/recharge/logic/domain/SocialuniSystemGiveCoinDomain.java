package com.socialuni.social.recharge.logic.domain;

import com.socialuni.social.recharge.constant.SocialuniCoinOrderType;
import com.socialuni.social.recharge.constant.SocialuniOrderDetailType;
import com.socialuni.social.recharge.logic.entity.SocialuniCreateCoinOrderEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialuniSystemGiveCoinDomain {
    @Resource
    SocialuniCreateCoinOrderEntity socialuniCreateCoinOrderEntity;

    public void giveCoin(Integer userId, Integer coinNum) {
        socialuniCreateCoinOrderEntity.createCoinOrderByOrderType(userId, coinNum, SocialuniCoinOrderType.recharge, SocialuniOrderDetailType.sysGive, null);
    }
}
