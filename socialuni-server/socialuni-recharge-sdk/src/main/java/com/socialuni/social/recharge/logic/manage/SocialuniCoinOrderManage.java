package com.socialuni.social.recharge.logic.manage;

import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.recharge.dao.DO.SocialuniCoinOrderDO;
import com.socialuni.social.recharge.dao.repository.SocialuniCoinOrderRepository;
import com.socialuni.social.user.sdk.dao.DO.SocialuniUserCoinDo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialuniCoinOrderManage {
    @Resource
    SocialuniCoinOrderRepository socialuniCoinOrderRepository;

    public void createCoinOrder(SocialuniUserCoinDo socialuniUserCoinDo, Integer coinNum, String orderType, String orderDetailType, Long relationOrderId) {
//        SocialuniCoinOrderDO socialuniCoinOrderDO = SocialuniCoinOrderFactory.createCoinOrderDOByOrderType(socialuniUserCoinDo, coinNum, orderType, relationOrderId);
        SocialuniCoinOrderDO socialuniCoinOrderDO = new SocialuniCoinOrderDO(socialuniUserCoinDo.getUserId(), coinNum, orderType, orderDetailType, socialuniUserCoinDo.getCoin(), relationOrderId);
        //保存coin订单
        socialuniCoinOrderRepository.save(socialuniCoinOrderDO);
    }
}
