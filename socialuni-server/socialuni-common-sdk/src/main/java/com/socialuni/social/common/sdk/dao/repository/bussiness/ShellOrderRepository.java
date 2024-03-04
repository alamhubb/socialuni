package com.socialuni.social.common.sdk.dao.repository.bussiness;

import com.socialuni.social.common.sdk.dao.DO.bussiness.SocialuniCoinOrderDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShellOrderRepository extends JpaRepository<SocialuniCoinOrderDO, Integer> {
    //    VipOrderDO findFirstByEnabledAndUserOrderByIdDesc(Boolean enabled, Integer userId);
    //支付成功后需要修改用户拥有贝壳
    List<SocialuniCoinOrderDO> findAllByUserIdAndStatusOrderByCreateTimeDesc(Integer userId, String status);
}
