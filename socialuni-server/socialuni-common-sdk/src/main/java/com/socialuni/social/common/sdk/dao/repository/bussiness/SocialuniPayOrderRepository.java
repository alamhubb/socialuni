package com.socialuni.social.common.sdk.dao.repository.bussiness;

import com.socialuni.social.common.sdk.dao.DO.bussiness.SocialuniPayCoinOrderDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialuniPayOrderRepository extends JpaRepository<SocialuniPayCoinOrderDO, Integer> {

    SocialuniPayCoinOrderDO findFirstByOrderNo(String orderNo);
}

