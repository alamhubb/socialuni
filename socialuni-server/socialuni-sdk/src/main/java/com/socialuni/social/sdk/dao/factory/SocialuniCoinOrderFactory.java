package com.socialuni.social.sdk.dao.factory;

import com.socialuni.social.sdk.constant.shell.SocialuniCoinOrderType;
import com.socialuni.social.sdk.dao.DO.bussiness.SocialuniCoinOrderDO;

public class SocialuniCoinOrderFactory {

    //充值成功创建订单
    public static SocialuniCoinOrderDO createCoinOrderDOByRechargeSuccess(Integer userId, Integer coin, Integer rechargeOrderId) {
        SocialuniCoinOrderDO socialuniCoinOrderDO = new SocialuniCoinOrderDO(userId, coin, SocialuniCoinOrderType.recharge);
        socialuniCoinOrderDO.setRechargeOrderId(rechargeOrderId);
        return socialuniCoinOrderDO;
    }
}
