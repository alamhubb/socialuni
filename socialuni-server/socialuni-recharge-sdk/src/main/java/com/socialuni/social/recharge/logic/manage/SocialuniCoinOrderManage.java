package com.socialuni.social.recharge.logic.manage;

import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.recharge.constant.SocialuniCoinOrderType;
import com.socialuni.social.recharge.dao.DO.SocialuniCoinOrderDO;
import com.socialuni.social.user.sdk.dao.DO.SocialuniUserCoinDo;
import org.springframework.stereotype.Service;

@Service
public class SocialuniCoinOrderManage {
    public void createCoinOrder(SocialuniUserCoinDo socialuniUserCoinDo, Integer coinNum, String orderType, Integer relationOrderId) {
//        SocialuniCoinOrderDO socialuniCoinOrderDO = SocialuniCoinOrderFactory.createCoinOrderDOByOrderType(socialuniUserCoinDo, coinNum, orderType, relationOrderId);
        SocialuniCoinOrderDO socialuniCoinOrderDO = new SocialuniCoinOrderDO(socialuniUserCoinDo.getUserId(), coinNum, orderType, socialuniUserCoinDo.getCoin());
        if (SocialuniCoinOrderType.recharge.equals(orderType)) {
            socialuniCoinOrderDO.setRechargeOrderId(relationOrderId);
        } else if (SocialuniCoinOrderType.expense.equals(orderType)) {
            socialuniCoinOrderDO.setUserContactId(relationOrderId);
        }
        //保存coin订单
        SocialuniRepositoryFacade.save(socialuniCoinOrderDO);
    }
}
