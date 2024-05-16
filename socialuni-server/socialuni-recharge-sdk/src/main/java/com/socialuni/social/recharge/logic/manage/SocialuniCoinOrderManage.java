package com.socialuni.social.recharge.logic.manage;

import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.recharge.dao.DO.SocialuniCoinOrderDO;
import com.socialuni.social.user.sdk.dao.DO.SocialuniUserCoinDo;
import org.springframework.stereotype.Service;

@Service
public class SocialuniCoinOrderManage {
    public void createCoinOrder(SocialuniUserCoinDo socialuniUserCoinDo, Integer coinNum, String orderType, String orderDetailType, Integer relationOrderId) {
//        SocialuniCoinOrderDO socialuniCoinOrderDO = SocialuniCoinOrderFactory.createCoinOrderDOByOrderType(socialuniUserCoinDo, coinNum, orderType, relationOrderId);
        SocialuniCoinOrderDO socialuniCoinOrderDO = new SocialuniCoinOrderDO(socialuniUserCoinDo.getUserId(), coinNum, orderType, orderDetailType, socialuniUserCoinDo.getCoin(), relationOrderId);
        //保存coin订单
        SocialuniRepositoryFacade.save(socialuniCoinOrderDO);
    }
}
